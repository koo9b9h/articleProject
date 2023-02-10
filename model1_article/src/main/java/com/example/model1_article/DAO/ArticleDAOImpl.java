package com.example.model1_article.DAO;


import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ArticleDAOImpl implements ArticleDAO {

    public ArticleDAOImpl() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    // 자원 연결과 해제에 대해서 고민해봤는데 코드가 길어지더라도 한 메소드에 한번씩 연결하고 닫는 것이 안정적이라고 판담함.
    @Override
    public void insertArticle(int categoryId, String author, String password, String title, String contents) {
        Timestamp insertTime = new Timestamp(System.currentTimeMillis());

        String query = "INSERT INTO articles (category_id, author, password, title, contents, create_time, modified_time ) " +
                "VALUES (?, ?, ?, ?, ?, ? ,?)";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query);

            try (conn; pstmt) {
                pstmt.setInt(1, categoryId);
                pstmt.setString(2, author);
                pstmt.setString(3, password);
                pstmt.setString(4, title);
                pstmt.setString(5, contents);
                pstmt.setTimestamp(6, insertTime);
                pstmt.setTimestamp(7, insertTime);
                pstmt.executeUpdate();

            }// try-with-resources
        } catch (SQLException e) {
            e.printStackTrace();
        } //try-catch
    }//insertArticle

    // DTO를 쓰면 코드가 줄지만 이 디렉토리 범위 내에서 DTO가 관여되지 않게 하려고 함.
    // 보여주는 기능과 검색 조건 기능까지 있어서 기능이 분리를 할 수 있다면 분리하면 좋을까 고민
    public List<Map<String, Object>> selectAll(int start, int recordsParPage,
                                               Timestamp startDate, Timestamp endDate, Integer categoryId, String searchTerm) {

        List<Map<String, Object>> articles = new ArrayList<>();
        String categoryQuery = "";
        String searchQuery = "";
        String dateQuery = "";
        int qeuryIndex = 0;

        if (categoryId != null) {
            categoryQuery = "AND category_id = ? ";
        }
        if (searchTerm != null && searchTerm != "") {
            searchQuery = "AND (author LIKE '%' + ? + '%' OR title LIKE '%' + ? + '%' OR contents LIKE '%' + ? + '%')";
        }
        if (startDate != null && endDate != null) {
            dateQuery = "AND create_time BETWEEN ? AND ? ";
        }
        String query = "SELECT title, contents, views, create_time, modified_time, author, category_id "
                + "FROM articles "
                + "WHERE (1=1)"
                + categoryQuery
                + searchQuery
                + dateQuery
                + "LIMIT ?,?";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query);

            if (categoryId != null) {
                pstmt.setInt(1, categoryId);
                qeuryIndex += 1;
            }
            if (searchTerm != null && searchTerm != "") {
                pstmt.setString(1 + qeuryIndex, '%' + searchTerm + '%');
                pstmt.setString(2 + qeuryIndex, '%' + searchTerm + '%');
                pstmt.setString(3 + qeuryIndex, '%' + searchTerm + '%');
                qeuryIndex += 3;
            }
            if (startDate != null && endDate != null) {
                pstmt.setTimestamp(1 + qeuryIndex, startDate);
                pstmt.setTimestamp(2 + qeuryIndex, endDate);
                qeuryIndex += 2;
            }
            pstmt.setInt(1 + qeuryIndex, start);
            pstmt.setInt(2 + qeuryIndex, recordsParPage);

            ResultSet rs = pstmt.executeQuery();

            try (conn; pstmt; rs) {

                while (rs.next()) {
                    Map<String, Object> article = new HashMap<>();
                    article.put("title", rs.getString("title"));
                    article.put("contents", rs.getString("contents"));
                    article.put("author", rs.getString("author"));
                    article.put("views", rs.getInt("views"));
                    article.put("createTime", rs.getTimestamp("create_time"));
                    article.put("modifiedTime", rs.getTimestamp("modified_time"));
                    article.put("categoryId", rs.getInt("category_id"));
                    articles.add(article);
                }
            }// try-with-resources
        } catch (Exception e) {
            e.printStackTrace();
        } //try-catch
        return articles;
    }

    // JDBC sql 틀린거 잡아내느라 시간 다 씀
    @Override
    public Integer selectAllCount(Timestamp startDate, Timestamp endDate, Integer categoryId, String searchTerm) {
        Integer totalcount = 0;
        String categoryQuery = "";
        String searchQuery = "";
        String dateQuery = "";
        int qeuryIndex = 0;

        if (categoryId != null) {
            categoryQuery = "AND category_id = ? ";
        }
        if (searchTerm != null && searchTerm != "") {
            searchQuery = "AND (author LIKE '%' + ? + '%' OR title LIKE '%' + ? + '%' OR contents LIKE '%' + ? + '%')";
        }
        if (startDate != null && endDate != null) {
            dateQuery = "AND create_time BETWEEN ? AND ? ";
        }

        String query = "SELECT COUNT(*) "
                + "FROM articles "
                + "WHERE (1=1)"
                + categoryQuery
                + searchQuery
                + dateQuery;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query);

            if (categoryId != null) {
                pstmt.setInt(1 + qeuryIndex, categoryId);
                qeuryIndex += 1;
            }
            if (searchTerm != null && searchTerm != "") {
                pstmt.setString(1 + qeuryIndex, '%' + searchTerm + '%');
                pstmt.setString(2 + qeuryIndex, '%' + searchTerm + '%');
                pstmt.setString(3 + qeuryIndex, '%' + searchTerm + '%');
                qeuryIndex += 3;
            }
            if (startDate != null && endDate != null) {
                pstmt.setTimestamp(1 + qeuryIndex, startDate);
                pstmt.setTimestamp(2 + qeuryIndex, endDate);
            }

            ResultSet rs = pstmt.executeQuery();

            try (conn; pstmt; rs) {
                rs.next();
                totalcount = rs.getInt(1);
            }// try-with-resources
        } catch (Exception e) {
            e.printStackTrace();
        } //try-catch

        return totalcount;
    }

}//end class
