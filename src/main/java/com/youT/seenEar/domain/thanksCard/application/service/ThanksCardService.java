package com.youT.seenEar.domain.thanksCard.application.service;

import com.youT.seenEar.domain.adviceCard.adapter.out.persistence.external.response.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.adapter.out.persistence.external.response.OpenAIResponse;
import com.youT.seenEar.domain.adviceCard.application.service.OpenAIService;
import com.youT.seenEar.domain.adviceCard.domain.AdviceCard;
import com.youT.seenEar.domain.member.application.port.out.LoadMemberPort;
import com.youT.seenEar.domain.member.domain.Member;
import com.youT.seenEar.domain.thanksCard.adapter.in.request.PostThanksCardTextRequest;
import com.youT.seenEar.domain.thanksCard.adapter.in.response.ThanksCardResponse;
import com.youT.seenEar.domain.thanksCard.application.port.in.ThanksCardUseCase;
import com.youT.seenEar.domain.thanksCard.application.port.out.SaveThanksCardPort;
import com.youT.seenEar.domain.thanksCard.domain.ThanksCard;
import com.youT.seenEar.global.exception.BaseException;
import com.youT.seenEar.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ThanksCardService implements ThanksCardUseCase {

    private final LoadMemberPort loadMemberPort;

    private final SaveThanksCardPort saveThanksCardPort;

    private final AwsPollyService awsPollyService;

    private final OpenAIService openAIService;

    private final S3MultipartService s3MultipartService;
    @Override
    public ThanksCardResponse postThanksCardText(Member youth, PostThanksCardTextRequest postThanksCardTextRequest)  {

        Member elder = loadMemberPort.findByUuid(postThanksCardTextRequest.getElderUuid()).orElseThrow(()->
                new BaseException(ErrorCode.NOT_FOUND));

        String speechUrl = awsPollyService.getTTS(postThanksCardTextRequest.getText());

        log.info(speechUrl);
        ThanksCard thanksCard = ThanksCard.builder()
                .thanksElder(elder)
                .thanksYouth(youth)
                .text(postThanksCardTextRequest.getText())
                .speechUrl(speechUrl)
                .build();
        saveThanksCardPort.saveThanksCardText(thanksCard);
        return ThanksCardResponse.builder()
                .text(thanksCard.getText())
                .speechUrl(thanksCard.getSpeechUrl())
                .build();
    }


    @Override
    public ThanksCardResponse postThanksCardSpeech(Member youth, MultipartFile multipartFile, String elderUuid) throws IOException {

        Member elder = loadMemberPort.findByUuid(elderUuid).orElseThrow(()->
                new BaseException(ErrorCode.NOT_FOUND));


        String speechUrl = s3MultipartService.upload(multipartFile,"seenEar");

        ResponseEntity<OpenAIResponse> openAIResponse =  openAIService.getOpenAIResponse(multipartFile);

        ThanksCard thanksCard = ThanksCard.builder()
                .thanksYouth(youth)
                .thanksElder(elder)
                .speechUrl(speechUrl)
                .text(Objects.requireNonNull(openAIResponse.getBody()).getText())
                .build();

        saveThanksCardPort.saveThanksCardText(thanksCard);
        return ThanksCardResponse.builder()
                .text(thanksCard.getText())
                .speechUrl(thanksCard.getSpeechUrl())
                .build();

    }


}
