<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>クラス作成</title>
</head>
<body>
    <h1>クラス作成</h1>
    <form action="../admin/ClassCreateExecute.action" method="post">
        <label for="className">クラス名:</label>
        <input type="text" id="className" name="className" required>
        <button type="submit">作成</button>
    </form>
    <a href="../scoremanager/AccountListAction">戻る</a>
</body>
</html>
