package com.youT.seenEar.domain.adviceCard.application.port.in;

import com.youT.seenEar.domain.adviceCard.adapter.in.request.AdviceCardSortRequest;
import com.youT.seenEar.domain.adviceCard.adapter.in.response.AdviceCardResponse;

import java.io.UnsupportedEncodingException;

public interface ChatGptUseCase {

    AdviceCardResponse sortAdviceType(Long id) throws UnsupportedEncodingException;
}
