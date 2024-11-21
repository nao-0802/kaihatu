<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>タイトル一覧</title>
</head>
<body>
    <h3>タイトル一覧</h3>
    <form action="BulletinBoardDetailAction" method="get">
        <select name="post_id" required>
            <c:forEach var="bulletin" items="${titleList}">
                <option value="${bulletin.postId}">${bulletin.title}</option>
            </c:forEach>
        </select>
        <button type="submit">表示</button>
    </form>
</body>
</html>