<%@page contentType="text/html; charset=UTF-8" %>
<!-- %@include file="../header.html" % -->

<table>
<c:forEach var="a" items="${list}">
	<tr>
	<td>名前</td>
	<td>${a.student_name}</td>
	<td>${a.status}</td>
	<td>備考</td>
	<td>${notes}</td>
</c:forEach>
<!-- %@include file="../footer.html" % -->
