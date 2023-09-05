package com.youT.seenEar.domain.adviceCard.adapter.out.external;

import com.youT.seenEar.domain.adviceCard.adapter.in.request.AdviceCardSortRequest;
import com.youT.seenEar.domain.adviceCard.adapter.in.response.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.application.port.in.ChatGptUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advice")
public class ChatGptController {

    private final ChatGptUseCase chatGptUseCase;

    @Operation(description = "[노인] 조언 카드 AI 서버에 요청 보내서 sorting 하기 ")
    @GetMapping("/sort/{adviceId}")
    public ResponseEntity<AdviceCardResponse> sortAdviceType (@PathVariable(value = "adviceId") Long id) throws UnsupportedEncodingException {

        log.info(String.valueOf(id));
        return ResponseEntity.ok().body(this.chatGptUseCase.sortAdviceType(id));

    }
}
