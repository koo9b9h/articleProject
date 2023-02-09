package com.example.model1_article.DAO;


import lombok.Getter;

import java.sql.*;

public class ArticleDAOImpl implements ArticleDAO {

    private int categoryId;
    private String author;
    private String password;
    private String title;
    private String contents;
    private Timestamp create_time;
    private Timestamp modified_time;
    private int view;

    public ArticleDAOImpl() {
        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
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

            try(conn;pstmt){
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

}// end class
