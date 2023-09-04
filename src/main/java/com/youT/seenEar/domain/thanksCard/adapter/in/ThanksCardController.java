package com.youT.seenEar.domain.thanksCard.adapter.in;

import com.youT.seenEar.domain.member.domain.CustomUserDetails;
import com.youT.seenEar.domain.thanksCard.adapter.in.request.PostThanksCardTextRequest;
import com.youT.seenEar.domain.thanksCard.adapter.in.response.ElderThanksCardResponse;
import com.youT.seenEar.domain.thanksCard.adapter.in.response.ThanksCardResponse;
import com.youT.seenEar.domain.thanksCard.application.port.in.ThanksCardUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/thanks")
public class ThanksCardController {

    private final  ThanksCardUseCase thanksCardUseCase;
    @Operation(description = "[청년] 감사편지 보내기 (텍스트) ")
    @PostMapping("/youth/text")
    public ResponseEntity<ThanksCardResponse> postThanksCardText(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                     @RequestBody PostThanksCardTextRequest postThanksCardTextRequest) throws IOException {

        return ResponseEntity.ok().body(thanksCardUseCase.postThanksCardText(customUserDetails.getMember(),postThanksCardTextRequest));

    }

    @Operation(description = "[청년] 감사편지 보내기 (speech) ")
    @PostMapping("/youth/speech")
    public ResponseEntity<ThanksCardResponse> postThanksCardSpeech(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                                   @RequestPart("file") MultipartFile multipartFile,
                                                                   @RequestPart("elderUuid") String elderUuid) throws IOException {

        return ResponseEntity.ok().body(thanksCardUseCase.postThanksCardSpeech(customUserDetails.getMember(),multipartFile, elderUuid));

    }

    @Operation(description = "[노인] 감사편지 조회하기 ")
    @GetMapping("/elder")
    public ResponseEntity<List<ElderThanksCardResponse>> getThanksCardList(@AuthenticationPrincipal CustomUserDetails customUserDetails){

        return ResponseEntity.ok().body(thanksCardUseCase.getElderThanksCard(customUserDetails.getMember()));


    }
}
