<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>生徒カルテ新規登録</title>
    <style>
        /* ここにCSSを追加 */
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            background-color: #f9f9f9;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }
        input, textarea, button {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        button {
            background-color: #007BFF;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .message {
            margin-top: 10px;
            color: red;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>生徒カルテ新規登録</h1>
        <form action="StudentRecordCreateExecuteAction" method="post">
            <!-- 生徒氏名 -->
            <label for="name">生徒氏名 <span class="message">（必須）</span></label>
            <input type="text" id="name" name="name" placeholder="生徒氏名を入力してください" required>

            <!-- 生年月日 -->
            <label for="birthdate">生年月日 <span class="message">（必須）</span></label>
            <input type="date" id="birthdate" name="birthdate" required>

            <!-- アレルギー -->
            <label for="allergy">アレルギー</label>
            <textarea id="allergy" name="allergy" rows="3" placeholder="アレルギー情報を入力してください"></textarea>

            <!-- 登録ボタン -->
            <button type="submit">登録</button>
        </form>
    </div>
</body>
</html>
