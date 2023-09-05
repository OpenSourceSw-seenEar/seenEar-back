package com.youT.seenEar.domain.adviceCard.adapter.out.persistence;

import com.youT.seenEar.domain.adviceCard.domain.AdviceCard;
import com.youT.seenEar.domain.adviceCard.domain.AdviceType;
import com.youT.seenEar.domain.adviceCard.domain.ConcernType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AdviceCardRepository extends JpaRepository<AdviceCard,Long> {
    Page<AdviceCard> findByConcernTypeAndAdviceType(ConcernType concernType, AdviceType adviceType, PageRequest of);

    Long countByConcernTypeAndAdviceType(ConcernType concernType, AdviceType adviceType);

    AdviceCard findAdviceCardById(Long id);

    @Modifying
    @Transactional
    @Query("update AdviceCard  set concernType = :concernType where id = :id")
    void updateConcernType(@Param(value = "concernType") ConcernType concernType,@Param(value = "id") Long id);

}
