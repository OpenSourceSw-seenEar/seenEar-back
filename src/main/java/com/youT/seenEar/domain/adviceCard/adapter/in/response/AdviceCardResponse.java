package com.youT.seenEar.domain.adviceCard.adapter.in.response;

import com.youT.seenEar.domain.adviceCard.domain.ConcernType;
import com.youT.seenEar.domain.member.adapter.in.response.model.MemberModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdviceCardResponse {

    private String elderUuid;
    private String text;

    private ConcernType concernType;

}
