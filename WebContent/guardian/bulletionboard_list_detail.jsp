<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.BulletionBoard" %>
<html>
<head>
    <title>お知らせ詳細</title>
    <style>
        /* 全体のスタイル */
        body {
            font-family: "Arial", sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            color: #333;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        /* メインコンテンツ */
        .container {
            width: 90%;
            max-width: 600px;
            background: white;
            margin: 100px 0;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            text-align: center; /* コンテンツを中央寄せ */
        }

        .info {
            font-size: 16px;
            padding: 10px 0;
            border-bottom: 1px solid #ddd;
        }

        .info:last-child {
            border-bottom: none;
        }

        /* 戻るボタン */
        .back-button {
            display: block;
            margin: 20px auto;
            padding: 10px 15px;
            width: 150px;
            background: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            text-align: center;
            font-size: 14px;
        }

        .back-button:hover {
            background: #2980b9;
        }
    </style>
</head>
<body>
     <header class="header">
        <div class="navtext-container">
            <p class="navtext">お知らせ詳細</p>
        </div>
        <%@include file="../common/G_header.jsp" %>
    </header>

    <div class="container">
        <%
            BulletionBoard board = (BulletionBoard) request.getAttribute("bulletionBoard");
            if (board != null) {
        %>
            <div class="info"><strong>日付</strong> <br> <%= board.getDay() %></div>
            <div class="info"><strong>タイトル</strong> <br> <%= board.getTitle() %></div>
            <div class="info"><strong>内容</strong> <br> <%= board.getContent() %></div>
        <%
            } else {
        %>
            <p>データがありません。</p>
        <%
            }
        %>
        <a class="back-button" href="../guardian/BulletionBoardList.action">一覧に戻る</a>
    </div>
</body>
</html>
