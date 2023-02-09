package com.example.model1_article.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleDTO {
    private String categoryName;
    private String author;
    private String password;
    private String title;
    private String contents;
    private int view;
    private Timestamp create_time;
    private Timestamp modified_time;
}
