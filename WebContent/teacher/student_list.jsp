<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="bean.StudentRecord, java.util.List" %>

<table>
	<tr>
	<td>ID</td>
	<td>名前</td>
	<td>在学フラグ</td>
	</tr>
<c:forEach var="p" items="${list}">
	<tr>
	<td>${p.studentId}</td>
	<td>
		<form action="LifeRecordAction" method="post">
			<input type="hidden" name="student_id" value="${p.Id}">
			<input type="button" name="student_name" value="${p.studentName}">
		</form>
	</td>
	<td>${p.flag}</td>
	</tr>
</c:forEach>
</table>
