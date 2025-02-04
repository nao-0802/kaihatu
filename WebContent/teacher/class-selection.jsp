<%@page contentType="text/html; charset=UTF-8" %>

<%@page import="bean.Class, java.util.List" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <meta charset="UTF-8">
    <title>クラス選択</title>
    <style>
        /* 基本のレイアウト設定 */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fb;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* フォームコンテナのスタイル */
        form {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 100%;
            max-width: 400px;
        }

        /* 見出しのスタイル */
        p {
            font-size: 1.2em;
            color: #333;
            margin-bottom: 15px;
        }

        /* セレクトボックス */
        select {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 1em;
            color: #333;
            background-color: #f9f9f9;
            margin-bottom: 20px;
            cursor: pointer;
        }

        select:focus {
            border-color: #6fa3ef;
            outline: none;
        }

        /* 入力ボタン */
        input[type="submit"] {
            background-color: #6fa3ef;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1.1em;
            width: 100%;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #5b8ed7;
        }

        /* 隠しフィールド */
        input[type="hidden"] {
            display: none;
        }
    </style>
</head>

<body>

    <form action="/kaihatu/teacher/ClassSelectExecute.action" method="post">

        <p>クラスの名前</p>
        <select name="class_id">
            <option>---------</option>
            <c:forEach var="p" items="${list}">
                <option value="${p.classId}">${p.className}</option>
            </c:forEach>
        </select>

        <input type="hidden" name="teacherID" value="${teacherID}">
        <input type="submit" name="Enter_Room_btn" value="入室">
    </form>

</body>
</html>
