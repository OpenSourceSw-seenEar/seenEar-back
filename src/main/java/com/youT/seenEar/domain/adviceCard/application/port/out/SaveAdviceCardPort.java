package com.youT.seenEar.domain.adviceCard.application.port.out;

import com.youT.seenEar.domain.adviceCard.adapter.in.response.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.adapter.out.persistence.external.response.OpenAIResponse;
import com.youT.seenEar.domain.adviceCard.domain.AdviceCard;
import com.youT.seenEar.domain.member.domain.Member;

public interface SaveAdviceCardPort {

   AdviceCard saveElderAdviceCard(AdviceCard adviceCard);
    AdviceCardResponse saveYouthAdviceMapping(Member youth, AdviceCard adviceCard);

}
