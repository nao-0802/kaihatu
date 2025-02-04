<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログアウト確認</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        .container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
            width: 400px;
            margin: 0 auto;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        h3 {
            color: #555;
            margin-bottom: 30px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin: 10px;
            transition: 0.3s;
        }

        button:hover {
            background-color: #45a049;
        }

        .no-button {
            background-color: #f44336;
        }

        .no-button:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>

<div class="container">
    <form action="Logout.action">
        <h2>ログアウト確認</h2>
        <h3>本当にログアウトしますか？</h3>
        <button name="yes_btn">はい</button>
    </form>

    <button class="no-button" name="no_btn" onclick="window.history.back()">いいえ</button>
</div>

</body>
</html>
