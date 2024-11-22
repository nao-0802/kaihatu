<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<%@page import="bean.StudentRecord, java.util.List" %>

<table>
    <tr>
        <td>名前</td>
    </tr>
    <c:forEach var="p" items="${list}">
        <tr>
            <td>
                <form action="LifeRecordAction" method="post">
                    <!-- name フィールドを表示 -->
                    <input type="button" name="student_name" value="${p.name}">
                    <p>${p.birthdate}</p>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
