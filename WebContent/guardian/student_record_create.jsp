<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<form action="../guardian/StudentRecordCreateExecute.action" method="post">
    <label>生徒名:</label>
    <select name="student_id" required>
        <% for (Student student : students) { %>
            <option value="<%= student.getStudentId() %>"><%= student.getStudentName() %></option>
        <% } %>
    </select>
    <label>生年月日:</label>
    <input type="date" name="birthdate" required>
    <label>アレルギー:</label>
    <input type="text" name="allergy">
    <button type="submit">登録</button>
</form>
