package com.ibar.demo.utilities;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Translator {
    private final MessageSource messageSource;

    public String translate(String code, String locale) {
        try {
            return messageSource.getMessage(code, null, Locale.forLanguageTag(locale));
        } catch (NoSuchMessageException ex) {
            return "Unknown";
        }
    }
}
