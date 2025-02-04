<%@page contentType="text/html; charset=UTF-8" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>投稿完了</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        header {
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #4CAF50;
            padding: 15px 20px;
        }

        .navtext-container {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
        }

        .navtext {
            letter-spacing: 2px;
            font-size: 28px;
            color: white;
            text-align: center;
        }

        main {
            max-width: 600px;
            margin: 40px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        p {
            font-size: 24px;
            color: #333;
            margin-bottom: 20px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #45a049;
        }

        /* スクロール用のスタイル */
        body {
            overflow-y: scroll;
        }

        /* レスポンシブデザイン */
        @media (max-width: 600px) {
            .navtext {
                font-size: 22px;
            }

            p {
                font-size: 20px;
            }

            button {
                font-size: 14px;
                padding: 10px 15px;
            }
        }
    </style>
</head>

<body>
    <header class="header">
        <div class="navtext-container">
            <p class="navtext">すてっぷのーと</p>
        </div>
    </header>

    <main>
        <p>投稿完了</p>
        <button onclick="location.href='../teacher/BulletionBoardList.action'">お知らせ一覧</button>
    </main>

</body>
</html>
