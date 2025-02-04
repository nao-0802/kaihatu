<%@page contentType="text/html; charset=UTF-8" %>
<!-- %@include file="../header.html" % -->

<head>
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
    </style>
</head>

<body>
    <div class="login-container">
        <form action="LoginGuardianExecute.action" method="post">
            <h2 name="title">保護者ログイン</h2>
            <p>保護者ID:<input type="text" name="guardian_id"></p>
            <p>パスワード:<input type="password" name="password"></p>
            <button name="login_btn">ログイン</button>
        </form>
    </div>
</body>

<!--  %@include file="../footer.html" % -->
