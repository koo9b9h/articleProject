package com.example.model1_article.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

interface ConnectionInformation {
    final String DRIVER = "org.mariadb.jdbc.Driver";
    final String DB_IP = "127.0.0.1";
    final String DB_PORT = "3306";
    final String DB_NAME = "study_model1";
    final String DB_URL =
            "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
    final String DB_USERNAME = "model1";
    final String DB_PASSWORD = "model1";
}
