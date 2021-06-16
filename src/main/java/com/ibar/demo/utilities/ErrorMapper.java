package com.ibar.demo.utilities;

import static com.ibar.demo.constants.StaticVariable.ID_DOES_NOT_EQUAL;
import static com.ibar.demo.constants.StaticVariable.OTP_NOT_FOUND;
import static com.ibar.demo.constants.StaticVariable.PHONE_NOT_FOUND_WITH_ID;
import static com.ibar.demo.constants.StaticVariable.PHOTO_NOT_FOUND_WITH_ID;
import static com.ibar.demo.constants.StaticVariable.USER_NOT_FOUND_WITH_ID;
import static com.ibar.demo.constants.StaticVariable.USER_NOT_FOUND_WITH_NAME;
import static com.ibar.demo.constants.StaticVariable.lang;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ErrorMapper {

    private static Translator translator;

    public ErrorMapper(Translator translator) {
        this.translator = translator;
    }

    public static String getUserNotFoundByIdError() {
        return translator.translate(USER_NOT_FOUND_WITH_ID, lang);

    }
    public static String getIdDoesNotEqualError(){
        return translator.translate(ID_DOES_NOT_EQUAL, lang);
    }

    public static  String getPhoneNumberNotFoundWithIDError() {
        return translator.translate(PHONE_NOT_FOUND_WITH_ID, lang);
    }

    public static String getUserNotFoundByNameError() {
        return translator.translate(USER_NOT_FOUND_WITH_NAME, lang);
    }

    public static String getProfilePhotoNotFoundByIdError() {
        return translator.translate(PHOTO_NOT_FOUND_WITH_ID, lang);
    }
    public static String getOtpVerificationError() {
        return translator.translate(OTP_NOT_FOUND, lang);
    }
}
