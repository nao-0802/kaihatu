<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>連絡帳一覧</title>
</head>
<body>
    <h1>連絡帳一覧</h1>
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
</body>
</html>
