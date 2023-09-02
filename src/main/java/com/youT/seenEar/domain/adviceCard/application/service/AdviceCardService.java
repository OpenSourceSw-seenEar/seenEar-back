package com.youT.seenEar.domain.adviceCard.application.service;

import com.youT.seenEar.domain.adviceCard.adapter.in.response.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.application.port.in.AdviceCardUseCase;
import com.youT.seenEar.domain.adviceCard.application.port.out.LoadAdviceCardPort;
import com.youT.seenEar.domain.adviceCard.application.port.out.SaveAdviceCardPort;
import com.youT.seenEar.domain.adviceCard.domain.AdviceType;
import com.youT.seenEar.domain.adviceCard.domain.ConcernType;
import com.youT.seenEar.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdviceCardService implements AdviceCardUseCase{

    private final LoadAdviceCardPort loadAdviceCardPort;

    private final SaveAdviceCardPort saveAdviceCardPort;
    @Override
    public AdviceCardResponse loadAdviceResponse(ConcernType concernType, AdviceType adviceType, Member member) {
        return loadAdviceCardPort.loadAdviceCard(concernType,adviceType,member);
    }

}
