package com.whytecreations.ecom.utils;

/**
 * @author Rishad
 * @since 07/04/2020
 */

public final class Constants {

    private Constants() {

    }

    // Languages
    public static final String LANG_ARABIC = "ar";
    public static final String LANG_ENGLISH = "en";


    public enum ApiError{
        SERVER_ERROR, NETWORK_ISSUE, SERVICE_FAILED, RESPONSE_FAILED
    }

    public enum LoginDataError{
        EMPTY_MOBILE_NO, EMPTY_PASSWORD, VALID
    }

}
