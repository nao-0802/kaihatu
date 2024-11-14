<%@page contentType="text/html; charset=UTF-8" %>
<!-- %@include file="../header.html" % -->
<!-- %@include file="../menu.html" % -->

<details id=sleep> <summary>睡眠</summary>
    <form action="sleep.action">
        <label><input type="radio" name="sleep_amount" value="寝た">寝た</label><br>
        <label><input type="radio" name="sleep_amount" value="起きた">起きた</label><br>
        <button name="sleep_btn">記録</button>
    </form>
</details>

<details id=meal> <summary>食事</summary>
    <form action="MealRecordAction">
        <label><input type="radio" name="meal_amount" value="全量">全量</label><br>
        <label><input type="radio" name="meal_amount" value="半量">半量</label><br>
        <label><input type="radio" name="meal_amount" value="少量">少量</label><br>
        <button name="meal_btn">記録</button>
    </form>
</details>

<details id=excretion> <summary>排泄</summary>
    <form action="excretion.action">
        <details id=joutai>
            <summary>
            <span class="close">状態</span>
            </summary>
            <label><input type="radio" name="excretion_amount" value="ころ">ころころ</label><br>
            <label><input type="radio" name="excretion_amount" value="mizu">水っぽい</label><br>
            <label><input type="radio" name="excretion_amount" value="その他">その他</label><br>
            <details id=sonota>
            <summary>
                <span class="open"></span>
            </summary>
            <input type="text" name="excretion_amount">
            </details>
        </details>
        <button name="excretion_btn">記録</button>
    </form>
</details>

<!--  %@include file="../footer.html" % -->