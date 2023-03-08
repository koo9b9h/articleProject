package com.koo.article_spring.repository.mybatis;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
public interface ArticleMapper {

    Integer getArticleTotalCount(Pagination pagination) throws Exception;

    List<ArticleDTO> getArticleList(Pagination pagination) throws Exception;

    String getPassword(Integer articleId) throws Exception;

    void insertArticle(ArticleDTO articleDTO) throws Exception;

    @Cacheable(value = "article" ,key = "#articleId")
    ArticleDTO getArticle(Integer articleId) throws Exception;

    void updateViews(ArticleDTO articleDTO) throws Exception;

    @CacheEvict(value = "article")
    void updateArticle(ArticleDTO articleDTO) throws Exception;

    void deleteArticle(Integer articleId) throws Exception;
}
