package com.koo.article_spring.domain;

import lombok.Data;

@Data
public class PageDTO {
    private Integer crrentPage = 1;
    private Integer recordsPerPage = 10;
    private Integer start = (crrentPage - 1) * recordsPerPage;
    private Integer articleTotalCount;
    private Integer totalPages ;
}
