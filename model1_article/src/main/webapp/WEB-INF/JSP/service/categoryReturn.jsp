<%@ page import="com.example.model1_article.repository.CategoryRepository" %>
<%@ page import="com.example.model1_article.repository.CategoryRepositoryImpl" %>
<%@ page import="com.example.model1_article.DTO.CategoryDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    CategoryDTO categoryDTO = categoryRepository.categoryNamesReturn();
    request.setAttribute("categoryNames", categoryDTO);
%>
</html>
