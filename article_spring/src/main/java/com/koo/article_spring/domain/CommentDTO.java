package com.koo.article_spring.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDTO {
    private Integer commentId;
    private String contents;
    private Timestamp createTime;
    private Integer articleId;
}
