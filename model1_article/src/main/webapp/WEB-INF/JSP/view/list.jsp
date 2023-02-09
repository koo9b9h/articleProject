
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>

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
                    <%-- JSP로 동적이게 변경소요 --%>
                    <option value="category1">Category예시 1</option>
                    <option value="category2">Category예시 2</option>
                    <option value="category3">Category예시 3</option>
                </select>

                <input type="text" id="searchTerm" name="searchTerm" >

                <input type="submit" value="검색">
            </form>
        </div>
    </header>

    <section>
        <header>총 <%="동적"%> 건</header>
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
                        //if 문이랑 out.println()으로 동적으로 게시글이 추가 되게 구현
                    %>
                </tbody>

            </table>
        </article>

        <footer>
            <div>
                <%
                    // 페이징 처리 코드 넣기

                %>
            </div>

            <button onclick="location.href='/boards/free/write'">작성</button>
        </footer>
    </section>
</body>

</html>