<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--%@ include file="../header.html" %-->

<h2>教職員一覧</h2>
<a href ="TeacherSignup.action">新規登録</a>
<table>
    <tr>
        <td>ID</td>
        <td>名前</td>
        <td>クラス</td>
    </tr>
    <c:forEach var="t_teacher" items="${teacher}">
        <tr>
            <td>${t_teacher.teacherId}</td>
            <td>${t_teacher.teacherName}</td>
            <td>${t_teacher.className}</td>
            <td><a href="TeacherUpdate.action?teacherId=${t_teacher.teacherId}">詳細・変更</a></td>
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
            <td><a href="GuardianUpdate.action?guardianId=${t_guardian.guardianId}">詳細・変更</a></td>
        </tr>
    </c:forEach>
</table>

<form action="../admin/logout_admin.jsp">
        	<button>ログアウト</button>
        </form>
<!--%@ include file="../footer.html" %-->
