<%@page contentType="text/html; charset=UTF-8" %>
<!-- %@include file="../header.html" % -->

<table>
<c:forEach var="t_teacher" items="${list}">
	<tr>
	<td>ID</td>
	<td>名前</td>
	</tr>
	<tr>
	<td>${t_teacher.teacher_id}</td>
	<td>${t_teacher.teacher_name}</td>
	<!-- actionいれる -->
	<td><a href="?=id{t_teacher.teacher_id}">変更</a></td>
	<td><a href="?=id{t_teacher.teacher_id}">削除</a></td>
	</tr>
</c:forEach>
</table>

<!-- %@include file="../footer.html" % -->
