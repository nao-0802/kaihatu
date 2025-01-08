<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<body>
<header class="header">
    <div class="navtext-container">
      <p class="navtext">全体掲示板</p>
    </div>
<%@include file="../common/G_header.jsp" %>


</header>



    <h3>投稿の詳細</h3>

    <c:if test="${not empty bulletin}">
        <table border="1">
            <tr>
                <th>投稿ID</th>
                <td>${bulletin.postId}</td>
            </tr>
            <tr>
                <th>タイトル</th>
                <td>${bulletin.title}</td>
            </tr>
            <tr>
                <th>内容</th>
                <td>${bulletin.content}</td>
            </tr>
            <tr>
                <th>教職員ID</th>
                <td>${bulletin.teacherId}</td>
            </tr>
        </table>
    </c:if>

    <c:if test="${empty bulletin}">
        <p>データが見つかりませんでした。</p>
    </c:if>

    <form action="BulletinBoardListAction" method="get">
        <button type="submit">戻る</button>
    </form>
</body>
</html>
