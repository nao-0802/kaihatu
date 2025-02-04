<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>連絡帳作成</title>
</head>
<body>
	<header class="header">
    	<div class="navtext-container">
        	<p class="navtext">連絡帳作成</p>
    	</div>

    	<%@include file="../common/G_header.jsp" %>
	</header>
<main>
    <h1>連絡帳を作成</h1>
    <form action="../guardian/ContactBookWriteExecute.action" method="post">
    	<input type="hidden" name="teacherId" value="${teacherId}">
        <p>教師名: ${teacherName}</p>
        <label for="contactDetails">連絡内容:</label><br>
        <textarea id="contactDetails" name="contactDetails" rows="10" cols="50" required></textarea><br><br>
        <button type="submit">送信</button>
    </form>


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
