<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.ContactBook" %>

<html>
<head>
    <title>連絡帳詳細</title>
    <style>
        /* 全体のスタイル */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center; /* 横方向中央 */
            align-items: center; /* 縦方向中央 */
            height: 100vh; /* 画面全体の高さ */
            color: #333;
            flex-direction: column;
        }

        /* ヘッダーのスタイル */
        .header {
            width: 100%;
            position: fixed; /* ヘッダーを固定 */
            top: 0;
            left: 0;
            background-color: #fff;
            padding: 5px 0;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            z-index: 1000;
        }

        .navtext-container {
            text-align: center;
        }

        .navtext {
            font-size: 1.5em;
            color: #007bff;
        }

        /* コンテンツボックス */
        .content {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 500px;
            text-align: center;
            margin-top: -60px; /* ヘッダー分のスペース */
        }

        /* タイトル */
        h2 {
            font-size: 1.6em;
            color: #007bff;
            margin-bottom: 15px;
        }

        /* テキスト */
        p {
            font-size: 1em;
            line-height: 1.5;
            margin: 10px 0;
        }

        strong {
            color: #007bff;
            text-align: center; /* 強調部分を中央寄せ */
            display: block; /* ブロック要素にして中央寄せ */
        }

        /* ボタン */
        .button {
            padding: 12px 24px;
            font-size: 1em;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-top: 20px;
            display: inline-block;
            text-align: center;
        }

        .button:hover {
            background-color: #0056b3;
        }

        /* スマホ対応 */
        @media (max-width: 768px) {
            h2 {
                font-size: 1.4em;
            }

            p {
                font-size: 0.95em;
            }
        }
    </style>
</head>
<body>
    <header class="header">
        <div class="navtext-container">
            <p class="navtext">連絡帳詳細</p>
        </div>
        <%@include file="../common/T_header.jsp" %>
    </header>

    <div class="content">
        <%
            ContactBook contactBook = (ContactBook) request.getAttribute("contactBook");
            if (contactBook != null) {
        %>
            <p><strong>日付</strong> <%= contactBook.getDay() %></p>
            <p><strong>連絡内容</strong> <%= contactBook.getContactDetails() %></p>

            <form action="../guardian/ContactBookList.action" method="get">
                <input type="hidden" name="guardian_id" value="<%= contactBook.getGuardianId() %>">
                <button type="submit" class="button">連絡帳一覧</button>
            </form>
        <%
            } else {
        %>
            <p>連絡帳の詳細情報がありません。</p>
        <%
            }
        %>
    </div>
</body>
</html>
