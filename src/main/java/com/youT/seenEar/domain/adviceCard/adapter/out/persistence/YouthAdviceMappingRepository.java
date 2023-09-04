package com.youT.seenEar.domain.adviceCard.adapter.out.persistence;

import com.youT.seenEar.domain.adviceCard.domain.YouthAdviceMapping;
import com.youT.seenEar.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YouthAdviceMappingRepository extends JpaRepository<YouthAdviceMapping,Long> {

    List<YouthAdviceMapping> findByAdviceRecipient(Member member);
}
