<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--     <script>
        function validateDate() {
            const dateInput = document.getElementById("date");
            const dateValue = dateInput.value;
            const regex = /^\d{4}-\d{2}-\d{2}$/; // YYYY-MM-DD 形式を確認

            if (!regex.test(dateValue)) {
                alert("日付は YYYY-MM-DD の形式で入力してください。");
                return false;
            }
            return true;
        }
    </script>-->




<style>
body{
  overflow: hidden;
}


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
}
.button{
  /* display: inline; */
  padding: 5px;
  margin-right: 10px;
}

 a{
   text-decoration: none;
 }

 .test{
  /* width: 100vw; */
  text-align: center;
  /* display: flex; */
}

input{
  text-align: center;
  width: 200px;
  height: 30px;
}

.test{
  display: flex;
  flex-direction: column;
  height: 100%;
}

.m{
  font-size: 20px;
    /* flex: 1; */
    overflow-y: scroll;
    padding: 10px 20px;
}

table{
  width: 90%;
  font-size: 20px;
  padding: 5px;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 10px;
}

table td{
text-align: center;
  border-bottom: solid 1px red;
}

a{
  color: black;
  text-decoration: none;

}
</style>






<body onload="filterTable()">
   <header class="header">
        <div class="navtext-container">
            <p class="navtext">連絡帳</p>
        </div>
        <%@include file="../common/G_header.jsp" %>

</header>



<main>
  <div class="test">
    <div class="bb">
      <button class="button" onclick="location.href='contactbookwrite.jsp'">連絡帳を書く</button>
    </div>

    <div>
      <input type="month" id="today">
    </div>
    <div class="m">

    <table id="dataTable">
        <tr>
            <th>
                <td hidden>日付</td>
            </th>
        </tr>
        <tr>
          <th>
              <td><a href=""><option value="">2024-11-01</option></a></td>
          </th>
      </tr>

      <tr>
          <th>
              <td>2024-11-02</td>
          </th>
      </tr>
      <tr>
        <th>
            <td><a href=""><option value="">2024-11-01</option></a></td>
        </th>
    </tr>


        <tr>
            <th>
                <td><a href=""><option value="">2024-11-01</option></a></td>
            </th>
        </tr>

        <tr>
            <th>
                <td>2024-11-02</td>
            </th>
        </tr>

        <tr>
            <th>
                <td><a href=""><option value="">2024-11-03</option></a></td>
            </th>
        </tr>

        <tr>
            <th>
                <td>2024-11-04</td>
            </th>
        </tr>
        <tr>
            <th>
                <td>2024-12-24</td>
            </th>
        </tr>
        <tr>
            <th>
                <td><a href="renrakutyou_e.html"><option value="">2024-12-25</option></a></td>
            </th>
        </tr>

    </table>
    </div>

</div>


</main>
</body>


<script>


		//今日の日時を表示
		window.onload = function () {
	    //今日の日時を表示
	    var date = new Date()
	    var year = date.getFullYear()
	    var month = date.getMonth() + 1
	    var day = date.getDate()

	    var toTwoDigits = function (num, digit) {
	      num += ''
	      if (num.length < digit) {
	        num = '0' + num
	      }
	      return num
	    }

	    var yyyy = toTwoDigits(year, 4)
	    var mm = toTwoDigits(month, 2)
	    var dd = toTwoDigits(day, 2)
	    var ymd = yyyy + "-" + mm;

	    document.getElementById("today").value = ymd;
	}


</script>
