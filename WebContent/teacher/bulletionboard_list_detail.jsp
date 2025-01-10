<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.BulletionBoard" %>

<html>
<head>
    <title>掲示詳細</title>
</head>
<body>
    <h2>掲示詳細</h2>
    <%
        BulletionBoard board = (BulletionBoard) request.getAttribute("bulletionBoard");
        if (board != null) {
    %>
        <p><strong>タイトル:</strong> <%= board.getTitle() %></p>
        <p><strong>内容:</strong> <%= board.getContent() %></p>
        <p><strong>日付:</strong> <%= board.getDay() %></p>
    <%
        } else {
    %>
        <p>掲示情報が見つかりませんでした。</p>
    <%
        }
    %>
    <br>
    <a href="../teacher/BulletionBoardList.action">一覧に戻る</a>
</body>
</html>
