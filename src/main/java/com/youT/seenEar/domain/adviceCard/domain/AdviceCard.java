package com.youT.seenEar.domain.adviceCard.domain;

import com.youT.seenEar.domain.member.domain.Member;
import com.youT.seenEar.domain.member.domain.MemberType;
import com.youT.seenEar.global.utils.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "AdviceCard")
public class AdviceCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "adviceType")
    private AdviceType adviceType;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "concernType")
    private ConcernType concernType;
    @Lob
    @Column(name = "text")
    private String text;

    // [연관관계] 노인 (1) : 조언 (N)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "advisorId")
    private Member advisor;
    // [연관관계] 청년 (N) : 조언 (M)
    @OneToMany(mappedBy = "adviceCard")
    List<YouthAdviceMapping> youthAdviceMappingList=new ArrayList<>();

    public void updateConcernType(ConcernType concernType){

        this.concernType=concernType;
    }


}
