<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>管理者新規登録</title>
    <style>
        /* 基本スタイル設定 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        /* フォーム全体のボックススタイル */
        .form-container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            max-width: 400px;
            width: 100%;
        }

        /* タイトルスタイル */
        h2[name="title"] {
            text-align: center;
            color: #333333;
            margin-bottom: 20px;
        }

        /* 各入力フィールドのスタイル */
        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #666666;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #cccccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }

        /* ボタンのスタイル */
        button[name="register_btn"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button[name="register_btn"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <form action="AdminSignupExecuteAction" method="post">
            <h2 name="title">管理者新規登録</h2>
            <div class="form-group">
                <label for="admin_id">管理者ID:</label>
                <input type="text" name="admin_id" id="admin_id" required>
            </div>
            <div class="form-group">
                <label for="password">パスワード:</label>
                <input type="password" name="password" id="password" required>
            </div>
            <button name="register_btn">登録</button>
        </form>
    </div>
</body>
</html>
