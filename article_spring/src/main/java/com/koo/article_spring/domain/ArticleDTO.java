package com.koo.article_spring.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ArticleDTO {
    private Integer articleId;  //PK.
    private String author;  //저자
    private String password;
    private String title;
    private String contents;
    private int views;
    private Timestamp createTime;
    private Timestamp modifiedTime;
    private Integer categoryId;
    private String categoryName;
}
