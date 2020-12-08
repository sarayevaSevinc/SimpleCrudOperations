package com.ibar.demo.utilities;

import com.ibar.demo.model.StaticVariable;

public class ErrorMapper {
    public static String getUserNotFoundByIdError(){
        switch (StaticVariable.lang) {
            case "az" :
                return StaticVariable.az_USER_NOT_FOUND_WITH_ID;
            case "en":
                return StaticVariable.en_USER_NOT_FOUND_WITH_ID;
            case "ru":
                return StaticVariable.ru_USER_NOT_FOUND_WITH_ID;
        }
        return StaticVariable.en_USER_NOT_FOUND_WITH_ID;
    }

    public static String getPhoneNumberNotFoundWithIDError(){
        switch (StaticVariable.lang) {
            case "az" :
                return StaticVariable.az_PHONE_NOT_FOUND_WITH_ID;
            case "en":
                return StaticVariable.en_PHONE_NOT_FOUND_WITH_ID;
            case "ru":
                return StaticVariable.ru_PHONE_NOT_FOUND_WITH_ID;
        }
        return StaticVariable.en_PHONE_NOT_FOUND_WITH_ID;
    }

    public static String getUserNotFoundByNameError(){
        switch (StaticVariable.lang) {
            case "az" :
                return StaticVariable.az_USER_NOT_FOUND_WITH_NAME;
            case "en":
                return StaticVariable.en_USER_NOT_FOUND_WITH_NAME;
            case "ru":
                return StaticVariable.ru_USER_NOT_FOUND_WITH_NAME;
        }
        return StaticVariable.en_USER_NOT_FOUND_WITH_NAME;
    }
}
