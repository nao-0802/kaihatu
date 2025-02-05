<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.BulletionBoard" %>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            color: #333;
            background: #fff;
            text-align: center;
        }



        /* メインコンテンツ */
        main {
            max-width: 700px;
            margin: 50px auto;
            padding: 20px;
             width: 55%;


        }

        /* テーブル */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        th, td {
            padding: 15px;
            border-bottom: 1px solid #ddd;
            text-align: center; /* 中央寄せ */
        }

        th {
            background: #f0f0f0;
            font-weight: bold;
        }

        /* 詳細ボタン */
        .detail-link {
            display: inline-block;
            padding: 6px 12px;
            background: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-size: 14px;
        }

        .detail-link:hover {
            background: #45a049;
        }
    </style>
</head>
<body>
    <header class="header">
        <div class="navtext-container">
            <p class="navtext">お知らせ</p>
        </div>
        <%@include file="../common/G_header.jsp" %>
    </header>

    <main>
        <table>
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
                            <a class="detail-link" href="../guardian/BulletionBoardListExecute.action?postId=<%= board.getPostId() %>">詳細</a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="3" class="no-data">お知らせはありません。</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </main>
</body>
</html>
