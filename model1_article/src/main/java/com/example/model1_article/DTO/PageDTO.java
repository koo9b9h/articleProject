package com.example.model1_article.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDTO {
    static int beforePage = 0;
    int crrentPage = 1;
    int recordsPerPage = 10;
    int start = (crrentPage - 1) * recordsPerPage;
    int articleTotalCount;
    int totalPages ;
}
