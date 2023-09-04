package com.youT.seenEar.domain.thanksCard.adapter.in.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThanksCardResponse {

    private String text;

    private String speechUrl;

}
