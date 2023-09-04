package com.youT.seenEar.domain.adviceCard.adapter.in.response;

import com.youT.seenEar.domain.adviceCard.domain.ConcernType;
import com.youT.seenEar.domain.member.domain.MemberType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class YouthAdviceCardResponse {

    private Long adviceCardId;

    private String text;

    private ConcernType concernType;

    private MemberType memberType;

    private String advisorName;


}
