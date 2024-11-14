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
	<td>${t_student.student_id}</td>
	<td>${t_student.student_name}</td>
	<td>${t_student.flag}</td>
	</tr>
</c:forEach>
</table>
