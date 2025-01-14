
<%@page contentType="text/html; charset=UTF-8" %>

<body>
<header class="header">
    <div class="navtext-container">
        <p class="navtext">欠席等連絡</p>
    </div>

    <%@include file="../common/G_header.jsp" %>
</header>

<main>

  <div>
    <form action="AttendanceCreateExecute.action" method="post">
        <table id="table0">
            <tr>
                <th class="a">日付</th>
                <td><input type="date" name="day" id="day" value=""></td>
            </tr>
        </table>

        <table id="table01">
            <tr>
                <th class="a">連絡内容</th>
                <td>
                    <select id="type" name="type" required onchange="aviewChange();">
                        <option value="" selected disabled>選択してください</option>

                        <option value="0">欠席</option>
                        <option value="1" name="Tardiness">遅刻</option>
                        <option value="2" name="Early">早退</option>
                    </select>
                </td>
            </tr>
           </table>

            <div class="notificationTime">
                <div id="Tardiness" style="display:none;">
                    <table>
                        <tr>
                            <th class="a">登校する時間</th>
                            <td><input type="time"  name="notificationTime"></td>
                        </tr>
                    </table>
                </div>

                <div id="Early" style="display:none;">
                    <table>
                        <tr>
                            <th class="a">早退する時間</th>
                            <td><input type="time"  name="notificationTime"></td>
                        </tr>
                    </table>
                </div>
            </div>


        <table id="table01">
            <tr>
                <th class="a">理由</th>
                <td>
                    <div class="reason">
                        <select class="form-reason" id="reason" required onchange="viewChange();">
                            <option value="" selected disabled>選択してください</option>
                            <option value="reason1">体調不良</option>
                            <option value="reason2">通院のため</option>
                            <option value="reason3">家庭の事情</option>
                        </select>
                    </div>
                </td>
            </tr>
        </table>

        <div class="reason_remarks">
            <div id="Box1" style="display:none;">
                <a>当てはまる症状を選択してください。</a>
                <table>
                    <tr>
                        <td>
                            <label><input type="checkbox">かぜ</label>
                        </td>
                        <td>
                            <label><input type="checkbox">発熱</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label><input type="checkbox">咳</label>
                        </td>
                        <td>
                            <label><input type="checkbox">鼻水・くしゃみ</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label><input type="checkbox">腹痛</label>
                        </td>
                        <td>
                            <label><input type="checkbox">嘔吐・下痢</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label><input type="checkbox">頭痛</label>
                        </td>
                        <td>
                            <label><input type="checkbox">発作</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label><input type="checkbox">感染予防</label>
                        </td>
                        <td>
                            <label><input type="checkbox">その他</label>
                        </td>
                    </tr>
                </table>
                </div>

            </div>

             <a><label for="notes">備考欄</label></a><br>

         <textarea id="notes" name="notes" rows="4" cols="50" placeholder="その他連絡事項をご記入ください。"></textarea>

        <input type="submit">
    </form>
</div>

</main>
</body>

<style>
main{
    margin-top: 52px;
    margin-left:  auto;
    margin-right: auto;
}

.back{
  margin-top: 5px;
}

button{
  height: 30px;
}

table {
    width: 300px;
    border-collapse: collapse;
    margin-top: 5px;
    margin-bottom: 5px;
  }

  th,
  td {
    vertical-align: middle;
  }

  th {
    font-weight: normal;
  }

#table01 tr {
    border-top: 1px solid #b5b1b1;
  }

  #table0 th,
  #table0 td {
    padding: 12px 0;
    border: none;
  }

  #table0 th {
    width: 50%;
  }

#table01 th,
#table01 td {
  padding: 12px 0;
  border: none;
}

  #table01 th {
    width: 50%;
  }

textarea{
    resize: none;
    width: 300px;
    height: 50px;
  margin-bottom: 5px;
}

input[type="submit"]{
    display: block;
    margin-left: auto;
    width: 60px;
    height: 30px;
}

.reason_remarks{
  margin-bottom: 15px;
}

form{
  padding-bottom: 40px;
}

/* sp */
@media only screen and (max-width: 480px) {
    #table0 th,
    #table0 td {
      width: 100%;
      display: block;
    }

    #table0 th {
      width: 100%;
    }

    #table0 td {
      padding-top: 0;
      margin-left: auto;
      margin-right: auto;
      text-align: center;
    }

    #table01 th,
    #table01 td {
      width: 100%;
      display: block;
    }

    #table01 th {
      width: 100%;
    }

    #table01 td {
      padding-top: 0;
      margin-left: auto;
      margin-right: auto;
      text-align: center;
    }
}
</style>

<script>
    function displayDateTime() {
        const now = new Date();
        const date = new Date();
        const yyyy = date.getFullYear();
        const mm = ('0' + (date.getMonth() + 1)).slice(-2);
        const dd = ('0' + date.getDate()).slice(-2);
        const datetimeString = now.toLocaleString();
        return document.getElementById("day").value = `${yyyy}-${mm}-${dd}`;
    }

    displayDateTime();

    function aviewChange(){
        if(document.getElementById('renraku')){
            id = document.getElementById('renraku').value;
            if(id == 'renraku2'){
                document.getElementById('renraku2').style.display = "";
                document.getElementById('renraku3').style.display = "none";
            }
            else if(id == 'renraku3'){
                document.getElementById('renraku2').style.display = "none";
                document.getElementById('renraku3').style.display = "";
            }
            else if(id != 'renraku2' && id != "renraku3"){
                document.getElementById('renraku2').style.display = "none";
                document.getElementById('renraku3').style.display = "none";
            }
        }

    window.onload = aviewChange;
    }

    function viewChange(){
        if(document.getElementById('reason')){
            id = document.getElementById('reason').value;
            if(id == 'reason1'){
                document.getElementById('Box1').style.display = "";
            }
            else if(id != 'reason1'){
                document.getElementById('Box1').style.display = "none";
            }
        }

    window.onload = viewChange;
    }

    window.addEventListener('pageshow',()=>{
        if(window.performance.navigation.type==2) location.reload();
    });
</script>

