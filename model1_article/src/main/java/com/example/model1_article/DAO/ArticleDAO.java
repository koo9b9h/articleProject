package com.example.model1_article.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ArticleDAO extends ConnectionInformation {
    public void insertArticle(int category_id, String author, String password, String title , String contents);

    public List<Map<String,Object>> selectAll(int start,int recordsPerPage);

    public int selectAllCount();

}
