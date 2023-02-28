package com.koo.article_spring.service;


import com.koo.article_spring.domain.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getCategoryAll() throws Exception;

    Integer getCategoryId(String categoryName) throws Exception;

    String getCategoryName(Integer categoryId) throws Exception;
}
