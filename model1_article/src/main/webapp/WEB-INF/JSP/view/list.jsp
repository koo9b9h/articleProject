<%@ page import="com.example.model1_article.DTO.CategoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model1_article.DTO.ArticleDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.example.model1_article.DTO.PageDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<%
    request.getRequestDispatcher("/categoryReturn").include(request,response);
    request.getRequestDispatcher("/listPresentation").include(request,response);
    PageDTO pageDTO = (PageDTO) request.getAttribute("pageDTO");
    List<ArticleDTO> articles = (List<ArticleDTO>) request.getAttribute("articles");
    CategoryDTO categoryDTO = (CategoryDTO)request.getAttribute("categoryNames");
%>


<body>
    <header>
        <h1>자유 게시판 - 목록</h1>
        <div id="requirement">
            <form>
                <label for="startDate">등록일 </label>
                <input type="date" id="startDate" name="startDate">

                <label for="endDate">~</label>
                <input type="date" id="endDate" name="endDate">

                <select id="category" name="category">
                    <option value="all">전체 카테고리</option>
                    <%
                        for(String categoryName :categoryDTO.getCategoryNames()){
                            out.print("<option value="+categoryName+">"+categoryName+"</option>");
                        }//for
                    %>
                </select>

                <input type="text" id="searchTerm" name="searchTerm" >

                <input type="submit" value="검색">
            </form>
        </div>
    </header>

    <section>
        <header>총 <%= pageDTO.getArticleTotalCount()%> 건</header>
        <article>
            <table>
                <thead>
                    <tr>
                        <th>카테고리</th>
                        <th>첨부</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>등록일시</th>
                        <th>수정일시</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        for(ArticleDTO article : articles){
                    %>
                        <tr>
                            <td><%= article.getCategoryName()%></td>
                            <td></td>
                            <td><%= article.getTitle()%></td>
                            <td><%= article.getAuthor()%></td>
                            <td><%= article.getView()%></td>
                            <td><%= simpleDateFormat.format(article.getCreateTime())%></td>
                            <td><%= simpleDateFormat.format(article.getModifiedTime())%></td>
                        </tr>
                    <%
                        }//for
                    %>
                </tbody>

            </table>
        </article>

        <footer>
            <div>
                <%
                    for(int pageCount = 1; pageCount <= pageDTO.getTotalPages(); pageCount++){
                        if(pageCount == pageDTO.getCrrentPage()) {
                            out.print(pageCount + " ");
                            continue;
                        }
                        out.println("<a href='?currentPage=" + pageCount + "'>" + pageCount + "</a> ");
                    }
                %>
            </div>

            <button onclick="location.href='/boards/free/write'">작성</button>
        </footer>
    </section>
</body>

</html>