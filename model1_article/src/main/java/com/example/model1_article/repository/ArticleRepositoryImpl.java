package com.example.model1_article.repository;

import com.example.model1_article.DAO.ArticleDAO;
import com.example.model1_article.DAO.ArticleDAOImpl;
import com.example.model1_article.DAO.CategoryDAO;
import com.example.model1_article.DAO.CategoryDAOImpl;
import com.example.model1_article.DTO.ArticleDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<ArticleDTO> showAll(int start, int recordsPerPage) {
        ArticleDAO articleDAO = new ArticleDAOImpl();
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        CategoryDAO categoryDAO = new CategoryDAOImpl();

        for(Map<String,Object> article:articleDAO.selectAll(start,recordsPerPage)){
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setTitle((String) article.get("title"));
            articleDTO.setContents((String) article.get("contents"));
            articleDTO.setAuthor((String)article.get("author"));
            articleDTO.setCreateTime((Timestamp) article.get("createTime"));
            articleDTO.setModifiedTime((Timestamp) article.get("modifiedTime"));
            articleDTO.setView((int)article.get("views"));
            articleDTO.setCategoryName(categoryDAO.findCategoryName((int) article.get("categoryId")));
            articleDTOList.add(articleDTO);
        }

        return articleDTOList;
    }

    @Override
    public int articlesCount() {
        ArticleDAO articleDAO = new ArticleDAOImpl();
        return articleDAO.selectAllCount();
    }
}
