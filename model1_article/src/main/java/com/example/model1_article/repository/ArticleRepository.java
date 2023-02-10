package com.example.model1_article.repository;

import com.example.model1_article.DTO.ArticleDTO;
import com.example.model1_article.DTO.SearchDTO;

import java.util.List;

public interface ArticleRepository {
    public void register(ArticleDTO articleDTO);

    public List<ArticleDTO> show(int start, int recordsPerPage, SearchDTO searchDTO);


    public Integer articlesCount(SearchDTO searchDTO);

}
