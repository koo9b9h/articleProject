package com.koo.article_spring.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
public class StringToTimestampConverter implements Converter<String, Timestamp> {

    @Override
    public Timestamp convert(String source) {
        Timestamp date = null;

        if (source != null && (!"".equals(source))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = new Timestamp(dateFormat.parse(source).getTime());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return date;
    }
}
