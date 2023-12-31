package com.youT.seenEar.domain.thanksCard.domain;

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
@AllArgsConstructor
@Builder
@Table(name = "ThanksCard")
public class ThanksCard extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "speechUrl")
    private String speechUrl;
    @Column(name = "text")
    private String text;

    @Enumerated(value = EnumType.STRING)
    private OpenStatus openStatus = OpenStatus.valueOf(OpenStatus.OPEN.toString());

    // [연관관계] 청년 (1) : 감사카드 (N) : 노인(1)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "thanksYouthId")
    private Member thanksYouth;
    // [연관관계] 청년 (1) : 감사카드 (N) : 노인(1)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "thanksElderId")
    private Member thanksElder;

    public void updateOpenStatus(OpenStatus openStatus){
        this.openStatus = openStatus;
    }

}
