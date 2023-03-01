package com.koo.article_spring.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Pagination {

    // 페이징 관련 속성
    private Integer currentPage = 1;
    private Integer recordsPerPage = 10;
    private Integer start = (currentPage - 1) * recordsPerPage;
    private Integer articleTotalCount;
    private Integer totalPages ;
    // 검색 조건
    private Timestamp startDate;
    private Timestamp endDate;
    private Integer categoryId;
    private String searchTerm;
    private String categoryName;

}
