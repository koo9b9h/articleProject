package com.koo.article_spring.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Slf4j
public class TimestampToStringConverter implements Converter<Timestamp, String> {

    @Override
    public String convert(Timestamp source) {
        String date = null;
        if (source != null && (!"".equals(source))) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = simpleDateFormat.format(source);
        }
        return date;
    }
}
