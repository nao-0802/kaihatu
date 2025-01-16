<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.StudentRecord, java.util.List"%>


<body>
<header class="header">
    <div class="navtext-container">
      <p class="navtext">生活記録</p>
    </div>
<%@include file="../common/G_header.jsp" %>
</header>

<main>

    <div class="container add-control">
        <input type="checkbox" id="tab1" class="radio" name="tab" onclick="onlyOne(this)"><label class="tab-title title1" id="title1" for="tab1">睡眠</label>
        <input type="checkbox" id="tab2" class="radio" name="tab" onclick="onlyOne(this)"><label class="tab-title title2" id="title2" for="tab2">ごはん</label>
        <input type="checkbox" id="tab3" class="radio" name="tab" onclick="onlyOne(this)"><label class="tab-title title3" id="title3" for="tab3">トイレ</label>
        <input type="checkbox" id="tab4" class="radio" name="tab" onclick="onlyOne(this)"><label class="tab-title title4" id="title4" for="tab4">くすり</label>

        <div class="tab-body" id="body1">
            <form action="../guardian/SleepRecordExecute.action"  method="post" onsubmit="showSuccessAlert();" >
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

        <div class='tab-body' id="body2">
            <form action="../guardian/MealRecordExecute.action" method="post" onsubmit="showSuccessAlert();">
                <table>
                    <tr>
                        <td><label><input type="radio" name="meal_amount" value="0" required>ぜんぶ</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="radio" name="meal_amount" value="1" required>はんぶん</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="radio" name="meal_amount" value="2" required>すこし</label></td>
                    </tr>
                </table>
                <button class="kiroku" name="meal_btn">記録</button>
            </form>
        </div>

        <div class="tab-body" id="body3">
            <form action="../guardian/ExcretionRecordExecute.action" method="post" onsubmit="showSuccessAlert();">
                <table>
                    <tr>
                        <td><label><input type="radio" name="type" value="0">かたい</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="radio" name="type" value="1">やわらかい</label></td>
                    </tr>
                    <tr>
                        <td><label>備考欄:</label>

                    </tr>
                </table>
                <input type="text" name="excretion_detail" placeholder="詳細を記入" oninput="autoCheck(this.value, 'radio1')">
                <br>
                <button class="kiroku" name="excretion_btn">記録</button>
            </form>
        </div>

        <div class="tab-body" id="body4">
            <form action="../guardian/MedicineRecordExecute.action" method="post" onsubmit="showSuccessAlert();">
                <table>
                    <tr>
                        <td><label><input type="radio" name="medicine" value="0" required>服薬済み</label><br></td>
                    </tr>
                </table>

                <br>
                <button class="kiroku" name="medicine_btn">記録</button>
            </form>
        </div>



    </div>

   </main>

</body>


<style>
main{
    margin-top: 52px;
    margin-left:  auto;
    margin-right: auto;
    /* width: 100vw;   */
}

.bb{
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: right;
  width: 100vw;
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
.title4, #body4 {
  border: 2px solid rgb(15, 104, 200);
}

.test{
  display: none;
}

.tab-title {
  width: 20%;
    /* width: 87px; */
    padding: 5px 5px;
    text-align: center;
    display: table;
    margin: 1px;
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

#tab4:checked ~ .title4 {
  background: rgba(0, 140, 255, 0.5);
}

#tab1:checked ~ #body1,
#tab2:checked ~ #body2,
#tab3:checked ~ #body3,
#tab4:checked ~ #body4{
  display: block;
}

form{
    text-align: center;
}



.kiroku{
  width: 100px;
  margin-top: 10px;
}



@media screen and (max-width: 426px) {
  .tab-title{
    width: 35%;
  }
}
</style>


<script>
// 記録成功時にアラートを表示する関数
function showSuccessAlert() {
    alert("記録が正常に保存されました！");
}
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


