<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.BulletionBoard" %>
<html>
<head>
    <title>お知らせ詳細</title>
</head>
<body>
	<header class="header">
    	<div class="navtext-container">
        	<p class="navtext">お知らせ詳細</p>
    	</div>

    	<%@include file="../common/G_header.jsp" %>
	</header>
    <main>
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
    </main>
</body>
<style>
body{
  overflow-y: scroll;
}

main {
    margin-top: 52px;
    margin-left: auto;
    margin-right: auto;
}
</style>
</html>
