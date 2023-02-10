package com.example.model1_article.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDTO {
    Integer crrentPage = 1;
    Integer recordsPerPage = 10;
    Integer start = (crrentPage - 1) * recordsPerPage;
    Integer articleTotalCount;
    Integer totalPages ;
}
