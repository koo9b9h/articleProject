package com.koo.article_spring.service;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.CategoryVO;

import java.util.List;

public interface RegisterService {
    public Integer getCategoryId(String categoryName);

    public void registerArticle(ArticleDTO articleDTO);
}
