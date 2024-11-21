<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>エラー - 生徒カルテ閲覧</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0; /* 背景色を統一 */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .error-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
            color: #dc3545; /* エラーメッセージの色 */
        }
        .error-message {
            color: red;
            font-size: 16px;
            margin-bottom: 20px;
        }
        .btn-container {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
        }
        .btn-no {
            background-color: #28a745;
            color: white;
            text-decoration: none;
            padding: 10px;
            border-radius: 4px;
            display: inline-block;
            width: 100%;
            text-align: center;
        }
        .btn-no:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>生徒カルテ閲覧エラー</h2>
        <div class="error-message">
            生徒カルテの閲覧に失敗しました。もう一度入力内容を確認し、再試行してください。
        </div>
        <div class="btn-container">
            <a href="student_list.jsp" class="btn-no">戻る</a>
        </div>
    </div>
</body>
</html>
