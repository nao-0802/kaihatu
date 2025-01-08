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
      <input type="month" id="searchTable" min="2020-04" value="">
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


    //月自動入力
    function displayDateTime() {
      const now = new Date();
      const date = new Date();
      const yyyy = date.getFullYear();
      const mm = ('0' + (date.getMonth() + 1)).slice(-2);
      const datetimeString = now.toLocaleString();
      return document.getElementById("searchTable").value = `${yyyy}-${mm}`;
  }
  displayDateTime();

  //検索.テーブル表示
  function filterTable() {
      const input = document.getElementById("searchTable").value;
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



  document.getElementById('searchTable').addEventListener('input', function() {
      filterTable()},);


</script>
