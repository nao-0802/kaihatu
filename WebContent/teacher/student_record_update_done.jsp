<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../common/T_header.jsp" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>更新完了</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            color: #333;
            text-align: center;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #4CAF50;
            color: white;
            padding: 15px 0;
            font-size: 20px;
            font-weight: bold;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .navtext-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 50px;
        }

        .navtext {
            font-size: 24px;
            font-weight: bold;
        }

        main {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 80vh;
            margin: 0 auto;
            padding: 20px;
        }

        h2 {
            font-size: 24px;
            margin: 20px 0;
            color: #333;
            font-weight: normal;
        }

        form {
            margin-top: 30px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 15px 30px;
            font-size: 18px;
            cursor: pointer;
            border-radius: 8px;
            transition: background-color 0.3s ease, transform 0.2s;
        }

        button:hover {
            background-color: #45a049;
            transform: scale(1.05);
        }

        a {
            color: #4CAF50;
            font-size: 16px;
            text-decoration: none;
            margin-top: 20px;
            display: inline-block;
            transition: text-decoration 0.2s ease;
        }

        a:hover {
            text-decoration: underline;
        }

    </style>
</head>
<body>

<header class="header">
    <div class="navtext-container">
        <p class="navtext">生徒カルテ更新完了</p>
    </div>
    <%@include file="../common/T_header.jsp" %>
</header>

<main>
    <h2>生徒カルテの更新が完了しました。</h2>
    <form action="../teacher/StudentRecord.action" method="post">
        <button name="student_id" value="${studentId}">生徒カルテへ</button>
    </form>
    <a href="../teacher/StudentListExecute.action">生徒一覧へ戻る</a>
</main>

</body>
</html>
