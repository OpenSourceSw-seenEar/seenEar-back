package com.youT.seenEar.domain.adviceCard.application.service;

import com.youT.seenEar.domain.adviceCard.adapter.in.request.AdviceCardSortRequest;
import com.youT.seenEar.domain.adviceCard.adapter.in.response.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.adapter.in.response.ChatGptResponse;
import com.youT.seenEar.domain.adviceCard.application.port.in.ChatGptUseCase;
import com.youT.seenEar.domain.adviceCard.application.port.out.LoadAdviceCardPort;
import com.youT.seenEar.domain.adviceCard.domain.AdviceCard;
import com.youT.seenEar.domain.adviceCard.domain.ConcernType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGptService implements ChatGptUseCase {


    private final LoadAdviceCardPort loadAdviceCardPort;
    @Override
    public AdviceCardResponse sortAdviceType(Long id) throws UnsupportedEncodingException {

        AdviceCard adviceCard = loadAdviceCardPort.findById(id);
        log.info(adviceCard.getText());
        ChatGptResponse chatGptResponse =  getChatGptResponse(adviceCard.getText());
        log.info(chatGptResponse.getAdvice());
        adviceCard.updateConcernType(getConcernType(chatGptResponse.getAdvice()));

        return AdviceCardResponse.builder()
                .elderUuid(adviceCard.getAdvisor().getUuid())
                .text(adviceCard.getText())
                .concernType(adviceCard.getConcernType())
                .build();
    }

    public ChatGptResponse getChatGptResponse(String text) throws UnsupportedEncodingException {

        String gptURL = "https://develop.api.maybeclean.link/sort-advice";
        String param = URLEncoder.encode(text, StandardCharsets.UTF_8);
        log.info(param);
        String url = gptURL+"?advice="+param;
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity<ChatGptResponse> response = restTemplate.getForEntity(url,ChatGptResponse.class);
        return response.getBody();

    }

    public ConcernType getConcernType(String text){

        if(text.equals("인간관계")){
            return ConcernType.PERSON;
        }else if (text.equals("가족")){
            return ConcernType.FAMILY;
        }else if (text.equals("건강")){
            return ConcernType.HEALTH;
        }else if (text.equals("결혼")){
            return ConcernType.MARRIAGE;
        }else if (text.equals("꿈/공부")){
            return ConcernType.DREAM;
        }else if (text.equals("돈")){
            return ConcernType.MONEY;
        }else if (text.equals("진로")){
            return ConcernType.VISION;
        }else if (text.equals("회사")){
            return ConcernType.CAREER;
        }
        return ConcernType.ELSE;
    }

}
