<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>投稿の詳細</title>
</head>
<body>
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
