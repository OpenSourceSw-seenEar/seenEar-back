package com.youT.seenEar.domain.adviceCard.domain;

import com.youT.seenEar.domain.member.domain.Member;
import com.youT.seenEar.global.utils.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Table(name = "YouthAdviceMapping")
public class YouthAdviceMapping extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    // [연관관계] 청년 (N) : 조언 (M)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adviceRecipient")
    private Member adviceRecipient;
    // [연관관계] 청년 (N) : 조언 (M)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adviceCard")
    private AdviceCard adviceCard;

}
