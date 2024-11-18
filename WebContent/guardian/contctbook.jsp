<div id="a">
    <a>日付</a><input type="text" name="day" id="day" value=""><br>
    <a>体調</a><input type="text" name="taityou"><br>
    <a>体温</a><input type="text" name="taion" value="36.0"><br>
    <a>排便</a><input type="text" name="excretion" value=""><br>
    <a>睡眠時間</a><input type="text" name="sleep_time"><br>
    <a>家庭から</a><br>
    <textarea name="kiduitakoto"></textarea>
</div>
<br>

<form action="#">
<button>送信</button>
</form>

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