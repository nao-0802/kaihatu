<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>保護者情報更新</title>
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
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .btn {
            width: 100%;
            padding: 10px;
            background-color: #28a745; /* 緑色 */
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }
        .btn:hover {
            background-color: #218838; /* 濃い緑 */
        }
        .btn-back {
            width: 100%;
            padding: 10px;
            background-color: #007bff; /* 青色 */
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }
        .btn-back:hover {
            background-color: #0056b3; /* 濃い青 */
        }
        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="update-container">
        <h2>保護者情報更新</h2>
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                ${errorMessage}
            </div>
        </c:if>
        <form action="GuardianUpdateExecute.action" method="POST" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="guardian-id">保護者ID:</label>
                <input type="text" id="guardian-id" name="guardianId" readonly value="${gid}">
            </div>
            <div class="form-group">
                <label for="guardian-name">保護者氏名:</label>
                <input type="text" id="guardian-name" name="guardianName" required maxlength="50" value="${name}">
            </div>
            <div class="form-group">
                <label for="password">パスワード:</label>
                <input type="password" id="password" name="password" required maxlength="255" value="${pass}">
            </div>


            <button type="submit" class="btn">変更</button>
            <a href="./AccountList.action" class="btn-back">戻る</a>
        </form>
    </div>

    <script>
        function validateForm() {
            const guardianId = document.getElementById('guardian_id').value; // 保護者IDの取得
            const guardianName = document.getElementById('guardian_name').value;
            const password = document.getElementById('password').value;
            errorMessage.style.display = 'none';

            // 未入力の場合の処理
            if (!guardianId || !guardianName || !email || !password) {
                errorMessage.textContent = 'すべてのフィールドを入力してください。';
                errorMessage.style.display = 'block';
                return false; // フォームの送信を防ぐ
            }

            // フォームは正常
            return true;
        }
    </script>
</body>
</html>
