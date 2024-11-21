<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>出欠席登録</title>
</head>
<body>
    <h1>出欠席登録</h1>
    <form action="AttendanceCreateExecuteAction.do" method="post">

        <label for="status">出欠席:</label>
        <select id="status" name="status" required>
            <option value="1">出席</option>
            <option value="2">欠席</option>
            <option value="3">遅刻</option>
        </select><br><br>

        <label for="notes">備考:</label>
        <textarea id="notes" name="notes" rows="4" cols="50"></textarea><br><br>

        <button type="submit">Register</button>
    </form>
</body>
</html>