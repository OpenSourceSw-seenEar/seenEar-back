package com.youT.seenEar.domain.adviceCard.application.port.in;

import com.youT.seenEar.domain.adviceCard.adapter.out.external.response.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.domain.AdviceType;
import com.youT.seenEar.domain.member.domain.Member;
import org.springframework.web.multipart.MultipartFile;


public interface OpenAIUseCase {

    AdviceCardResponse getAdviceText(Member member, MultipartFile multipartFile, AdviceType adviceType);
}