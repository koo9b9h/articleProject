<%@ page import="com.example.model1_article.DTO.CategoryDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="http://localhost:8080/JS/validation.js"></script>
        <link rel="stylesheet" href="http://localhost:8080/CSS/write.css">

        <title>게시글 등록</title>
    </head>

    <body>
    <header>
        <h1> 게시판 - 등록</h1>
    </header>

    <section>
        <article>
            <form id="registerForm" name="registerForm" action="/register" method="post" enctype="multipart/form-data">
                <table>
                    <tr>
                        <td><h3>카테고리* </h3></td>
                        <td>
                            <select name="category" required>
                                <%
                                    request.getRequestDispatcher("/categoryReturn").include(request,response);
                                    CategoryDTO categoryDTO = (CategoryDTO)request.getAttribute("categoryNames");
                                    for(String categoryName :categoryDTO.getCategoryNames()){
                                %>
                                    <%= "<option value="+categoryName+">"+categoryName+"</option>" %>
                                <%
                                    }//for
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><h3>작성자* </h3></td>
                        <td><input type="text" name="author" required></td>
                    </tr>
                    <tr>
                        <td><h3>비밀번호*</h3></td>
                        <td><input type="password" name="password" placeholder="비밀번호" required></td>
                        <td><input type="password" name="confirm_password" placeholder="비밀번호 확인" required></td>
                    </tr>
                    <tr>
                        <td><h3>제목* </h3></td>
                        <td><input type="text" name="title" required></td>
                    </tr>
                    <tr>
                        <td><h3>내용* </h3></td>
                        <td><textarea name="content" rows="30" cols="100" required></textarea></td>
                    </tr>
                    <tr>
                        <td><h3>첨부 파일</h3></td>
                        <td>
                            <input type="file" name="attachment1" ><br><br>
                            <input type="file" name="attachment2" ><br><br>
                            <input type="file" name="attachment3" >
                        </td>
                    </tr>
                </table>
            </form>

        </article>
        <footer>
            <button onclick="location.href='/boards/free/list'">취소</button>
            <input type="submit" form="registerForm" value="저장" onclick="return validateForm()">
        </footer>
    </section>
    </body>
</html>