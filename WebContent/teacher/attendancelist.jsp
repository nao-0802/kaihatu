<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.Student" %>
<%@page import="bean.Attendance" %>
<%@page import="java.util.List" %>

<h2>生徒の出席情報</h2>

<table border="1">
    <thead>
        <tr>
            <th>生徒名</th>
            <th>出席状態</th>
            <th>備考</th>
            <th>出席日</th>
        </tr>
    </thead>
    <tbody>
        <!-- 生徒リストのループ -->
        <c:forEach var="student" items="${studentList}">
            <tr>
                <td>${student.studentName}</td>
                <td>
                    <!-- 生徒ごとの出席情報の表示 -->
                    <c:forEach var="attendance" items="${student.attendanceList}">
                        <c:choose>
                            <c:when test="${attendance.status == 0}">出席</c:when>
                            <c:when test="${attendance.status == 1}">欠席</c:when>
                            <c:when test="${attendance.status == 2}">遅刻</c:when>
                            <c:when test="${attendance.status == 3}">早退</c:when>
                        </c:choose><br>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="attendance" items="${student.attendanceList}">
                        ${attendance.notes}<br>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="attendance" items="${student.attendanceList}">
                        ${attendance.date}<br>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
