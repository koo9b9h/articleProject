<%@ page import="com.example.model1_article.DTO.ArticleDTO" %>
<%@ page import="com.example.model1_article.repository.ArticleRepository" %>
<%@ page import="com.example.model1_article.repository.ArticleRepositoryImpl" %>
<%@ page import="com.example.model1_article.DTO.PageDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%

    ArticleRepository articleRepository = new ArticleRepositoryImpl();
    PageDTO pageDTO = new PageDTO();
    pageDTO.setArticleTotalCount(articleRepository.articlesCount());
    pageDTO.setTotalPages((int)Math.ceil(pageDTO.getArticleTotalCount() * 1.0 / pageDTO.getRecordsPerPage()));
    pageDTO.setCrrentPage(Integer.parseInt(request.getParameter("currentPage")));
    pageDTO.setStart((pageDTO.getCrrentPage() - 1) * pageDTO.getRecordsPerPage());

    request.setAttribute("articles", articleRepository.showAll(pageDTO.getStart(), pageDTO.getRecordsPerPage()));
    request.setAttribute("pageDTO",pageDTO);

%>
</html>
