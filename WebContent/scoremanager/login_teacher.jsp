<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>教職員ログイン</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 300px;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        p {
            text-align: left;
            margin: 10px 0;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 10px;
            margin-top: 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #45a049;
        }

        .error {
            color: red;
            margin-bottom: 15px;
            text-align: center;
        }

        .signup-link {
            text-align: center;
            margin-top: 15px;
        }

        .signup-link a {
            color: #007bff;
            text-decoration: none;
        }

        .signup-link a:hover {
            text-decoration: underline;
        }

        .password-container {
            position: relative;
            display: flex;
            align-items: center;
        }

        #toggleIcon {
            position: absolute;
            right: 10px;
            cursor: pointer;
        }
    </style>
</head>

<body>
    <div class="login-container">
        <form action="LoginTeacherExecute.action" method="post">
            <h2>教職員ログイン</h2>

            <!-- エラーメッセージ表示 -->
            <c:if test="${not empty errors}">
                <div class="error">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <p>教職員ID:<input type="text" name="teacherID" placeholder="教職員ID(10桁)" required></p>

            <p>
                パスワード:
                <div class="password-container">
                    <input type="password" id="password" name="password" placeholder="パスワード" required>
                    <i id="toggleIcon" class="fa-solid fa-eye fa-fw"></i>
                </div>
            </p>

            <!-- ログインボタン -->
            <button type="submit">ログイン</button>
        </form>
    </div>

    <script>
        const passwordField = document.getElementById('password');
        const toggleIcon = document.getElementById('toggleIcon');
        let passwordVisible = false;

        toggleIcon.addEventListener('click', function () {
            if (passwordVisible) {
                passwordField.type = 'password';
                toggleIcon.classList.remove('fa-eye-slash');
                toggleIcon.classList.add('fa-eye');
                passwordVisible = false;
            } else {
                passwordField.type = 'text';
                toggleIcon.classList.remove('fa-eye');
                toggleIcon.classList.add('fa-eye-slash');
                passwordVisible = true;
            }
        });
    </script>
</body>

</html>
