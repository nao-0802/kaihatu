<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>連絡帳作成</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>連絡帳作成</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="ContactBookWriteExecute.action" method="post">
        <!-- 保護者IDと名前の表示 -->
        <input type="hidden" name="guardianId" value="${guardianId}">

        <h3>保護者名: ${guardianName}</h3>

        <!-- 連絡内容の入力 -->
        <label for="contactDetails">連絡内容</label>
        <textarea name="contactDetails" id="contactDetails" rows="10" cols="50" placeholder="連絡内容を記入してください"></textarea>
        <br><br>

        <button type="submit">送信</button>
    </form>

    <br>
    <a href="./StudentListExecute.action">生徒一覧に戻る</a>
</body>
</html>
