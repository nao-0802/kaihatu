<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>生徒カルテ情報登録完了</title>
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
            text-align: center; /* コンテンツ全体を中央揃え */
        }
        h2 {
            margin-bottom: 20px;
            color: #28a745; /* 緑色 */
        }
        p {
            margin-bottom: 30px;
            font-size: 16px;
        }
        .btn-container {
            margin-top: 20px; /* ボタンを少し下に */
        }
        .btn {
            display: inline-block; /* ボタン風に表示 */
            padding: 10px 20px;
            background-color: #007bff; /* 青色背景 */
            color: white;
            text-decoration: none; /* 下線を削除 */
            font-size: 16px;
            border-radius: 4px; /* 角丸 */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* ボックスシャドウ */
            transition: background-color 0.3s ease; /* ホバー時の色変更をスムーズに */
        }
        .btn:hover {
            background-color: #0056b3; /* 濃い青に変化 */
        }
    </style>
</head>
<body>
    <div class="update-container">
        <h2>生徒カルテ情報が登録されました！</h2>
        <p>生徒カルテ情報の登録が正常に完了しました。</p>
        <p>登録内容をご確認ください。</p>
        <div class="btn-container">
            <!-- ボタン風リンク -->
            <a href="student_record_guardian.jsp" class="btn">生徒カルテへ戻る</a>
        </div>
    </div>
</body>
</html>
