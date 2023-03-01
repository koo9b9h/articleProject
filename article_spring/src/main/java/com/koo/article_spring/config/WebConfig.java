package com.koo.article_spring.config;

import com.koo.article_spring.converter.StringToTimestampConverter;
import com.koo.article_spring.converter.TimestampToStringConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addConverter(new StringToTimestampConverter());
        registry.addConverter(new TimestampToStringConverter());
    }


}
