package com.youT.seenEar.domain.adviceCard.application.port.in;

import com.youT.seenEar.domain.adviceCard.adapter.in.response.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.domain.AdviceType;
import com.youT.seenEar.domain.adviceCard.domain.ConcernType;
import com.youT.seenEar.domain.member.domain.Member;

public interface AdviceCardUseCase {

    AdviceCardResponse loadAdviceResponse(ConcernType concernType, AdviceType adviceType, Member member);

}
