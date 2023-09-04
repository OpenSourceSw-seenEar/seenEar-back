package com.youT.seenEar.domain.thanksCard.adapter.out.persistence;

import com.youT.seenEar.domain.thanksCard.domain.ThanksCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThanksCardRepository extends JpaRepository<ThanksCard,Long> {
}
