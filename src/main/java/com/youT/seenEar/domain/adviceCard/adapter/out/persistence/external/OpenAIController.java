package com.youT.seenEar.domain.adviceCard.adapter.out.persistence.external;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youT.seenEar.domain.adviceCard.adapter.out.persistence.external.request.AdviceCardRequest;
import com.youT.seenEar.domain.adviceCard.adapter.out.persistence.external.response.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.adapter.out.persistence.external.response.OpenAIResponse;
import com.youT.seenEar.domain.adviceCard.application.port.in.OpenAIUseCase;
import com.youT.seenEar.domain.adviceCard.domain.AdviceType;
import com.youT.seenEar.domain.member.domain.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advice")
public class OpenAIController {

    private final OpenAIUseCase openAIUseCase;
    @Operation(description = "[노인] 조언카드 녹음하기 불맛 ")
    @PostMapping("/elder/short")
    public ResponseEntity<AdviceCardResponse> recordHotAdvice (@AuthenticationPrincipal CustomUserDetails customUserDetails ,
                                                               @RequestParam("file")MultipartFile multipartFile) {

        return ResponseEntity.ok().body(this.openAIUseCase.getText(customUserDetails.getMember(), multipartFile, AdviceType.SHORT));

    }

    @Operation(description = "[노인] 조언카드 녹음하기 착한맛 ")
    @PostMapping("/elder/mild")
    public ResponseEntity<AdviceCardResponse> recordAdvice (@AuthenticationPrincipal CustomUserDetails customUserDetails ,
                                                        @RequestParam("file")MultipartFile multipartFile) {

        return ResponseEntity.ok().body(this.openAIUseCase.getText(customUserDetails.getMember(), multipartFile, AdviceType.EXPERIENCE));

    }

    @PostMapping("/test")
    public ResponseEntity<?> testOpenAI(@RequestParam("file")MultipartFile multipartFile){
        System.err.println(multipartFile.getSize());

        return ResponseEntity.ok().body("");
    }
}
