<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.BulletionBoard" %>

<html>
<head>
    <title>全体掲示一覧</title>
</head>
<body>
    <h2>全体掲示一覧</h2>
    <table border="1">
        <thead>
            <tr>
                <th>日付</th>
                <th>タイトル</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<BulletionBoard> boardList = (List<BulletionBoard>) request.getAttribute("boardList");
                if (boardList != null && !boardList.isEmpty()) {
                    for (BulletionBoard board : boardList) {
            %>
                <tr>
                    <td><%= board.getDay() %></td>
                    <td><%= board.getTitle() %></td>
                    <td>
                        <form action="../teacher/BulletionBoardListExecute.action" method="get">
                            <input type="hidden" name="postId" value="<%= board.getPostId() %>">
                            <button type="submit">詳細</button>
                        </form>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="3">掲示情報がありません。</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <br>
    <form action="../teacher/BulletionBoard.action" method="post">
        <button type="submit">新規作成</button>
    </form>
    <form action="../teacher/StudentListExecute.action" method="post">
        <button type="submit">生徒一覧</button>
    </form>
</body>
</html>
