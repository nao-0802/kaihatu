<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>アカウント管理</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            margin: 0 auto;
        }

        h2 {
            color: #333;
            border-bottom: 2px solid #4CAF50;
            padding-bottom: 5px;
            margin-bottom: 20px;
        }

        .button-container {
            margin-bottom: 20px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            margin: 5px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            transition: 0.3s;
        }

        button:hover {
            background-color: #45a049;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            margin-right: 10px;
        }

        select {
            padding: 5px;
            font-size: 14px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
            background: white;
        }

        table, th, td {
            border: 1px solid #ddd;
            text-align: left;
        }

        th, td {
            padding: 10px;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a {
            color: #007BFF;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <!-- 新規登録ボタン -->
    <div class="button-container">
        <button onclick="location.href='../scoremanager/TeacherSignup.action'">教職員新規登録</button>
        <button onclick="location.href='../scoremanager/GuardianSignup.action'">保護者新規登録</button>
        <button onclick="location.href='../admin/StudentSignup.action'">生徒新規登録</button>
        <button onclick="location.href='../admin/ClassCreate.action'">クラス新規作成</button>
    </div>

    <h2>教職員一覧</h2>
    <form action="../scoremanager/AccountList.action" method="get">
        <label for="teacherFlag">表示対象:</label>
        <select name="teacherFlag" id="teacherFlag">
            <option value="0" <c:if test="${param.teacherFlag == '0'}">selected</c:if>>有効</option>
            <option value="1" <c:if test="${param.teacherFlag == '1'}">selected</c:if>>無効</option>
        </select>
        <button type="submit">表示</button>
    </form>
    <table>
        <tr>
            <th>ID</th>
            <th>名前</th>
            <th>クラス</th>
            <th>操作</th>
        </tr>
        <c:forEach var="t_teacher" items="${teacher}">
            <tr>
                <td>${t_teacher.teacherId}</td>
                <td>${t_teacher.teacherName}</td>
                <td>${t_teacher.className}</td>
                <td><a href="../scoremanager/TeacherUpdate.action?teacherId=${t_teacher.teacherId}">詳細・変更</a></td>
            </tr>
        </c:forEach>
    </table>

    <h2>保護者一覧</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>名前</th>
            <th>操作</th>
        </tr>
        <c:forEach var="t_guardian" items="${guardian}">
            <tr>
                <td>${t_guardian.guardianId}</td>
                <td>${t_guardian.guardianName}</td>
                <td><a href="../scoremanager/GuardianUpdate.action?guardianId=${t_guardian.guardianId}">詳細・変更</a></td>
            </tr>
        </c:forEach>
    </table>

    <h2>生徒一覧</h2>
    <form action="../scoremanager/AccountList.action" method="get">
        <label for="studentFlag">表示対象:</label>
        <select name="studentFlag" id="studentFlag">
            <option value="0" <c:if test="${param.studentFlag == '0'}">selected</c:if>>有効</option>
            <option value="1" <c:if test="${param.studentFlag == '1'}">selected</c:if>>無効</option>
        </select>
        <button type="submit">表示</button>
    </form>
    <table>
        <tr>
            <th>ID</th>
            <th>名前</th>
            <th>クラス</th>
            <th>操作</th>
        </tr>
        <c:forEach var="t_student" items="${student}">
            <tr>
                <td>${t_student.studentId}</td>
                <td>${t_student.studentName}</td>
                <td>${t_student.className}</td>
                <td><a href="../admin/StudentUpdate.action?studentId=${t_student.studentId}">詳細・変更</a></td>
            </tr>
        </c:forEach>
    </table>

    <form action="../admin/logout_admin.jsp">
        <button>ログアウト</button>
    </form>
</div>

</body>
</html>
