<%@page contentType="text/html; charset=UTF-8" %>
<!-- %@include file="../header.html" % -->
<!-- %@include file="akaunto.jsp" % -->

<table>
<c:forEach var="t_teacher" items="${list}">
	<tr>
	<td>ID</td>
	<td>名前</td>
	</tr>
	<tr>
	<td>${t_teacher.teacher_id}</td>
	<td>${t_teacher.teacher_name}</td>
	</tr>
</c:forEach>
</table>

<!-- %@include file="../footer.html" % -->
