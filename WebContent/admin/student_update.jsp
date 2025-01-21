<%-- 教職員情報更新 JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            width: 400px;
            box-sizing: border-box;
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
        input[type="text"], input[type="password"], select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .btn {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #218838;
        }
        .btn-back {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            display: inline-block;
            text-align: center;
            margin-top: 10px;
            box-sizing: border-box;
        }
        .btn-back:hover {
            background-color: #0056b3;
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
        <%-- エラーメッセージ表示 --%>
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>

        <form action="../admin/StudentUpdateExecute.action" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="student-id">生徒ID</label>
                <input id="student-id" type="text" name="studentId" value="${gid}" readonly />
            </div>
            <div class="form-group">
                <label for="student-name">氏名</label>
                <input id="student-name" type="text" name="studentName" value="${name}" maxlength="30" required />
            </div>
            <div class="form-group">
               <label for="student-class">クラス名</label>
                <select class="form-control" name="className" required>
                    <option value="">-- クラス名を選択 --</option>
                    <c:forEach var="className" items="${classNames}">
                        <option value="${className}" <c:if test="${className == student.className}">selected</c:if>>${className}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="student-flag">有効/無効</label>
                <select id="student-flag" name="flag" required>
                    <option value="1" ${flag == 1 ? 'selected' : ''}>無効</option>
                    <option value="0" ${flag == 0 ? 'selected' : ''}>有効</option>
                </select>
            </div>
            <button type="submit" class="btn">変更</button>
            <a href="../scoremanager/AccountList.action" class="btn-back">戻る</a>
        </form>
    </div>

    <script>
        function validateForm() {
            const teacherName = document.getElementById('student-name').value;
            const classId = document.getElementById('student-class').value;

            if (!teacherName || !password || classId === "0") {
                alert('すべてのフィールドを正しく入力してください。');
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
