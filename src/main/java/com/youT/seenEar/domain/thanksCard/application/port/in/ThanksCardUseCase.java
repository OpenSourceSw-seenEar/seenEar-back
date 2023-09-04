package com.youT.seenEar.domain.thanksCard.application.port.in;

import com.youT.seenEar.domain.member.domain.Member;
import com.youT.seenEar.domain.thanksCard.adapter.in.request.PostThanksCardTextRequest;
import com.youT.seenEar.domain.thanksCard.adapter.in.response.ThanksCardResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ThanksCardUseCase {

    ThanksCardResponse postThanksCardText(Member youth, PostThanksCardTextRequest postThanksCardTextRequest) throws IOException;

    ThanksCardResponse postThanksCardSpeech(Member youth, MultipartFile multipartFile, String elderUuid) throws IOException;

}
