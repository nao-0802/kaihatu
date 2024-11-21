<%@page contentType="text/html; charset=UTF-8" %>

<%@page import="bean.Class, java.util.List" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<form action="ClassSelectExecuteAction" method="post">

	<p>クラスの名前</p>
	<select name="class_id">
	<option>---------</option>
		<c:forEach var="p" items="${list}">
			<option value="${p.classId}">${p.className}</option>
		</c:forEach>
	</select>

	<input type="submit" name="Enter_Room_btn" value="入室">
</form>
