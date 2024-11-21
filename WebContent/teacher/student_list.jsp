<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
	<tr>
	<td>ID</td>
	<td>名前</td>
	<td>在学フラグ</td>
	</tr>
<c:forEach var="t_student" items="${list}">
	<tr>
	<td>${t_student.studentId}</td>
	<td>
		<form action="LifeRecordAction" method="post">
			<input type="hidden" name="student_id" value="${ t_student.studentId}">
			<input type="button" name="student_name" value="${t_student.studentName}">
		</form>
	</td>
	<td>${t_student.flag}</td>
	</tr>
</c:forEach>
</table>
