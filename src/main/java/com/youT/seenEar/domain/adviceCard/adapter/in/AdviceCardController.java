package com.youT.seenEar.domain.adviceCard.adapter.in;

import com.youT.seenEar.domain.adviceCard.adapter.in.response.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.adapter.in.response.YouthAdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.application.port.in.AdviceCardUseCase;
import com.youT.seenEar.domain.adviceCard.domain.AdviceType;
import com.youT.seenEar.domain.adviceCard.domain.ConcernType;
import com.youT.seenEar.domain.member.domain.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advice_card")
@RequiredArgsConstructor
public class AdviceCardController {

    private final AdviceCardUseCase adviceCardUseCase;
    // 조언카드는 tts
    @Operation(description = "[청년] 조언카드 받기 ")
    @GetMapping("/receive/youth/{concernType}/{adviceType}")
    public ResponseEntity<AdviceCardResponse> loadAdvice (@AuthenticationPrincipal CustomUserDetails customUserDetails ,
                                                          @PathVariable("concernType")String concernType,
                                                          @PathVariable("adviceType")String adviceType) {

        ConcernType concernTypeEnum=ConcernType.valueOf(concernType);
        AdviceType adviceTypeEnum=AdviceType.valueOf(adviceType);
        return ResponseEntity.ok().body(this.adviceCardUseCase.loadAdviceResponse(concernTypeEnum,adviceTypeEnum,customUserDetails.getMember()));

    }
    @Operation(description = "[청년] 조언카드 조회하기 ")
    @GetMapping("/youth")
    public ResponseEntity<List<YouthAdviceCardResponse>> getAdviceCardList (@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        return ResponseEntity.ok().body(this.adviceCardUseCase.getAdviceCardList(customUserDetails.getMember()));

    }


}