package com.example.model1_article.repository;

import com.example.model1_article.DTO.ArticleDTO;

import java.util.List;

public interface ArticleRepository {
    public void register(ArticleDTO articleDTO);

    public List<ArticleDTO> showAll(int start, int recordsPerPage);


    public int articlesCount();

}
