package com.youT.seenEar.domain.adviceCard.application.port.out;

import com.youT.seenEar.domain.adviceCard.adapter.in.request.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.domain.AdviceCard;
import com.youT.seenEar.domain.member.domain.Member;

public interface SaveAdviceCardPort {

    AdviceCard saveElderAdviceCard(Member elder,AdviceCard adviceCard);
    AdviceCardResponse saveYouthAdviceMapping(Member youth, AdviceCard adviceCard);

}
