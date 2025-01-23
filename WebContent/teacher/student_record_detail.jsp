<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/T_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>生徒カルテ詳細</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f9f9f9;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .allergies {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h2>生徒カルテ詳細</h2>
    <table>
        <tr>
            <th>生徒氏名</th>
            <td>${studentName}</td>
        </tr>
        <tr>
            <th>保護者氏名</th>
            <td>${guardianName}</td>
        </tr>
        <tr>
            <th>生年月日</th>
            <td>${studentRecord.birthdate}</td>
        </tr>
        <tr>
        	<th>クラス</th>
        	<td>${className}</td>
        </tr>
        <tr>
            <th>特徴</th>
            <td>${studentRecord.features}</td>
        </tr>
    </table>
    <div class="allergies">
        <h3>アレルギー情報</h3>
        <ul>
            <c:forEach var="allergyName" items="${allergyNames}">
                <li>${allergyName}</li>
            </c:forEach>
        </ul>
    </div>

    <div class="button-container">
    <form action="../teacher/StudentRecordUpdate.action" method="get">
        <input type="hidden" name="studentId" value="${studentRecord.studentId}">
        <button type="submit">生徒カルテを更新</button>
    </form>
</div>
</body>
</html>
