<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>全体掲示作成</title>
</head>
<body>
    <h2>全体掲示作成</h2>
    <form action="../teacher/BulletionBoardExecute.action" method="post">
        <label for="title">タイトル:</label><br>
        <input type="text" id="title" name="title" required><br><br>

        <label for="content">内容:</label><br>
        <textarea id="content" name="content" rows="5" cols="30" required></textarea><br><br>

        <input type="submit" value="投稿">
    </form>
</body>
</html>
