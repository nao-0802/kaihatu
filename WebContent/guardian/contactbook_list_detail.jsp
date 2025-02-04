<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.ContactBook" %>

<html>
<head>
    <title>連絡帳詳細</title>
    <style>
        /* 全体のスタイル */
        body {
            font-family: 'Verdana', sans-serif;
            background-color: #fafafa; /* 明るく柔らかな背景色 */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* 画面全体を使って中央揃え */
            color: #333;
        }

        /* コンテンツのスタイル */
        .content {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 16px; /* 角をさらに丸く */
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 600px;
            text-align: center;
            font-size: 1.1em;
        }

        h2 {
            font-size: 2em; /* タイトルを少し小さく */
            color: #FF8C42; /* 柔らかなオレンジ色 */
            margin-bottom: 20px;
            font-weight: 300;
        }

        p {
            font-size: 1.1em; /* 少し小さめのフォント */
            line-height: 1.6;
            margin: 15px 0;
        }

        strong {
            color: #FF8C42; /* 強調部分もオレンジ色 */
        }

        /* ボタンのスタイル */
        .button {
            padding: 14px 28px;
            font-size: 1em; /* ボタンのフォントサイズを少し小さめ */
            background-color: #FF8C42; /* オレンジ色 */
            color: white;
            border: none;
            border-radius: 12px; /* さらに丸みを帯びたボタン */
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
            margin-top: 30px;
            display: inline-block;
            text-align: center;
        }

        .button:hover {
            background-color: #FF7A29; /* ホバー時に少し濃いオレンジ */
            transform: scale(1.03); /* ボタンの動きを穏やかに */
        }

        /* スマホ対応 */
        @media (max-width: 768px) {
            h2 {
                font-size: 1.8em;
            }

            p {
                font-size: 1em;
            }
        }
    </style>
</head>
<body>
    <div class="content">
        <h2>連絡帳詳細</h2>

        <%
            ContactBook contactBook = (ContactBook) request.getAttribute("contactBook");
            if (contactBook != null) {
        %>

            <p><strong>日付:</strong> <%= contactBook.getDay() %></p>
            <p><strong>連絡内容:</strong> <%= contactBook.getContactDetails() %></p>
        <%
            } else {
        %>
            <p>連絡帳の詳細情報がありません。</p>
        <%
            }
        %>

        <form action="../guardian/ContactBookList.action" method="get">
            <!-- サーバーサイドの値を直接埋め込む -->
            <input type="hidden" name="guardian_id" value="<%= contactBook.getGuardianId() %>">
            <button type="submit" class="button">連絡帳閲覧</button>
        </form>
    </div>
</body>
</html>
