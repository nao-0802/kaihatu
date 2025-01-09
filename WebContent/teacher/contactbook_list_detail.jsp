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
        
        <p><strong>生徒氏名:</strong> <%= contactBook.getTeacherId() %></p>
        <p><strong>日付:</strong> <%= contactBook.getDay() %></p>
        <p><strong>連絡内容:</strong> <%= contactBook.getContactDetails() %></p>
    <%
        } else {
    %>
        <p>連絡帳の詳細情報がありません。</p>
    <%
        }
    %>

    <a href="../teacher/ContactBookList.action">戻る</a>
</body>
</html>
