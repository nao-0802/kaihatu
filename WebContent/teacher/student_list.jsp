<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
	<tr>
	<td>ID</td>
	<td>名前</td>
	<td>在学フラグ</td>
	</tr>
<c:forEach var="t_teacher" items="${list}">
	<tr>
	<td name="student_id">${t_student.student_id}</td>
	<td>
		<form action="LifeRecordAction" method="post">
			<input type="button" name="student_name" value="${t_student.student_name}">
		</form>
	</td>
	<td>${t_student.flag}</td>
	</tr>
</c:forEach>
</table>
