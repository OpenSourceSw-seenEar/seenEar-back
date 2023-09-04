package com.youT.seenEar.domain.thanksCard.adapter.in.response;

import com.youT.seenEar.domain.thanksCard.domain.OpenStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ElderThanksCardResponse {

    private Long thanksCardId;

    private String youthName;

    private String text;

    private String speechUrl;

    private OpenStatus openStatus;

}
