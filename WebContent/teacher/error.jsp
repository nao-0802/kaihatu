<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>エラー発生</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffffff; /* 背景色を白に設定 */
            color: #000000; /* テキスト色を黒に設定 */
            text-align: center;
            padding: 50px;
        }
        .error-message {
            background-color: #f8d7da; /* 背景色は薄い赤 */
            border: 1px solid #f5c6cb; /* 境界線の色 */
            padding: 20px;
            margin-bottom: 20px;
            font-size: 18px;
            color: #721c24; /* 文字色を赤に設定 */
        }
        .back-link {
            text-decoration: none;
            color: #155724; /* リンクの色 */
            font-weight: bold;
        }
    </style>
</head>
<body>

    <div class="error-message">
        <h2>エラーが発生しました</h2>
        <p>
            <%-- リクエストスコープからエラーメッセージを取得 --%>
            <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "予期しないエラーが発生しました。" %>
        </p>
    </div>

    <p>
        <a href="LifeRecord.jsp" class="back-link">ホームページに戻る</a>

    </p>

</body>
</html>
