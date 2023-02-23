package com.koo.article_spring.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ArticleDTO {
    private String categoryName;
    private String author;
    private String password;
    private String title;
    private String contents;
    private int view;
    private Timestamp createTime;
    private Timestamp modifiedTime;
    private Integer categoryId;
}
