package com.example.model1_article.repository;

import com.example.model1_article.DAO.ArticleDAO;
import com.example.model1_article.DAO.ArticleDAOImpl;
import com.example.model1_article.DAO.CategoryDAO;
import com.example.model1_article.DAO.CategoryDAOImpl;
import com.example.model1_article.DTO.RegisterRequiredDTO;

import java.sql.SQLException;

public class ArticleRepositoryImpl implements ArticleRepository{

    @Override
    public void register(RegisterRequiredDTO registerRequiredDTO) {
        ArticleDAO articleDAO = new ArticleDAOImpl();
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        int categoryId = categoryDAO.findCategoryId(registerRequiredDTO.getCategoryName());
        articleDAO.insertArticle(
                categoryId,
                registerRequiredDTO.getAuthor(),
                registerRequiredDTO.getPassword(),
                registerRequiredDTO.getTitle(),
                registerRequiredDTO.getContents()
        );
    }
}
