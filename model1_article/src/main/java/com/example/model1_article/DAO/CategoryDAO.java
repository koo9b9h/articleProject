package com.example.model1_article.DAO;

import java.util.List;

public interface CategoryDAO extends ConnectionInformation{
    public Integer findCategoryId(String categoryName);
    public String findCategoryName(int categoryId);
    public List<String> findAllName();
}
