package com.example.model1_article.repository;

import com.example.model1_article.DAO.ArticleDAO;
import com.example.model1_article.DAO.ArticleDAOImpl;
import com.example.model1_article.DAO.CategoryDAO;
import com.example.model1_article.DAO.CategoryDAOImpl;
import com.example.model1_article.DTO.ArticleDTO;

public class ArticleRepositoryImpl implements ArticleRepository{

    @Override
    public void register(ArticleDTO articleDTO) {
        ArticleDAO articleDAO = new ArticleDAOImpl();
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        int categoryId = categoryDAO.findCategoryId(articleDTO.getCategoryName());
        articleDAO.insertArticle(
                categoryId,
                articleDTO.getAuthor(),
                articleDTO.getPassword(),
                articleDTO.getTitle(),
                articleDTO.getContents()
        );
    }
}
