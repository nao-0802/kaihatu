<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
<header class="header">
    <h1>出席状況</h1>
</header>

<main>
    <h2>クラスの出席状況</h2>

    <!-- 出席情報が空の場合にメッセージを表示 -->
    <c:if test="${empty attendanceList}">
        <p>現在、出席情報はありません。</p>
    </c:if>

    <!-- 出席情報がある場合、繰り返し表示 -->
    <c:forEach var="attendance" items="${attendanceList}">
        <div>
            <p>生徒ID: ${attendance.studentId}</p> <!-- studentIdのプロパティ -->
            <p>生徒名: ${attendance.studentName}</p> <!-- studentNameのプロパティ -->
            <p>日付: ${attendance.day}</p> <!-- dayのプロパティ -->
            <p>出席状況: ${attendance.type}</p> <!-- typeのプロパティ -->
            <p>症状: ${attendance.symptom}</p> <!-- symptomのプロパティ -->
            <p>理由: ${attendance.reason}</p> <!-- reasonのプロパティ -->
            <p>備考: ${attendance.notes}</p> <!-- notesのプロパティ -->

            <!-- 遅刻または早退の場合、時間を表示 -->
            <c:choose>
                <c:when test="${attendance.type == '遅刻'}">
                    <p>遅刻時間: ${attendance.lateTime}</p> <!-- 遅刻時間 -->
                </c:when>
                <c:when test="${attendance.type == '早退'}">
                    <p>早退時間: ${attendance.earlyLeaveTime}</p> <!-- 早退時間 -->
                </c:when>
            </c:choose>
        </div>
        <hr />
    </c:forEach>
</main>
</body>
