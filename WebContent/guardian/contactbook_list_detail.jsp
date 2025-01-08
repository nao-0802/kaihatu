<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>連絡帳詳細</title>
</head>
<body>
    <h1>連絡帳詳細</h1>
    <table border="1">
        <tr>
            <th>日付</th>
            <td>${contactBook.day}</td>
        </tr>
        <tr>
            <th>内容</th>
            <td>${contactBook.contactDetails}</td>
        </tr>
        <tr>
            <th>確認状況</th>
            <td>${contactBook.contactCheck ? "確認済み" : "未確認"}</td>
        </tr>
    </table>
    <a href="../guardain/ContactBookListAction">一覧に戻る</a>
</body>
</html>
