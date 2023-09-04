package com.youT.seenEar.domain.thanksCard.adapter.out.persistence;

import com.youT.seenEar.domain.member.domain.Member;
import com.youT.seenEar.domain.thanksCard.domain.ThanksCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThanksCardRepository extends JpaRepository<ThanksCard,Long> {

    List<ThanksCard> findByThanksElder(Member member);
}
