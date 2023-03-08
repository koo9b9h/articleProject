package com.koo.article_spring.service;

public interface DeleteService {
    Boolean isPasswordCheck(Integer articleId, String password) throws Exception;

    void deleteAllArticleInfo(Integer articleId) throws Exception;
}
