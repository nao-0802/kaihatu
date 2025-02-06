<%@ page import="bean.MealRecord, bean.SleepRecord, bean.ExcretionRecord, bean.MedicineRecord" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<body>
<header class="header">
    <div class="navtext-container">
        <p class="navtext">生活記録確認</p>
        </div>
        <%@include file="../common/T_header.jsp" %>
</header>


<main>
    <div class="day">
        <input type="date">
    </div>

    <div class="buttons">
            <form action="../teacher/StudentRecord.action" method="post">
                <button class="back-btn" name="student_id" value="${studentId}">戻る</button>
            </form>
        </div>

    <div class="container add-control">
        <input type="checkbox" id="tab1" class="radio" name="tab" value="1" onclick="onlyOne(this)" onchange="change()"><label class="tab-title title1" id="title1" for="tab1">睡眠</label>
        <input type="checkbox" id="tab2" class="radio" name="tab" value="2" onclick="onlyOne(this)" onchange="change()"><label class="tab-title title2" id="title2" for="tab2">ごはん</label>
        <input type="checkbox" id="tab3" class="radio" name="tab" value="3" onclick="onlyOne(this)" onchange="change()"><label class="tab-title title3" id="title3" for="tab3">トイレ</label>
        <input type="checkbox" id="tab4" class="radio" name="tab" value="4" onclick="onlyOne(this)" onchange="change()"><label class="tab-title title4" id="title4" for="tab4">くすり</label>


    <div class="tab-body" id="body1">
        <table>

             <tr>
                <th>日付</th>
            	<th>時間</th>
            	<th>詳細</th>
             </tr>
             <%
            List<SleepRecord> sleepRecords = (List<SleepRecord>) request.getAttribute("sleepRecords");
            if (sleepRecords != null && !sleepRecords.isEmpty()) {
                for (SleepRecord record : sleepRecords) {
                    String sleepDetail = record.getSleep() == 0 ? "起床" : "就寝";
        %>
        <tr>
            <td><%= record.getDay() %></td>
            <td><%= record.getTime() %></td>
            <td><%= sleepDetail %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3">睡眠記録がありません。</td>
        </tr>
        <%
            }
        %>

        </table>
    </div>

    <div class="tab-body" id="body2">
        <table>

             <tr>
             	<th>日付</th>
            	<th>時間</th>
            	<th>詳細</th>
             </tr>
             <%
            List<MealRecord> mealRecords = (List<MealRecord>) request.getAttribute("mealRecords");
            if (mealRecords != null && !mealRecords.isEmpty()) {
                for (MealRecord record : mealRecords) {
                    String mealDetail;
                    switch (record.getMealAmount()) {
                        case 0:
                            mealDetail = "全量";
                            break;
                        case 1:
                            mealDetail = "半量";
                            break;
                        case 2:
                            mealDetail = "少量";
                            break;
                        default:
                            mealDetail = "不明";
                            break;
                    }
	        %>
	        <tr>
	            <td><%= record.getDay() %></td>
	            <td><%= record.getTime() %></td>
	            <td><%= mealDetail %></td>
	        </tr>
	        <%
	                }
	            } else {
	        %>
	        <tr>
	            <td colspan="3">食事記録がありません。</td>
	        </tr>
	        <%
	            }
	        %>

        </table>
    </div>

    <div class="tab-body" id="body3">
        <table>

             <tr>
             	<th>日付</th>
            	<th>時間</th>
            	<th>詳細</th>
             </tr>
             <%
            List<ExcretionRecord> excretionRecords = (List<ExcretionRecord>) request.getAttribute("excretionRecords");
            if (excretionRecords != null && !excretionRecords.isEmpty()) {
                for (ExcretionRecord record : excretionRecords) {
                    String typeDetail = record.getType() == 0 ? "かたい" : "やわらかい";
                    String excretionDetail = record.getExcretionDetail() != null && !record.getExcretionDetail().isEmpty()
                            ? record.getExcretionDetail()
                            : "なし";
        %>
        <tr>
            <td><%= record.getDay() %></td>
            <td><%= record.getTime() %></td>
            <td><%= typeDetail %> - <%= excretionDetail %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3">排泄記録がありません。</td>
        </tr>
        <%
            }
        %>

        </table>
    </div>

    <div class="tab-body" id="body4">
        <table>

             <tr>
               	<th>日付</th>
            	<th>時間</th>
             </tr>
             <%
            List<MedicineRecord> medicineRecords = (List<MedicineRecord>) request.getAttribute("medicineRecords");
            if (medicineRecords != null && !medicineRecords.isEmpty()) {
                for (MedicineRecord record : medicineRecords) {

        %>
        <tr>
            <td><%= record.getDay() %></td>
            <td><%= record.getTime() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3">睡眠記録がありません。</td>
        </tr>
        <%
            }
        %>

        </table>
    </div>

    <div id="life_record">
        <table>
            <th>
                <tr>
                    <td>life_record</td>
                </tr>
            </th>
        </table>
    </div>
</div>
</main>
</body>


<script>

    function onlyOne(checkbox) {
        var checkboxes = document.getElementsByName('tab');
        checkboxes.forEach((item) => {
            if (item !== checkbox) item.checked = false;
        });
    };

    function change() {
        const content = document.getElementById('life_record');
        const checkboxes = document.querySelectorAll('input[name="tab"]:checked');
        if (checkboxes.length === 0) {
            content.style.display = "block";
        } else {
            content.style.display = "none";
        }
    }

    window.addEventListener('pageshow',()=>{
        if(window.performance.navigation.type==2) location.reload();
      });


</script>


<style>
	body{
	  overflow: hidden;
	}

	main{
	  margin-top: 52px;
	  margin-left:  auto;
	  margin-right: auto;
	  width: 100vw;
	  height: 100%;
	}

	.day{
	  padding: 10px;
	  text-align: center;
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


	.tab-title {
	  width: 20%;
	  padding: 5px 5px;
	  text-align: center;
	  display: table;
	  margin: 1px;
	}

	.tab-body {
	  display: none;
	  width: 80%;
	  padding: 10px;
	  height: 60vh;
	  overflow-y: scroll;
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



	table{
	  text-align: center;
	  margin-left: auto;
	  margin-right: auto;
	  border-collapse: collapse;
	}


	table td{
	  border: 1px solid rgb(0, 0, 0);
	  white-space: nowrap;
	  width: 30%;
	}


	#life_record{
	  margin-top: 15px;
	  text-align: center;
	  margin-left: auto;
	  margin-right: auto;
	  width: 100%;
	  height: 60vh;
	  overflow-y: scroll;
	}

	@media screen and (max-width: 426px) {
	.tab-title{
	  width: 40%;
	}
	.tab-body {
	  height: 50vh;
	}
	#life_record{
	  height: 50vh;
	}
	}

</style>
</html>
