package com.koo.article_spring.domain;

import lombok.Data;

@Data
public class PageDTO {
    Integer crrentPage = 1;
    Integer recordsPerPage = 10;
    Integer start = (crrentPage - 1) * recordsPerPage;
    Integer articleTotalCount;
    Integer totalPages ;
}
