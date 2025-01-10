<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.StudentRecord, java.util.List"%>
<head>
    <meta charset="UTF-8">
    <title>生活記録</title>
    <script>
        // 記録成功時にアラートを表示する関数
        function showSuccessAlert() {
            alert("記録が正常に保存されました！");
        }
    </script>
</head>


<style>
@media only screen and (max-width: 769px){
main{
    margin-top: 52px;
    margin-left:  auto;
    margin-right: auto;
}

.bb{
  /* display: block; */
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: right;
  width: 100vw;
  /* background-color: aqua; */
}
.button{
  /* display: inline; */
  padding: 5px;
  margin-right: 10px;
}


.title1, #body1 {
    border: 2px solid rgb(0, 128, 0);
}
.title2, #body2 {
    border: 2px solid rgb(230, 230, 0);
}
.title3, #body3 {
    border: 2px solid rgb(200, 15, 15);
}

.tab-title {
    width: 85px;
    padding: 5px 5px;
    text-align: center;
    display: table;
}

.tab-body {
  display: none;
    width: 220px;
    height: 150px;
    padding: 10px;
}

/* radio non-display */
.container .radio {
  display: none;
}

/* tabs position */
.container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    /* background-color: violet; */
}

.container::after {
    content: "";
    width: 100%;
}

/* .container .tab-title {
    position: relative;
} */

.container .tab-body {
    order: 1;
    margin-top: 10px;
}

/* tab's body init */


/* tab's color */
.add-control .radio:checked + .tab-title {
    color: #000000;
}

#tab1:checked ~ .title1 {
    background: rgba(0, 128, 0, 0.5);
}

#tab2:checked ~ .title2 {
    background: rgba(255, 255, 0, 0.5);
}

#tab3:checked ~ .title3 {
    background: rgba(255, 0, 0, 0.5);
}

#tab1:checked ~ #body1,
#tab2:checked ~ #body2,
#tab3:checked ~ #body3{
  display: block;
}

form{
    text-align: center;
}



.kiroku{
  width: 100px;
  margin-top: 10px;
}
}
</style>

<script>
function onlyOne(checkbox) {
    var checkboxes = document.getElementsByName('tab');
    checkboxes.forEach((item) => {
        if (item !== checkbox) item.checked = false;
    });
}


function autoCheck(textValue, radioId) {
    document.getElementById(radioId).checked = textValue.length > 0;
}

window.addEventListener('pageshow',()=>{
    if(window.performance.navigation.type==2) location.reload();
  });
</script>


<body>
<header class="header">
    <div class="navtext-container">
      <p class="navtext">生活記録</p>
    </div>
<%@include file="../common/G_header.jsp" %>
</header>

<main>

    <div class="bb">
        <button class="button" onclick="location.href='contactbookwrite.jsp'">連絡帳を書く</button>
    </div>

    <div class="container add-control">
        <input type="checkbox" id="tab1" class="radio" name="tab" onclick="onlyOne(this)"><label class="tab-title title1" id="title1" for="tab1">睡眠</label>
        <input type="checkbox" id="tab2" class="radio" name="tab" onclick="onlyOne(this)"><label class="tab-title title2" id="title2" for="tab2">ごはん</label>
        <input type="checkbox" id="tab3" class="radio" name="tab" onclick="onlyOne(this)"><label class="tab-title title3" id="title3" for="tab3">トイレ</label>

        <div class="tab-body" id="body1">
            <form action="../guardian/SleepRecordExecute.action"  method="post" onsubmit="showSuccessAlert();">
                <table>
                    <tr>
                        <td><label><input type="radio" name="sleep" value="0" required>寝た</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="radio" name="sleep" value="1" required>起きた</label></td>
                    </tr>
                </table>
                <button class="kiroku" name="sleep_btn">記録</button>
            </form>
        </div>

         <!-- 食事 -->
        <details name="life" id="b">
            <summary>食事</summary>
            <form action="../guardian/MealRecordExecute.action" method="post" onsubmit="showSuccessAlert();">

                <div>
                    <label><input type="radio" name="meal_amount" value="0">全量</label><br>
                    <label><input type="radio" name="meal_amount" value="1">半量</label><br>
                    <label><input type="radio" name="meal_amount" value="2">少量</label><br>
                    <button type="submit">記録</button>
                </div>
            </form>
        </details>

        <div class="tab-body" id="body3">

        <!-- 排泄 -->
        <details name="life" id="c">
            <summary>排泄</summary>
            <form action="../guardian/ExcretionRecordExecute.action" method="post" onsubmit="showSuccessAlert();">

                <div>
                    <!-- 排泄種別のラジオボタン -->
                    <label><input type="radio" name="type" value="0">かたい</label><br>
                    <label><input type="radio" name="type" value="1">やわらかい</label><br>

                    <!-- その他の詳細を入力するテキストフィールド -->
                    <label>その他:</label>
                    <input type="text" name="excretion_detail" placeholder="詳細を記入"><br>
                </div>
                <!-- 提出ボタン -->
                <button type="submit">記録</button>
                </form>
           </details>
        </div>
    </div>
<!-- 服薬 -->
        <details name="life" id="medicine">
            <summary>服薬</summary>
            <form action="../guardian/MedicineRecordExecute.action" method="post" onsubmit="showSuccessAlert();">

                <div>
                    <label><input type="radio" name="medicine" value="0" required>服薬済み</label><br>
                    <button type="submit">記録</button>
                </div>
            </form>
        </details>


   </main>
</body>






<form action="../guardian/ContactBookWrite.action">
<button>連絡帳を書く</button>
</form>

<form action="../guardian/ContactBookList.action">
<button>連絡帳を見る</button>
</form>



<form action="AttendanceCreate.action">
<button>出席連絡</button>
</form>



