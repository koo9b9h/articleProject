package com.koo.article_spring.repository.mybatis;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.PageDTO;
import com.koo.article_spring.domain.SearchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleListMapper {

    public abstract Integer getArticleTotalCount(SearchDTO searchDTO) throws Exception;

    public abstract List<ArticleDTO> getArticleList(@Param("searchDTO") SearchDTO searchDTO, @Param("pageDTO") PageDTO pageDTO) throws Exception;
}
