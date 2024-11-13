<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>生徒情報更新</title>
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
            width: 300px;
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
    </style>
</head>
<body>
    <div class="update-container">
        <h2>生徒情報更新</h2>

        <!-- エラーメッセージの表示 -->
        <div class="error-message" id="error-message" style="display: ${not empty errorMessage ? 'block' : 'none'};">
            ${errorMessage != null ? errorMessage : ''}
        </div>

        <!-- 生徒情報更新フォーム -->
        <form action="student/update" method="POST" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="student_name">生徒氏名:</label>
                <input type="text" id="student_name" name="student_name" class="form-control" value="${student_name != null ? student_name : ''}" required>
            </div>
            <div class="form-group">
                <label for="password">パスワード:</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="class_id">クラスID:</label>
                <input type="text" id="class_id" name="class_id" class="form-control" value="${class_id != null ? class_id : ''}" required>
            </div>
            <button type="submit" class="btn">更新</button>
        </form>
    </div>

    <script>
        function validateForm() {
            const student_name = document.getElementById('student_name').value;
            const password = document.getElementById('password').value;
            const class_id = document.getElementById('class_id').value;
            const errorMessage = document.getElementById('error-message');
            errorMessage.style.display = 'none';

            // 未入力の場合の処理
            if (!student_name || !password || !class_id) {
                errorMessage.textContent = '生徒氏名、パスワード、クラスIDを入力してください。';
                errorMessage.style.display = 'block';
                return false; // フォームの送信を防ぐ
            }

            // フォームは正常
            return true;
        }
    </script>
</body>
</html>
