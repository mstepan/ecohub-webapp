package com.github.kostia.ecohub.flows.employee;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@ReadingConverter
public class StringToDateConverter implements Converter<String, Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String str) {
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format", e);
        }
    }
}
