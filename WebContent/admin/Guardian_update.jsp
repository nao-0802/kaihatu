<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
        <form action="/guardian/update" method="POST" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="guardian_id">保護者ID:</label>
                <input type="text" id="guardian_id" name="guardian_id" class="form-control" required pattern="^g.*$" title="保護者IDは「g」で始まる必要があります" value="${param.guardian_id}">
            </div>
            <div class="form-group">
                <label for="guardian_name">保護者氏名:</label>
                <input type="text" id="guardian_name" name="guardian_name" class="form-control" required maxlength="50" value="${param.guardian_name}">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" class="form-control" required maxlength="100" value="${param.email}">
            </div>
            <div class="form-group">
                <label for="password">パスワード:</label>
                <input type="password" id="password" name="password" class="form-control" required maxlength="255" value="${param.password}">
            </div>
            <button type="submit" class="btn">更新</button>
        </form>
        <!-- 戻るボタンを追加 -->
        <form action="Guardian_update.jsp">
            <button type="submit" class="btn-back">戻る</button>
        </form>
    </div>

    <script>
        function validateForm() {
            const guardianId = document.getElementById('guardian_id').value; // 保護者IDの取得
            const guardianName = document.getElementById('guardian_name').value;
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const errorMessage = document.getElementById('error-message');
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
