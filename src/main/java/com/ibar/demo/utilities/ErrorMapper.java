package com.ibar.demo.utilities;

import static com.ibar.demo.model.StaticVariable.PHONE_NOT_FOUND_WITH_ID;
import static com.ibar.demo.model.StaticVariable.PHOTO_NOT_FOUND_WITH_ID;
import static com.ibar.demo.model.StaticVariable.USER_NOT_FOUND_WITH_ID;
import static com.ibar.demo.model.StaticVariable.USER_NOT_FOUND_WITH_NAME;
import static com.ibar.demo.model.StaticVariable.lang;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Log4j2
public class ErrorMapper {

    private static Translator translator;

    public ErrorMapper(Translator translator) {
        this.translator = translator;
    }

    public static String getUserNotFoundByIdError() {
        log.info(lang);
        String translate = translator.translate(USER_NOT_FOUND_WITH_ID, lang);
        log.info(translate);
        return translate;

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
}
