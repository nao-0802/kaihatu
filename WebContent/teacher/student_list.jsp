<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<%@ page import="bean.StudentRecord, java.util.List" %>
<%@ page import="bean.Student, java.util.List" %>

<table>
    <p>名前</p>
    <c:forEach var="student" items="${studentList}">
        <form action="LifeRecord.action" method="post">
            <p>${student.studentName}</p>
            <button name="student_id" value="${student.studentId}">生活記録</button>
        </form>
        <form action="StudentRecord.action">
            <button name="student_id" value="${student.studentId}">カルテ</button>
        </form>
        <form action="ContactBookWrite.action">
            <button name="student_id" value="${student.studentId}">連絡帳</button>
        </form>
    </c:forEach>

</table>
<br>

<!-- 生徒全体の出席確認に飛べるボタン -->
<div>
    <form action="Attendance.action" method="post">
        <button type="submit">生徒全体の出席確認</button>
    </form>
</div>
