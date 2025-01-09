<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.ContactBook" %>

<html>
<head>
    <title>連絡帳一覧</title>
</head>
<body>
    <h2>連絡帳一覧</h2>

    <table>
        <thead>
            <tr>
                <th>生徒氏名</th>
                <th>日付</th>
                <th>詳細</th>
            </tr>
        </thead>
        <tbody>
            <%
                // リクエストスコープから contactBookList を取得
                List<ContactBook> contactBooks = (List<ContactBook>) request.getAttribute("contactBookList");
                if (contactBooks != null && !contactBooks.isEmpty()) {
                    for (ContactBook book : contactBooks) {
            %>
                <tr>
                    <td><%= book.getStudentName() %></td>
                    <td><%= book.getDay() %></td>
                    <td>
                        <a href="ContactBookListExecute.action?contactBookId=<%= book.getContactBookId() %>">詳細</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="3">連絡帳がありません。</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
