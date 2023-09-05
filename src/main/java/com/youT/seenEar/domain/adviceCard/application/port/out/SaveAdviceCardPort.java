package com.youT.seenEar.domain.adviceCard.application.port.out;

import com.youT.seenEar.domain.adviceCard.domain.AdviceCard;

public interface SaveAdviceCardPort {

    AdviceCard saveElderAdviceCard(AdviceCard adviceCard);

}