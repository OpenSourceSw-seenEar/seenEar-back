package com.youT.seenEar.domain.thanksCard.adapter.out.persistence;


import com.youT.seenEar.domain.member.domain.Member;
import com.youT.seenEar.domain.thanksCard.adapter.in.response.ElderThanksCardResponse;
import com.youT.seenEar.domain.thanksCard.application.port.out.LoadThanksCardPort;
import com.youT.seenEar.domain.thanksCard.application.port.out.SaveThanksCardPort;
import com.youT.seenEar.domain.thanksCard.domain.ThanksCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ThanksCardPersistenceAdapter implements LoadThanksCardPort, SaveThanksCardPort {

    private final ThanksCardRepository thanksCardRepository;
    @Override
    @Transactional
    public void saveThanksCardText(ThanksCard thanksCard) {

       thanksCardRepository.saveAndFlush(thanksCard);

    }


    @Override
    public List<ThanksCard> loadElderThanksCard(Member member) {
        return thanksCardRepository.findByThanksElder(member);
    }
}
