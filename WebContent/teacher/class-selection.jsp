<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="ClassSelectExecuteAction" method="post">

	<p>クラスの名前</p>
	<select name="Class_name">
		<c:forEach var="p" items="${list}">
			<option value="${p.class_id}">${p.class_name}</option>
		</c:forEach>
	</select>

	<input type="submit" name="Enter_Room_btn" value="入室">
</form>
