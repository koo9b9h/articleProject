package com.example.model1_article.repository;

import com.example.model1_article.DAO.CategoryDAO;
import com.example.model1_article.DAO.CategoryDAOImpl;
import com.example.model1_article.DTO.CategoryDTO;

public class CategoryRepositoryImpl implements CategoryRepository{
    @Override
    public CategoryDTO categoryNamesReturn() {
        CategoryDTO categoryDTO = new CategoryDTO();
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        categoryDTO.setCategoryNames(categoryDAO.findAllName());
        return categoryDTO;
    }
}
