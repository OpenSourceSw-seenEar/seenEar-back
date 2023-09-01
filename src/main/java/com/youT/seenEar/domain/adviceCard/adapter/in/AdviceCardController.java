package com.youT.seenEar.domain.adviceCard.adapter.in;

import com.youT.seenEar.domain.adviceCard.adapter.in.request.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.application.port.in.AdviceCardUseCase;
import com.youT.seenEar.domain.adviceCard.domain.AdviceType;
import com.youT.seenEar.domain.adviceCard.domain.ConcernType;
import com.youT.seenEar.domain.member.domain.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/advice")
@RequiredArgsConstructor
public class AdviceCardController {

    private final AdviceCardUseCase adviceCardUseCase;
    @Operation(description = "[청년] 조언카드 받기 ")
    @GetMapping("/receive/youth/{concernType}/{adviceType}")
    public ResponseEntity<AdviceCardResponse> loadAdvice (@AuthenticationPrincipal CustomUserDetails customUserDetails ,
                                                    @PathVariable("concernType")String concernType,
                                                    @PathVariable("adviceType")String adviceType) {

            ConcernType concernTypeEnum=ConcernType.valueOf(concernType);
            AdviceType adviceTypeEnum=AdviceType.valueOf(adviceType);
            return ResponseEntity.ok().body(this.adviceCardUseCase.loadAdviceResponse(concernTypeEnum,adviceTypeEnum,customUserDetails.getMember()));

    }
    @GetMapping("/test")
    public ResponseEntity<?> index(){
        return ResponseEntity.ok().body("hi");
    }
}
