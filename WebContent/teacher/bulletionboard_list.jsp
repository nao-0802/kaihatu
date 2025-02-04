<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.BulletionBoard" %>

<html>
<head>
    <title>全体掲示一覧</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
    <style>
        body {
            background-color: #F4F6F8;
            font-family: 'Arial', sans-serif;
            margin: 0;
        }

        header {
            background-color: #4CAF50;
            padding: 15px;
            text-align: center;
            color: white;
        }

        .navtext-container {
            display: flex;
            justify-content: center;
        }

        .navtext {
            font-size: 24px;
            letter-spacing: 2px;
        }

        main {
            padding: 20px;
            margin-top: 20px;
            max-width: 900px;
            margin-left: auto;
            margin-right: auto;
            background-color: #fff;
            border-radius: 8px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f4f4f4;
            font-weight: bold;
        }

        td {
            background-color: #fff;
        }

        td button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 6px 12px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        td button:hover {
            background-color: #45a049;
        }

        form {
            display: inline-block;
            margin-right: 10px;
        }

        .no-data {
            text-align: center;
            color: #888;
            font-size: 16px;
            padding: 20px 0;
        }

        .form-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .form-container button {
            background-color: #2196F3;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 16px;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .form-container button:hover {
            background-color: #1e88e5;
        }
    </style>
</head>
<body>

<header class="header">
    <div class="navtext-container">
        <p class="navtext">お知らせ一覧</p>
    </div>
    <%@include file="../common/T_header.jsp" %>
</header>

<main>
<br>
<br>
<br>
<br>
    <table>
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
                    <td colspan="3" class="no-data">掲示情報がありません。</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <div class="form-container">
        <form action="../teacher/BulletionBoard.action" method="post">
            <button type="submit">新規作成</button>
        </form>
        <form action="../teacher/StudentListExecute.action" method="post">
            <button type="submit">生徒一覧</button>
        </form>
    </div>
</main>

</body>
</html>
