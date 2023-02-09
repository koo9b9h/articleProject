<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="http://localhost:8080/JS/validation.js"></script>
        <title>JSP - Hello World</title>
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
                                <% //이 부분 동적으로 만들기 %>
                                <option value="java">java</option>
                                <option value="javascript">javascript</option>
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
                            <input type="file" name="attachment1"><br><br>
                            <input type="file" name="attachment2"><br><br>
                            <input type="file" name="attachment3">
                        </td>
                    </tr>
                </table>
            </form>

            <button onclick="location.href='/boards/free/list'">취소</button>
            <input type="submit" form="registerForm" value="저장" onclick="return validateForm()">
        </article>
    </section>
    </body>
</html>