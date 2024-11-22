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
            <!-- student_id を使用 -->
            <td>${p.student_id}</td>
            <td>
                <form action="LifeRecordAction" method="post">
                    <!-- student_id を hidden フィールドとして送信 -->
                    <input type="hidden" name="student_id" value="${p.student_id}">
                    <!-- name フィールドを表示 -->
                    <input type="button" name="student_name" value="${p.name}">
                </form>
            </td>
            <!-- flag プロパティを表示 -->
            <td>${p.flag}</td>
        </tr>
    </c:forEach>
</table>
