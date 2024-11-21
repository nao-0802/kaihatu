<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>入力内容確認</title>
    <style>
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
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        button {
            background-color: #007BFF;
            color: #fff;
            border: 2px solid #007BFF;
            padding: 15px 30px;
            font-size: 18px;
            border-radius: 5px;
            cursor: pointer;
            margin: 10px;
            transition: background-color 0.3s, border-color 0.3s;
        }
        button:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        button:focus {
            outline: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>入力内容確認</h1>
        <form action="StudentRecordCreateExecuteAction" method="post">
            <!-- 確認された生徒氏名 -->
            <label for="name">生徒氏名</label>
            <input type="text" id="name" name="name" value="${name}" readonly>

            <!-- 確認された生年月日 -->
            <label for="birthdate">生年月日</label>
            <input type="text" id="birthdate" name="birthdate" value="${birthdate}" readonly>

            <!-- 確認されたアレルギー -->
            <label for="allergy">アレルギー</label>
            <textarea id="allergy" name="allergy" rows="3" readonly>${allergy}</textarea>

            <!-- 入力確認ボタン -->
            <div class="button-container">
                <button type="submit">登録</button>
                <!-- 修正ボタンを新規登録画面にリダイレクト -->
                <button type="button" onclick="window.location.href='StudentRecordCreateAction'">修正</button>
            </div>
        </form>
    </div>
</body>
</html>
