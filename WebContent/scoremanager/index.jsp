<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>JSPからJSPへの遷移</title>
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

        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 350px;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        label {
            font-size: 16px;
            margin-bottom: 10px;
            display: block;
            color: #555;
        }

        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            background-color: white;
            cursor: pointer;
            transition: 0.3s;
        }

        select:hover {
            border-color: #4CAF50;
        }
    </style>
    <script>
        function navigateToPage() {
            var selectedPage = document.getElementById("pageSelect").value;
            if (selectedPage) {
                window.location.href = selectedPage;
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>ページ選択</h1>
        <form>
            <label for="pageSelect">移動先を選択してください:</label>
            <select id="pageSelect" onchange="navigateToPage()">
                <option value="">選択してください</option>
                <option value="index_admin.jsp">管理者</option>
                <option value="index_teacher.jsp">教職員</option>
                <option value="index_guardian.jsp">保護者</option>
            </select>
        </form>
    </div>
</body>
</html>
