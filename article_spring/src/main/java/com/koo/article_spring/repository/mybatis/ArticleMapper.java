package com.koo.article_spring.repository.mybatis;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.Pagination;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    Integer getArticleTotalCount(Pagination pagination) throws Exception;

    List<ArticleDTO> getArticleList(Pagination pagination) throws Exception;

    void insertArticle(ArticleDTO articleDTO) throws Exception;
}
