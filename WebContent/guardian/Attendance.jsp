<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>出欠席登録</title>
</head>
<body>
    <h1>出欠席登録</h1>
    <form action="AttendanceCreateExecute.action" method="post">
        <fieldset>
            <legend>出欠席情報</legend>

            <!-- 出欠席: プルダウン -->
            <label for="type">出欠席:</label>
            <select id="type" name="type" required>
                <option value="0">欠席</option>
                <option value="1">遅刻</option>
                <option value="2">早退</option>
            </select>
            <br><br>

            <!-- 備考 -->
            <label for="notes">備考:</label>
            <textarea id="notes" name="notes" rows="4" cols="50"></textarea>
            <br><br>

            <!-- 登録ボタン -->
            <button type="submit">登録</button>
        </fieldset>
    </form>
</body>
</html>
