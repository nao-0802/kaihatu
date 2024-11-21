<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--%@ include file="../header.html" %-->

<h2>教職員一覧</h2>
<a href ="TeacherSignup.action">新規登録</a>
<table>
    <tr>
        <td>ID</td>
        <td>名前</td>
    </tr>
    <c:forEach var="t_teacher" items="${teacher}">
        <tr>
            <td>${t_teacher.teacherId}</td>
            <td>${t_teacher.teacherName}</td>
            <td><a href="TeacherUpdate.action?id=${t_teacher.teacherId}">変更</a></td>
            <td><a href="TeacherDelete.action?id=${t_teacher.teacherId}">削除</a></td>
        </tr>
    </c:forEach>
</table>

<h2>保護者一覧</h2>
<a href ="GuardianSignup.action">新規登録</a>
<table>
    <tr>
        <td>ID</td>
        <td>名前</td>
    </tr>
    <c:forEach var="t_guardian" items="${guardian}">
        <tr>
            <td>${t_guardian.guardianId}</td>
            <td>${t_guardian.guardianName}</td>
            <td><a href="GuardianUpdate.action?id=${t_guardian.guardianId}">変更</a></td>
            <td><a href="GuardianDelete.action?id=${t_guardian.guardianId}">削除</a></td>
        </tr>
    </c:forEach>
</table>

<!--%@ include file="../footer.html" %-->
