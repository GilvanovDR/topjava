package ru.javawebinar.topjava.web;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class toLocalDate implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {if (source == "") {
        return null;
    } else
    return LocalDate.parse(source);
    }
}
