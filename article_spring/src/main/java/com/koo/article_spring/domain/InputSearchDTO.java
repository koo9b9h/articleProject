package com.koo.article_spring.domain;

import lombok.Data;

@Data
public class InputSearchDTO {
    String searchTerm;
    String startDate;
    String endDate;
    String category;
}
