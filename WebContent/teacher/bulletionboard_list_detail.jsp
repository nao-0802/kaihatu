<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.BulletionBoard" %>

<html>
<head>
    <title>掲示詳細</title>
    <style>
        /* ページ全体のスタイル */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fb;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-top: 20px;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        .board-details {
            margin: 20px 0;
            color: #555;
        }

        .board-details p {
            font-size: 1.2em;
            line-height: 1.6;
        }

        .board-details strong {
            color: #6fa3ef;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
        }

        .back-link a {
            color: #6fa3ef;
            font-size: 1.1em;
            text-decoration: none;
            padding: 8px 16px;
            background-color: #f1f1f1;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .back-link a:hover {
            background-color: #e0e0e0;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>掲示詳細</h2>

        <div class="board-details">
            <%
                BulletionBoard board = (BulletionBoard) request.getAttribute("bulletionBoard");
                if (board != null) {
            %>
                <p><strong>タイトル:</strong> <%= board.getTitle() %></p>
                <p><strong>内容:</strong> <%= board.getContent() %></p>
                <p><strong>日付:</strong> <%= board.getDay() %></p>
            <%
                } else {
            %>
                <p>掲示情報が見つかりませんでした。</p>
            <%
                }
            %>
        </div>

        <div class="back-link">
            <a href="../teacher/BulletionBoardList.action">一覧に戻る</a>
        </div>
    </div>

</body>
</html>
