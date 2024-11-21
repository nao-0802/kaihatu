<%@page contentType="text/html; charset=UTF-8" %>

<%@page import="bean.ContactBook, java.util.List" %>

<% @SuppressWarnings("unchecked") List<ContactBook> list=(List<ContactBook>)request.getAttribute("list"); %>

<a href="student_list.jsp">←</a>
<p>教職員からの連絡</p>
<input type="text" name="Teacher_Id" value="<%=list.getTeacherId() %>" readonly>
<input type="text" name="G_Contact_Book" value="<%=list.getContactDetails() %>"readonly>

<form action="ContactBookWriteExecuteAction" method="post">

<p>保護者からの連絡</p>

<input type="text" name="ContactBookWrite">
<input type="botton" value="送信">

</form>