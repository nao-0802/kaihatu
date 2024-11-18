<%@page contentType="text/html; charset=UTF-8" %>

<form action="#">
<div id="a">
    <a>日付</a><input type="text" name="day" id="day" value=""><br>
    <a>体調</a><input type="text" name="taityou" required><br>
    <a>体温</a><input type="text" name="taion" value="36.0"><br>
    <a>排便</a><input type="text" name="excretion"><br>
    <a>睡眠時間</a><input type="text" name="sleep_neta"> ～ <input type="text" name="sleep_okita"><br>
    <!-- 生活記録から取得：排便、睡眠(就寝/起床) -->
    <a>家庭から</a><br>
    <textarea name="contact_details"></textarea>
</div>
<br>

<input type="submit">
</form>

<!-- 日付自動入力 -->
<script>
    function displayDateTime() {
        const now = new Date();
        const date = new Date();
        const yyyy = date.getFullYear();
        const mm = ('0' + (date.getMonth() + 1)).slice(-2);
        const dd = ('0' + date.getDate()).slice(-2);
        const datetimeString = now.toLocaleString();
        return document.getElementById("day").value = `${yyyy}/${mm}/${dd}`;
    }

    displayDateTime();
</script>