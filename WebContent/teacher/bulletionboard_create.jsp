<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>全体掲示作成</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
    <style>
        body {
            background-color: #F4F6F8;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #4CAF50;
            padding: 20px;
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
            max-width: 800px;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        label {
            font-size: 16px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"], textarea {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
            width: 100%;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        /* スクロール用のスタイル */
        body {
            overflow-y: scroll;
        }
    </style>
</head>
<body>

<header class="header">
    <div class="navtext-container">
        <p class="navtext">お知らせ作成</p>
    </div>
    <%@include file="../common/T_header.jsp" %>
</header>

<main>
<br>
<br>
<br>
<br>
    <form action="../teacher/BulletionBoardExecute.action" method="post">
        <label for="title">タイトル:</label>
        <input type="text" id="title" name="title" required>

        <label for="content">内容:</label>
        <textarea id="content" name="content" rows="5" cols="30" required></textarea>

        <input type="submit" value="投稿">
    </form>
</main>

</body>
</html>
