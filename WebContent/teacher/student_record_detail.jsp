<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>生徒カルテ</title>
</head>
<body>
    <h2>${student.studentName}のカルテ</h2>

    <h3>生徒情報</h3>
    <p>生徒ID: ${student.studentId}</p>
    <p>誕生日: ${student.birthDate}</p>
    <p>特徴: ${student.features}</p>

    <h3>保護者情報</h3>
    <ul>
        <c:forEach var="guardian" items="${glist}">
            <li>${guardian.name} (${guardian.relationship})</li>
        </c:forEach>
    </ul>

    <h3>カルテ情報</h3>
    <ul>
        <c:forEach var="record" items="${recordList}">
            <li>日付: ${record.date}, 内容: ${record.description}</li>
        </c:forEach>
    </ul>

    <h3>アレルギー情報</h3>
    <ul>
        <c:forEach var="allergy" items="${allergyList}">
            <li>${allergy.allergyName}</li>
        </c:forEach>
    </ul>

    <h3>クラス情報</h3>
    <ul>
        <c:forEach var="className" items="${classList}">
            <li>${className}</li>
        </c:forEach>
    </ul>
</body>
</html>
