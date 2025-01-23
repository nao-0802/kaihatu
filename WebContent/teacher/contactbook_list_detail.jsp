<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.ContactBook" %>

<html>
<head>
    <title>連絡帳詳細</title>
</head>
<body>
    <h2>連絡帳詳細</h2>

    <%
        ContactBook contactBook = (ContactBook) request.getAttribute("contactBook");
        if (contactBook != null) {
    %>

        <p><strong>生徒氏名:</strong> <%= contactBook.getStudentName() %></p>
        <p><strong>日付:</strong> <%= contactBook.getDay() %></p>
        <p><strong>連絡内容:</strong> <%= contactBook.getContactDetails() %></p>
    <%
        } else {
    %>
        <p>連絡帳の詳細情報がありません。</p>
    <%
        }
    %>

    <form action="../teacher/ContactBookListReturn.action" method="get">
        <!-- サーバーサイドの値を直接埋め込む -->
        <input type="hidden" name="guardian_id" value="<%= contactBook.getGuardianId() %>">
        <button type="submit" class="button">連絡帳閲覧</button>
    </form>
</body>
</html>
