<%@ page import="com.example.model1_article.repository.ArticleRepository" %>
<%@ page import="com.example.model1_article.repository.ArticleRepositoryImpl" %>
<%@ page import="com.example.model1_article.DTO.PageDTO" %>
<%@ page import="com.example.model1_article.DTO.SearchDTO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    try {
        ArticleRepository articleRepository = new ArticleRepositoryImpl();
        SearchDTO searchDTO = new SearchDTO();

        if (
                request.getParameter("startDate") != "" && request.getParameter("endDate") != ""
                        && request.getParameter("startDate") != null && request.getParameter("endDate") != null
        ) {
            String start = request.getParameter("startDate");
            String end = request.getParameter("endDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Timestamp startDate = new Timestamp(dateFormat.parse(start).getTime());
            Timestamp endDate = new Timestamp(dateFormat.parse(end).getTime());

            searchDTO.setStartDate(startDate);
            searchDTO.setEndDate(endDate);
        }

        if ((request.getParameter("category") != null && (!request.getParameter("category").equals("all")))
                && (request.getParameter("category") != "")) {
            searchDTO.setCategoryName(request.getParameter("category"));
        }

        if (request.getParameter("searchTerm").replaceAll(" ","").equals("") && request.getParameter("searchTerm") != null) {
            searchDTO.setSearchTerm(request.getParameter("searchTerm"));
        }


        PageDTO pageDTO = new PageDTO();
        pageDTO.setArticleTotalCount(articleRepository.articlesCount(searchDTO));
        pageDTO.setTotalPages((int) Math.ceil(pageDTO.getArticleTotalCount() * 1.0 / pageDTO.getRecordsPerPage()));
        if (request.getParameter("currentPage") != null) {
            pageDTO.setCrrentPage(Integer.parseInt(request.getParameter("currentPage")));
        }
        pageDTO.setStart((pageDTO.getCrrentPage() - 1) * pageDTO.getRecordsPerPage());

        request.setAttribute("articles", articleRepository.show(pageDTO.getStart(), pageDTO.getRecordsPerPage(), searchDTO));
        request.setAttribute("pageDTO", pageDTO);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</html>
