<%@page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<select name="Grade_sel">

</select>
<p>年</p>
<select name="Class_sel">
<c:forEach var="p" items="${list}">
	${p.classnum }
</c:forEach>
</select>
<p>組</p>
<input type="button" name="Enter_Roon_btn" value="入室">