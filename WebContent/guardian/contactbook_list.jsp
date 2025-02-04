<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>連絡帳一覧</title>
</head>
<body>
	<header class="header">
    	<div class="navtext-container">
        	<p class="navtext">連絡帳閲覧</p>
    	</div>

    	<%@include file="../common/G_header.jsp" %>
	</header>
	<main>
	<br>
    <table border="1">
        <tr>
            <th>日付</th>
            <th>詳細</th>
        </tr>
        <c:forEach var="contactBook" items="${contactBookList}">
            <tr>
                <td>${contactBook.day}</td>
                <td>
                    <form action="ContactBookListExecute.action" method="get">
                        <input type="hidden" name="contact_book_id" value="${contactBook.contactBookId}" />
                        <input type="submit" value="詳細を見る" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>


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
