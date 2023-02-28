package com.koo.article_spring.service;

import com.koo.article_spring.domain.ArticleDTO;


public interface RegisterService {
    public void registerArticle(ArticleDTO articleDTO) throws Exception;
}
