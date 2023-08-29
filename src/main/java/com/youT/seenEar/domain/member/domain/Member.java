package com.youT.seenEar.domain.member.domain;

import com.youT.seenEar.domain.adviceCard.domain.AdviceCard;
import com.youT.seenEar.domain.adviceCard.domain.YouthAdviceMapping;
import com.youT.seenEar.domain.thanksCard.domain.ThanksCard;
import com.youT.seenEar.global.utils.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "Member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "memberType")
    private MemberType memberType;
    @Column(name = "uuid")
    private String uuid;
    // [연관관계] 청년 (1) : 감사카드 (N) : 노인(1)
    @OneToMany(mappedBy = "thanksYouth")
    List<ThanksCard> youthSendThanksCards=new ArrayList<>();
    // [연관관계] 청년 (1) : 감사카드 (N) : 노인(1)
    @OneToMany(mappedBy = "thanksElder")
    List<ThanksCard> elderReceiveThanksCards=new ArrayList<>();
    // [연관관계] 청년 (1) : 조언 (N) : 노인(1)
    @OneToMany(mappedBy = "advisor")
    List<AdviceCard> elderSendAdviceCards=new ArrayList<>();
    // [연관관계] 청년 (N) : 조언 (M)
    @OneToMany(mappedBy = "adviceRecipient")
    List<YouthAdviceMapping> youthAdviceMappingList=new ArrayList<>();

}
