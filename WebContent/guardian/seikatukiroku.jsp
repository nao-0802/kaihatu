<%@page contentType="text/html; charset=UTF-8" %>
<!-- %@include file="../G_header.html" % -->
<%@page import="bean.StudentRecord, java.util.List"%>


<style>
summary {
	display: block;
  }

  summary::-webkit-details-marker {
	display: none;
  }

#b {
	background-color: aquamarine;
	width: 500px;
}

#c[open] {
	background-color: aqua;
	width: 500px;
}

</style>

<script>
    function autoCheck(textValue, radioId) {
        document.getElementById(radioId).checked = textValue.length > 0;
    }
</script>

<details>

</details>


<details name="life" id="a">
    <summary>睡眠</summary>
    <form action="../guardian/SleepRecordExecute.action">
        <div>
            <label><input type="radio" name="sleep" value="1">寝た</label><br>
            <label><input type="radio" name="sleep" value="0">起きた</label><br>
            <button>記録</button>
            <input type="hidden" name="guardian_id" value="${guardianID}">
        </div>
    </form>
</details>


<details name="life" id=b>
    <summary>食事</summary>
    <form action="../guardian/MealRecordExecute.action">
        <div>
            <label><input type="radio" name="meal_amount" value="0">全量</label><br>
            <label><input type="radio" name="meal_amount" value="1">半量</label><br>
            <label><input type="radio" name="meal_amount" value="2">少量</label><br>
            <button>記録</button>
            <input type="hidden" name="guardian_id" value="${guardianID}">
        </div>
    </form>
</details>


<details name="life" id="medicine">
    <summary>服薬</summary>
    <form action="../guardian/MedicineRecordExecute.action">
        <div>
            <label><input type="radio" name="medicine" value="0" required>服薬済み</label><br>



            <button type="submit">記録</button>
            <input type="hidden" name="guardian_id" value="${guardianID}">
        </div>
    </form>
</details>



<details name="life" id="c">
    <summary>排泄</summary>
    <form action="../guardian/ExcretionRecordExecute.action" method="post">
        <div>
            <!-- 排泄種別のラジオボタン -->
            <label><input type="radio" name="type" value="0">かたい</label><br>
            <label><input type="radio" name="type" value="1">やわらかい</label><br>

            <!-- その他の詳細を入力するテキストフィールド -->
            <label>その他:</label>
            <input type="text" name="excretion_detail" placeholder="詳細を記入"><br>

            <!-- 隠しフィールド -->
            <input type="hidden" name="guardian_id" value="${guardianID}">
        </div>
        <!-- 提出ボタン -->
        <button>記録</button>
    </form>
</details>



<form action="../guardian/ContactBookWrite.action">
<button>連絡帳を書く</button>
</form>



<!-- <input type="button" onclick="location.href='#'" value="連絡帳を書く"> -->

<!--  %@include file="../footer.html" % -->