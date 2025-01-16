<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>管理者ログアウト</title>
</head>
<body>
    <h2>ログアウトしますか？</h2>
    <form action="../scoremanager/LogoutExecute.action" method="post">
        <button type="submit">はい</button>
    </form>
    <form action="../scoremanager/AccountList.action" method="get">
        <button type="submit">いいえ</button>
    </form>
</body>
</html>
