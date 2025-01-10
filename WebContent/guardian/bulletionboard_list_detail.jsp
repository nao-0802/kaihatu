<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.BulletionBoard" %>
<html>
<head>
    <title>掲示板詳細</title>
</head>
<body>
    <h2>掲示板詳細</h2>
    <%
        BulletionBoard board = (BulletionBoard) request.getAttribute("bulletionBoard");
        if (board != null) {
    %>
        <p>日付: <%= board.getDay() %></p>
        <p>タイトル: <%= board.getTitle() %></p>
        <p>内容: <%= board.getContent() %></p>
    <%
        } else {
    %>
        <p>データがありません。</p>
    <%
        }
    %>
    <br>
    <a href="../guardian/BulletionBoardList.action">一覧に戻る</a>
</body>
</html>
