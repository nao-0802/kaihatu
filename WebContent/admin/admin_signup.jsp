<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>管理者アカウント登録</title>
</head>
<body>
    <h2>管理者アカウント登録</h2>

    <!-- エラーメッセージがあれば表示 -->
    <c:if test="${not empty errors}">
        <div style="color: red;">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <form action="../admin/AdminSignupExecute.action" method="post" autocomplete="off">
        <p>
            マスターアカウントID:
            <input type="text" name="master_id" required autocomplete="off">
        </p>
        <p>
            マスターアカウントパスワード:
            <input type="password" name="master_password" required autocomplete="new-password">
        </p>
        <p>
            管理者ID:
            <input type="text" name="admin_id" required>
        </p>
        <p>
            パスワード:
            <input type="password" name="password" required>
        </p>
        <button type="submit">登録</button>
    </form>
</body>
</html>
