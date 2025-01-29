package com.modsen.passengerservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegexpConstants {

    public static final String PHONE_REGEXP = "^\\+375(17|29|33|44|25)[0-9]{3}[0-9]{2}[0-9]{2}$";

}
