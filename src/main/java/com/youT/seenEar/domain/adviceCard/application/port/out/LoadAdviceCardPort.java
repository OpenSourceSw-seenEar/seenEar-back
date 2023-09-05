package com.youT.seenEar.domain.adviceCard.application.port.out;

import com.youT.seenEar.domain.adviceCard.adapter.in.response.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.adapter.in.response.YouthAdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.domain.AdviceCard;
import com.youT.seenEar.domain.adviceCard.domain.AdviceType;
import com.youT.seenEar.domain.adviceCard.domain.ConcernType;
import com.youT.seenEar.domain.member.domain.Member;

import java.util.List;

public interface LoadAdviceCardPort {

    AdviceCardResponse loadAdviceCard(ConcernType concernType, AdviceType adviceType, Member member);

    List<YouthAdviceCardResponse> getAdviceCardList(Member member);

    AdviceCard findById(Long id);

}