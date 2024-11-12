<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>教師情報更新</title>
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
            width: 400px; /* 固定幅に調整 */
            box-sizing: border-box; /* padding込みで計算 */
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
        input[type="text"], input[type="password"] {
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
        }
        .btn:hover {
            background-color: #218838; /* 濃い緑 */
        }
        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 15px;
        }
        .btn-back {
            width: 100%; /* ボタンの幅を更新ボタンと同じに設定 */
            padding: 10px;
            background-color: #007bff; /* 青色 */
            color: white;
            text-decoration: none;
            border-radius: 4px;
            display: inline-block;
            text-align: center;
            margin-top: 10px;
            box-sizing: border-box; /* padding込みで計算 */
        }
        .btn-back:hover {
            background-color: #0056b3; /* 濃い青 */
        }
    </style>
</head>
<body>
    <div class="update-container">
        <h2>教職員情報更新</h2>
        <!-- エラーメッセージ表示エリア -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>

        <form action="teacherUpdate" method="POST" onsubmit="return validateForm()">
            <!-- 教師名 -->
            <div class="form-group">
                <label for="teacher_name">名前:</label>
                <input type="text" id="teacher_name" name="teacher_name" class="form-control" value="${teacher.teacher_name}" required>
            </div>
            <!-- クラスID -->
            <div class="form-group">
                <label for="class_id">クラスID:</label>
                <input type="text" id="class_id" name="class_id" class="form-control" value="${teacher.class_id}" required>
            </div>
            <!-- パスワード -->
            <div class="form-group">
                <label for="password">パスワード:</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <button type="submit" class="btn">更新</button>
        </form>

        <!-- 戻るボタン -->
        <a href="Teacher_update.jsp" class="btn-back">戻る</a>
    </div>

    <script>
        function validateForm() {
            const teacherName = document.getElementById('teacher_name').value;
            const classId = document.getElementById('class_id').value;
            const password = document.getElementById('password').value;
            const errorMessage = document.getElementById('error-message');
            errorMessage.style.display = 'none';

            // 未入力の場合の処理
            if (!teacherName || !classId || !password) {
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
