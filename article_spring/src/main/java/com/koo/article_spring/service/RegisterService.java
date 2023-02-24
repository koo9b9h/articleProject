package com.koo.article_spring.service;

import com.koo.article_spring.domain.ArticleDTO;


public interface RegisterService {
    public Integer getCategoryId(String categoryName) throws Exception;

    public void registerArticle(ArticleDTO articleDTO) throws Exception;
}
