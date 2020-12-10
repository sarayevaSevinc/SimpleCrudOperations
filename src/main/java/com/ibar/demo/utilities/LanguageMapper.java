package com.ibar.demo.utilities;

import com.ibar.demo.model.StaticVariable;

public class LanguageMapper {
    public static void chooseLang(String lang) {
        StaticVariable.lang = lang.equals("en") ? "en" :
                lang.equals("ru") ? "ru" :
                        lang.equals("az") ? "az" : "en";
    }
}
