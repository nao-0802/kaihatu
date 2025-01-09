<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>生活記録作成成功</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

    <h2>生活記録が正常に保存されました。</h2>
    <p>生活記録が正常に送信されました。</p>

    <!-- セッションから学生情報を取得して名前とIDを表示 -->
    <p>名前: ${sessionScope.student.studentName} (${sessionScope.student.studentId})</p>

    <!-- 生活記録一覧に戻るリンク -->
    <a href="./LifeRecord.action?student_id=${sessionScope.student.studentId}">生活記録一覧に戻る</a>

</body>
</html>
	