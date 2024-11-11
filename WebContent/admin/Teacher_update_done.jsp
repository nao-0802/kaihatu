<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>教職員情報変更完了</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .update-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #28a745; /* 緑色 */
        }
        p {
            text-align: center;
            margin-bottom: 30px;
            font-size: 16px;
        }
        .btn-container {
            text-align: center; /* ボタンを中央揃えにする */
        }
        .btn {
            width: 100%;
            padding: 10px;
            background-color: #007bff; /* 青色 */
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3; /* 濃い青 */
        }
    </style>
</head>
<body>
    <div class="update-container">
        <h2>教職員情報が更新されました！</h2>
        <p>教職員情報の更新が正常に完了しました。</p>
        <p>変更内容をご確認ください。</p>
        <div class="btn-container">
            <a href="Teacher_update.jsp">教職員更新へ</a>
        </div>
    </div>
</body>
</html>
