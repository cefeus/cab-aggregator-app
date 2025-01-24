package org.passengerservice.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiRoutesConstants {

    public final String PASSENGERS_API_V1 = "/api/v1/passengers";
    public final String FIND_ALL_ENDPOINT = "/all";
    public final String SET_PAYMENT_CASH_ENDPOINT = "/cash/{id}";
    public final String SET_PAYMENT_CARD_ENDPOINT = "/card/{id}";
    public final String UPDATE_PHONE_ENDPOINT = "/phone/{id}";
    public final String DELETE_ENDPOINT = "/{id}";
    public final String FIND_BY_ID_ENDPOINT = "/{id}";
    public final String UPDATE_ENDPOINT = "/{id}";
}
