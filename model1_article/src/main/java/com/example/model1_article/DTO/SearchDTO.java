package com.example.model1_article.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchDTO {
    Timestamp startDate;
    Timestamp endDate;
    String categoryName;
    String searchTerm;
}
