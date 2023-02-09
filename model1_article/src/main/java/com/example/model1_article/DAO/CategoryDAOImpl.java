package com.example.model1_article.DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDAOImpl implements CategoryDAO {


    public CategoryDAOImpl(){
        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    // 데이터가 아주 적고 단순한 테이블이어서 select로 데이터를 다 뽑고 Map 으로 진행. 많을 경우 각각마다 DB WHEHE조건 적용
    public Map<Integer,String> selectAll(){
        String query = "SELECT * FROM categorys";
        Map<Integer,String> category = new HashMap<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();;

            try(conn;pstmt;rs){
                while(rs.next()){
                    category.put(rs.getInt("category_id"),rs.getString("category_name"));
                }
            }// try-with-resources
        } catch (SQLException e) {
            e.printStackTrace();
        } //try-catch
        return category;
    }

    public int findCategoryId(String categoryName){
        Map<Integer,String> category = selectAll();
        int category_id = -1;
        for(Map.Entry<Integer, String> entry : category.entrySet()){
            if(entry.getValue().equals(categoryName)){
                category_id = entry.getKey();
            }
        }
        return category_id;
    }

    public String findCategoryName(int categoryId){
        Map<Integer,String> category = selectAll();
        return category.get(categoryId);
    }

    public List<String> findAllName(){
        Map<Integer,String> category = selectAll();
        List<String> categoryNames = new ArrayList<>();
        for(Map.Entry<Integer, String> entry : category.entrySet()){
            categoryNames.add(entry.getValue());
        }
        return categoryNames;
    }
}
