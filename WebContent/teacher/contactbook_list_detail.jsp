<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.ContactBook" %>


<body onload="filterTable()">
<header class="header">
    <div class="navtext-container">
        <p class="navtext">連絡帳</p>
    </div>
    <%@include file="../common/T_header.jsp" %>
</header>

<main>
<div class="test">
    <!-- 生徒名を表示 -->
    <div class="student-info">
        <p>名前: ${list.studentName} (${list.studentId})</p>
    </div>

    <div>
      <input type="month" id="today" min="2020-04" value="">
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
                <td>2025-01-04</td>
            </th>
        </tr>
<tr>
            <th>
                <td>2024-11-04</td>
            </th>
        </tr>
    </table>
    </div>

</div>




    <%
        ContactBook contactBook = (ContactBook) request.getAttribute("contactBook");
        if (contactBook != null) {
    %>

        <p><strong>生徒氏名:</strong> <%= contactBook.getStudentName() %></p><!-- iranai? -->
        <p><strong>日付:</strong> <%= contactBook.getDay() %></p>
        <p><strong>連絡内容:</strong> <%= contactBook.getContactDetails() %></p>
    <%
        } else {
    %>
        <p>連絡帳がありません。</p>
    <%
        }
    %>


</main>
</body>
</html>



<style>

body{
    overflow: hidden;
}

main{
margin-top:52px;
margin-left:auto;
margin-right:auto;
}

.test{
  display: flex;
  flex-direction: column;
  height: 100%;
    width: 100vw;
    text-align:center;
}

input{
    text-align: center;
    width: 200px;
    height: 30px;
}

.m{
    overflow-y: scroll;
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

<script>


	//今日の日時を表示
	function displayDataTime() {
	    //今日の日時を表示
	    var date = new Date()
	    var year = date.getFullYear()
	    var month = date.getMonth() + 1


	    var toTwoDigits = function (num, digit) {
	      num += ''
	      if (num.length < digit) {
	        num = '0' + num
	      }
	      return num
	    }

	    var yyyy = toTwoDigits(year, 4)
	    var mm = toTwoDigits(month, 2)

	    var ymd = yyyy + "-" + mm;

	    return document.getElementById("today").value = ymd;
	}
	displayDataTime();

  //検索.テーブル表示
  function filterTable() {
      const input = document.getElementById("today").value;
      const table = document.getElementById("dataTable");
      const tr = table.getElementsByTagName("tr");

      for (let i = 1; i < tr.length; i++) {
          const td = tr[i].getElementsByTagName("td")[0];
          if (td) {
              const txtValue = td.textContent || td.innerText;
              if (txtValue.indexOf(input) > -1) {
                  tr[i].style.display = "";
              } else {
                  tr[i].style.display = "none";
              }
          }
      }
  }



  document.getElementById('today').addEventListener('input', function() {
      filterTable()},);


</script>

