package com.koo.article_spring.service;

import com.koo.article_spring.domain.CategoryVO;

import java.util.List;

public interface WriteService {
    public List<CategoryVO> getCategoryNames();
}
