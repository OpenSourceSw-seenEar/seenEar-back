package com.youT.seenEar.domain.adviceCard.application.service;

import com.youT.seenEar.domain.adviceCard.adapter.out.persistence.external.request.AdviceCardRequest;
import com.youT.seenEar.domain.adviceCard.adapter.out.persistence.external.response.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.adapter.out.persistence.external.response.OpenAIResponse;
import com.youT.seenEar.domain.adviceCard.application.port.in.OpenAIUseCase;
import com.youT.seenEar.domain.adviceCard.application.port.out.LoadAdviceCardPort;
import com.youT.seenEar.domain.adviceCard.application.port.out.SaveAdviceCardPort;
import com.youT.seenEar.domain.adviceCard.domain.AdviceCard;
import com.youT.seenEar.domain.adviceCard.domain.AdviceType;
import com.youT.seenEar.domain.member.adapter.out.external.response.KakaoTokens;
import com.youT.seenEar.domain.member.domain.Member;
import com.youT.seenEar.global.exception.BaseException;
import com.youT.seenEar.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class OpenAIService implements OpenAIUseCase {

    private final LoadAdviceCardPort loadAdviceCardPort;

    private final SaveAdviceCardPort saveAdviceCardPort;

    @Value("${open-ai.secretKey}")
    private String secretKey;
    @Override
    @Transactional
    public AdviceCardResponse getText(Member member, MultipartFile multipartFile, AdviceType adviceType) {

        ResponseEntity<OpenAIResponse> response = getOpenAIResponse(multipartFile);

        if(response == null){
            throw new BaseException(ErrorCode.INVALID_VALUE);
        }
        AdviceCard adviceCard=AdviceCard.builder()
                .advisor(member)
                .adviceType(adviceType)
                .text(response.getBody().getText()).build();
        Long saveId = saveAdviceCardPort.saveElderAdviceCard(adviceCard).getId();
        return AdviceCardResponse.builder()
                .id(saveId)
                .text(adviceCard.getText())
                .build();
    }

    public ResponseEntity<OpenAIResponse> getOpenAIResponse(MultipartFile multipartFile){

      try{
          String openAIURL="https://api.openai.com/v1/audio/translations";

          HttpHeaders httpHeaders=new HttpHeaders();
          httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
          httpHeaders.add("Authorization","Bearer "+secretKey);

          // Convert MultipartFile to ByteArrayResource
          ByteArrayResource fileResource = new ByteArrayResource(multipartFile.getBytes()) {
              @Override
              public String getFilename() {
                  return multipartFile.getOriginalFilename();
              }
          };


          MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
          body.add("model","whisper-1");
          body.add("file",fileResource);

          HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body,httpHeaders);
          RestTemplate restTemplate= new RestTemplate();
          ResponseEntity<OpenAIResponse> response = restTemplate.postForEntity(openAIURL, request, OpenAIResponse.class);

          return response;


      } catch(Exception e){
          log.info(e.getMessage());
      }
      return null;
    }
}
