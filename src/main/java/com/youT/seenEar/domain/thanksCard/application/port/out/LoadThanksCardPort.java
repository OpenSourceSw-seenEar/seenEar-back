package com.youT.seenEar.domain.thanksCard.application.port.out;

import com.youT.seenEar.domain.member.domain.Member;
import com.youT.seenEar.domain.thanksCard.adapter.in.response.ElderThanksCardResponse;
import com.youT.seenEar.domain.thanksCard.domain.ThanksCard;

import java.util.List;

public interface LoadThanksCardPort {

    List<ThanksCard> loadElderThanksCard(Member member);
}
