package com.youT.seenEar.domain.adviceCard.adapter.out.persistence.external.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdviceCardResponse {

    private Long id;

    private String text;

}
