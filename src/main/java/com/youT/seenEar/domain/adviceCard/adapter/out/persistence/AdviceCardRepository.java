package com.youT.seenEar.domain.adviceCard.adapter.out.persistence;

import com.youT.seenEar.domain.adviceCard.domain.AdviceCard;
import com.youT.seenEar.domain.adviceCard.domain.AdviceType;
import com.youT.seenEar.domain.adviceCard.domain.ConcernType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdviceCardRepository extends JpaRepository<AdviceCard,Long> {
    Page<AdviceCard> findByConcernTypeAndAdviceType(ConcernType concernType, AdviceType adviceType, PageRequest of);

    Long countByConcernTypeAndAdviceType(ConcernType concernType, AdviceType adviceType);


}
