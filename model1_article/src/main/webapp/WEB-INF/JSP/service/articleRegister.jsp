<%@ page import="com.example.model1_article.DTO.RegisterRequiredDTO" %>
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

    RegisterRequiredDTO registerRequiredDTO = new RegisterRequiredDTO();
    ArticleInputData articleInputData = new ArticleInputData();
    ArticleRepository articleRepository = new ArticleRepositoryImpl();

    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    List<FileItem> items = upload.parseRequest(request);

    for (FileItem item : items) {
        String fieldName = item.getFieldName();
        if (fieldName.equals("category")) {
            registerRequiredDTO.setCategoryName(item.getString("UTF-8"));
        }
        if (fieldName.equals("author")) {
            registerRequiredDTO.setAuthor(item.getString("UTF-8"));
        }
        if (fieldName.equals("title")) {
            registerRequiredDTO.setTitle(item.getString("UTF-8"));
        }
        if (fieldName.equals("content")) {
            registerRequiredDTO.setContents(item.getString("UTF-8"));
        }
        if (fieldName.equals("password")) {
            registerRequiredDTO.setPassword(item.getString("UTF-8"));
        }
        if (fieldName.equals("confirm_password")) {
            confirm_password = item.getString("UTF-8");
        }
    }

    if(articleInputData.availableCheck(registerRequiredDTO,confirm_password)) {
        articleRepository.register(registerRequiredDTO);
        response.sendRedirect("boards/free/list");
    }
%>

<script>
    alert("올바른 양식으로 다시 저장해주세요.");
    location.href="boards/free/write";
</script>

</html>