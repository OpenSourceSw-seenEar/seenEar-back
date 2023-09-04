package com.youT.seenEar.domain.thanksCard.application.port.out;

import com.youT.seenEar.domain.thanksCard.domain.ThanksCard;

public interface SaveThanksCardPort {

    void saveThanksCardText(ThanksCard thanksCard);
}
