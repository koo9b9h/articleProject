package com.example.model1_article.DAO;

import java.sql.SQLException;

public interface ArticleDAO extends ConnectionInformation {
    public void insertArticle(int category_id, String author, String password, String title , String contents);


}
