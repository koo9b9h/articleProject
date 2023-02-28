package com.koo.article_spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {

    private Timestamp startDate;
    private Timestamp endDate;
    private Integer categoryId;
    private String searchTerm;
    private String categoryName;
}
