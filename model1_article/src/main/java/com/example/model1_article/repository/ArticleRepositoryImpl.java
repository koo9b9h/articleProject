package com.example.model1_article.repository;

import com.example.model1_article.DAO.ArticleDAO;
import com.example.model1_article.DAO.ArticleDAOImpl;
import com.example.model1_article.DAO.CategoryDAO;
import com.example.model1_article.DAO.CategoryDAOImpl;
import com.example.model1_article.DTO.ArticleDTO;
import com.example.model1_article.DTO.SearchDTO;
import lombok.extern.log4j.Log4j2;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ArticleRepositoryImpl implements ArticleRepository {

    @Override
    public void register(ArticleDTO articleDTO) {
        ArticleDAO articleDAO = new ArticleDAOImpl();
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        Integer categoryId = categoryDAO.findCategoryId(articleDTO.getCategoryName());
        articleDAO.insertArticle(
                categoryId,
                articleDTO.getAuthor(),
                articleDTO.getPassword(),
                articleDTO.getTitle(),
                articleDTO.getContents()
        );
    }

    @Override
    public List<ArticleDTO> show(int start, int recordsPerPage, SearchDTO searchDTO) {
        ArticleDAO articleDAO = new ArticleDAOImpl();
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        CategoryDAO categoryDAO = new CategoryDAOImpl();

        Timestamp filterStart = searchDTO.getStartDate();
        Timestamp filterEnd = searchDTO.getEndDate();
        Integer filterCategory = categoryDAO.findCategoryId(searchDTO.getCategoryName());
        String filterSearch = searchDTO.getSearchTerm();


        for (Map<String, Object> article :
                articleDAO.selectAll(start, recordsPerPage, filterStart, filterEnd, filterCategory, filterSearch)) {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setTitle((String) article.get("title"));
            articleDTO.setContents((String) article.get("contents"));
            articleDTO.setAuthor((String) article.get("author"));
            articleDTO.setCreateTime((Timestamp) article.get("createTime"));
            articleDTO.setModifiedTime((Timestamp) article.get("modifiedTime"));
            articleDTO.setView((int) article.get("views"));
            articleDTO.setCategoryName(categoryDAO.findCategoryName((int) article.get("categoryId")));
            articleDTOList.add(articleDTO);
        }
        return articleDTOList;
    }

    @Override
    public Integer articlesCount(SearchDTO searchDTO) {
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        Timestamp filterStart = searchDTO.getStartDate();
        Timestamp filterEnd = searchDTO.getEndDate();
        Integer filterCategory = categoryDAO.findCategoryId(searchDTO.getCategoryName());
        String filterSearch = searchDTO.getSearchTerm();
        ArticleDAO articleDAO = new ArticleDAOImpl();


        return articleDAO.selectAllCount(filterStart, filterEnd, filterCategory, filterSearch);
    }
}
