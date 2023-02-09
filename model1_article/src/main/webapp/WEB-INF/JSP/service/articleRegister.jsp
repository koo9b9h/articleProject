<%@ page import="com.example.model1_article.DTO.ArticleDTO" %>
<%@ page import="com.example.model1_article.validation.ArticleInputData" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model1_article.repository.ArticleRepository" %>
<%@ page import="com.example.model1_article.repository.ArticleRepositoryImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<%
    String confirm_password = null;

    ArticleDTO articleDTO = new ArticleDTO();
    ArticleInputData articleInputData = new ArticleInputData();
    ArticleRepository articleRepository = new ArticleRepositoryImpl();

    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    List<FileItem> items = upload.parseRequest(request);

    for (FileItem item : items) {
        String fieldName = item.getFieldName();
        if (fieldName.equals("category")) {
            articleDTO.setCategoryName(item.getString("UTF-8"));
        }
        if (fieldName.equals("author")) {
            articleDTO.setAuthor(item.getString("UTF-8"));
        }
        if (fieldName.equals("title")) {
            articleDTO.setTitle(item.getString("UTF-8"));
        }
        if (fieldName.equals("content")) {
            articleDTO.setContents(item.getString("UTF-8"));
        }
        if (fieldName.equals("password")) {
            articleDTO.setPassword(item.getString("UTF-8"));
        }
        if (fieldName.equals("confirm_password")) {
            confirm_password = item.getString("UTF-8");
        }
    }

    if(articleInputData.availableCheck(articleDTO,confirm_password)) {
        articleRepository.register(articleDTO);
        response.sendRedirect("boards/free/list");
    }
%>

<script>
    alert("올바른 양식으로 다시 저장해주세요.");
    location.href="boards/free/write";
</script>

</html>