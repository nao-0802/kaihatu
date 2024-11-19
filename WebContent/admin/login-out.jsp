<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--%@ include file="../header.html" %-->

<h2>教職員一覧</h2>
<table>
    <tr>
        <td>ID</td>
        <td>名前</td>
    </tr>
    <c:forEach var="t_teacher" items="${teacher}">
        <tr>
            <td>${t_teacher.teacher_id}</td>
            <td>${t_teacher.teacher_name}</td>
            <td><a href="TeacherUpdateExecuteAction?id=${t_teacher.teacher_id}">変更</a></td>
            <td><a href="TeacherDeleteExecuteAction?id=${t_teacher.teacher_id}">削除</a></td>
        </tr>
    </c:forEach>
</table>

<h2>保護者一覧</h2>
<table>
    <tr>
        <td>ID</td>
        <td>名前</td>
    </tr>
    <c:forEach var="t_guardian" items="${guardian}">
        <tr>
            <td>${t_guardian.guardian_id}</td>
            <td>${t_guardian.guardian_name}</td>
            <td><a href="GuardianUpdateExecuteAction?id=${t_guardian.guardian_id}">変更</a></td>
            <td><a href="GuardianDeleteExecuteAction?id=${t_guardian.guardian_id}">削除</a></td>
        </tr>
    </c:forEach>
</table>

<!--%@ include file="../footer.html" %-->
