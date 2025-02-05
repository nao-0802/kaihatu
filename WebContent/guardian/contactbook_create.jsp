<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>連絡帳作成</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        /* 既存のスタイル */
        body {
            margin: 0;
            padding-top: 80px;
            font-family: 'Hiragino Kaku Gothic ProN', Meiryo, sans-serif;
            background-color: #ffffff;
        }

        .header {
            position: fixed;
            top: 0;
            width: 100%;
            background: #fff8e1;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            z-index: 1000;
            padding: 10px 0;
            text-align: center;
        }

        .main-content {
            padding: 25px;
            max-width: 650px;
            margin: 40px auto;
            background: #ffffff;
            box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
            border-radius: 12px;
            text-align: center;
        }

        .contact-form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .contact-form label {
            font-weight: bold;
            margin-top: 12px;
            font-size: 1.1em;
            color: #5a5a5a;
        }

        .contact-form textarea {
            width: 90%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 8px;
            resize: none;
            font-size: 1em;
            background-color: #fffaf0;
        }

        .contact-form button {
            margin-top: 15px;
            padding: 12px 20px;
            background-color: #6fa3ef;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 1.1em;
            transition: background-color 0.3s;
        }

        .contact-form button:hover {
            background-color: #5b8ed7;
        }

        .back-link {
            margin-top: 20px;
            font-size: 1em;
        }

        .back-link a {
            color: #6fa3ef;
            text-decoration: none;
        }

        .back-link a:hover {
            text-decoration: underline;
        }

        .error {
            color: red;
            font-weight: bold;
            text-align: center;
            margin-bottom: 12px;
            background: #ffe5e5;
            padding: 10px;
            border-radius: 6px;
            display: none; /* 最初は非表示 */
        }
    </style>
    <script>
        function validateForm() {
            // エラーメッセージを非表示にする
            var errorMessage = document.getElementById("error-message");
            errorMessage.style.display = "none";

            var contactDetails = document.getElementById("contactDetails").value;
            if (contactDetails.trim() === "") {
                // 連絡内容が空の場合はエラーメッセージを表示
                errorMessage.innerText = "連絡内容を入力してください";
                errorMessage.style.display = "block"; // エラーメッセージを表示
                return false; // フォーム送信を停止
            }
            return true; // フォーム送信を許可
        }
    </script>
</head>
<body>
    <header class="header">
        <div class="navtext-container">
            <p class="navtext">連絡帳を書く</p>
        </div>
        <%@include file="../common/G_header.jsp" %>
    </header>

    <main class="main-content">
        <!-- エラーメッセージの表示 -->
        <div id="error-message" class="error"></div>

        <form action="ContactBookWriteExecute.action" method="post" class="contact-form" onsubmit="return validateForm()">
            <input type="hidden" name="guardianId" value="${guardianId}">
            <h3>教師名: ${teacherName}</h3> <!-- ここで教師名を表示 -->

            <label for="contactDetails">連絡内容</label>
            <textarea name="contactDetails" id="contactDetails" rows="10" cols="50" placeholder="連絡内容を記入してください">${param.contactDetails}</textarea>

            <button type="submit">送信</button>
        </form>

        <div class="back-link">
            <a href="./LifeRecord.action">生活記録に戻る</a>
        </div>
    </main>
</body>
</html>
