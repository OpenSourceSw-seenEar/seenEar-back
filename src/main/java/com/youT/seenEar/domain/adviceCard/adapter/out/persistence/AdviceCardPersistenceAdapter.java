package com.youT.seenEar.domain.adviceCard.adapter.out.persistence;

import com.youT.seenEar.domain.adviceCard.adapter.in.response.AdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.adapter.in.response.YouthAdviceCardResponse;
import com.youT.seenEar.domain.adviceCard.application.port.out.LoadAdviceCardPort;
import com.youT.seenEar.domain.adviceCard.application.port.out.SaveAdviceCardPort;
import com.youT.seenEar.domain.adviceCard.domain.AdviceCard;
import com.youT.seenEar.domain.adviceCard.domain.AdviceType;
import com.youT.seenEar.domain.adviceCard.domain.ConcernType;
import com.youT.seenEar.domain.adviceCard.domain.YouthAdviceMapping;
import com.youT.seenEar.domain.member.domain.Member;
import com.youT.seenEar.global.exception.BaseException;
import com.youT.seenEar.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
@Transactional
public class AdviceCardPersistenceAdapter implements SaveAdviceCardPort, LoadAdviceCardPort {

    private final AdviceCardRepository adviceCardRepository;

    private  final YouthAdviceMappingRepository youthAdviceMappingRepository;
    @Override
    public AdviceCardResponse loadAdviceCard(ConcernType concernType, AdviceType adviceType, Member member) {

        // 조건에 맞는 게시물 개수 가져옴
        Long qty= adviceCardRepository.countByConcernTypeAndAdviceType(concernType,adviceType);
        // 랜덤하게 하나 뽑기
        int idx = (int)(Math.random() * qty);
        Page<AdviceCard> adviceCard = adviceCardRepository.findByConcernTypeAndAdviceType(concernType,adviceType, PageRequest.of(idx,1));

        AdviceCard randomAdviceCard=adviceCard.getContent().get(0);
        YouthAdviceMapping youthAdviceMapping = YouthAdviceMapping.builder()
                .adviceRecipient(member)
                .adviceCard(randomAdviceCard).build();
        youthAdviceMappingRepository.saveAndFlush(youthAdviceMapping);
        return AdviceCardResponse.builder()
                .elderUuid(randomAdviceCard.getAdvisor().getUuid())
                .text(randomAdviceCard.getText()).build();
    }

    @Override
    public List<YouthAdviceCardResponse> getAdviceCardList(Member member) {
        List<YouthAdviceMapping> youthAdviceMappingList = youthAdviceMappingRepository.findByAdviceRecipient(member);
        return  youthAdviceMappingList.stream().map(youthAdviceMapping ->
                YouthAdviceCardResponse.builder()
                        .advisorName(youthAdviceMapping.getAdviceCard().getAdvisor().getName())
                        .adviceCardId(youthAdviceMapping.getAdviceCard().getId())
                        .concernType(youthAdviceMapping.getAdviceCard().getConcernType())
                        .text(youthAdviceMapping.getAdviceCard().getText())
                        .memberType(youthAdviceMapping.getAdviceCard().getAdvisor().getMemberType())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public AdviceCard findById(Long id) {
        return adviceCardRepository.findAdviceCardById(id);
    }


    @Override
    @Transactional
    public AdviceCard saveElderAdviceCard(AdviceCard adviceCard) {

        return adviceCardRepository.saveAndFlush(adviceCard);
    }

}