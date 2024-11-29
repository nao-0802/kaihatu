<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>出欠状況確認</title>
</head>
<body>
    <h1>出欠状況一覧</h1>

    <!-- クラスごとに出欠状況を表示 -->
    <c:forEach var="attendance" items="${attendanceList}">
        <div>
            <h3>生徒: ${attendance.studentName}</h3>
            <p>日付: ${attendance.day}</p>
            <p>出席状況: ${attendance.type}</p>
        </div>
        <hr />
    </c:forEach>
</body>
</html>
