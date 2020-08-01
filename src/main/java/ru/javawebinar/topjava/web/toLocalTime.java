package ru.javawebinar.topjava.web;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalTime;

public class toLocalTime implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(String source) {
        if (source == "") {
            return null;
        } else return LocalTime.parse(source);
    }
}
