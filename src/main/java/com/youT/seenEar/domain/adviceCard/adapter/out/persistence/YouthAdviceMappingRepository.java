package com.youT.seenEar.domain.adviceCard.adapter.out.persistence;

import com.youT.seenEar.domain.adviceCard.domain.YouthAdviceMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YouthAdviceMappingRepository extends JpaRepository<YouthAdviceMapping,Long> {
}
