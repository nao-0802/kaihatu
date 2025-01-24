<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 新規登録ボタン -->
<div style="margin-bottom: 20px;">
    <button onclick="location.href='../scoremanager/TeacherSignup.action'">教職員新規登録</button>
    <button onclick="location.href='../scoremanager/GuardianSignup.action'">保護者新規登録</button>
    <button onclick="location.href='../admin/StudentSignup.action'">生徒新規登録</button>
    <button onclick="location.href='../admin/ClassCreate.action'">クラス新規作成</button>
</div>

<h2>教職員一覧</h2>
<form action="../scoremanager/AccountList.action" method="get">
    <label for="teacherFlag">表示対象:</label>
    <select name="teacherFlag" id="teacherFlag">
        <option value="0" <c:if test="${param.teacherFlag == '0'}">selected</c:if>>有効</option>
        <option value="1" <c:if test="${param.teacherFlag == '1'}">selected</c:if>>無効</option>
    </select>
    <button type="submit">表示</button>
</form>
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
            <td><a href="../scoremanager/TeacherUpdate.action?teacherId=${t_teacher.teacherId}">詳細・変更</a></td>
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
            <td>${t_guardian.guardianId}</td>
            <td>${t_guardian.guardianName}</td>
            <td><a href="../scoremanager/GuardianUpdate.action?guardianId=${t_guardian.guardianId}">詳細・変更</a></td>
        </tr>
    </c:forEach>
</table>

<h2>生徒一覧</h2>
<form action="../scoremanager/AccountList.action" method="get">
    <label for="studentFlag">表示対象:</label>
    <select name="studentFlag" id="studentFlag">
        <option value="0" <c:if test="${param.studentFlag == '0'}">selected</c:if>>有効</option>
        <option value="1" <c:if test="${param.studentFlag == '1'}">selected</c:if>>無効</option>
    </select>
    <button type="submit">表示</button>
</form>
<table>
    <tr>
        <td>ID</td>
        <td>名前</td>
        <td>クラス</td>
    </tr>
    <c:forEach var="t_student" items="${student}">
        <tr>
            <td>${t_student.studentId}</td>
            <td>${t_student.studentName}</td>
            <td>${t_student.className}</td>
            <td><a href="../admin/StudentUpdate.action?studentId=${t_student.studentId}">詳細・変更</a></td>
        </tr>
    </c:forEach>
</table>

<form action="../admin/logout_admin.jsp">
    <button>ログアウト</button>
</form>
