package com.koo.article_spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {
    Timestamp startDate;
    Timestamp endDate;
    Integer categoryId;
    String searchTerm;
}
