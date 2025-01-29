<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.BulletionBoard" %>
<html>
<head>
    <title>お知らせ</title>
</head>
<body>
    <h2>全体掲示板</h2>
    <table border="1">
        <thead>
            <tr>
                <th>日付</th>
                <th>タイトル</th>
                <th>詳細</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<BulletionBoard> bulletionBoardList = (List<BulletionBoard>) request.getAttribute("bulletionBoardList");
                if (bulletionBoardList != null && !bulletionBoardList.isEmpty()) {
                    for (BulletionBoard board : bulletionBoardList) {
            %>
                <tr>
                    <td><%= board.getDay() %></td>
                    <td><%= board.getTitle() %></td>
                    <td>
                        <a href="../guardian/BulletionBoardListExecute.action?postId=<%= board.getPostId() %>">詳細</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="3">掲示板のデータがありません。</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
