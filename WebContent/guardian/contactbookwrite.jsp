<%@page contentType="text/html; charset=UTF-8" %>

<head>
    <meta name="viewport" content="width=device-width, initial-sccle=1">
</head>

<style>
body {
    height: 100vh;
    display: flex;
    margin: 0;
}

.header{
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: var(--background-navbar);
  position: fixed;
  width: 100%;
  height: 52px;
  background-color: #757575;
}

main{
	margin-top: 60px;
    margin-left:  auto;
    margin-right: auto;
}


button {
  background-color: #757575;
  height: 50px;
  border: none;
  width: 60px;
  border-right: 1px solid #ddd;
}

.back{
  font-size: 30px;
  color: #ddd;
}

.ren{
  padding-bottom: 10px;
}


table {
    width: 350px;
    border-collapse: collapse;
    margin-bottom: 15px;
  }

  th,
  td {
    vertical-align: middle;
    border: 1px solid #707070;
  }

  th {
    font-weight: normal;
  }


#table01 tr {
    border-bottom: 1px solid #b5b1b1;
  }

  #table01 th,
  #table01 td {
    padding: 12px 0;
    border: none;
  }

  #table01 th {
    width: 30%;
  }

  input[type="text"]{
    width: 110px;
}


textarea{
	resize: none;
	width: 250px;
	height: 50px;
}

input[type="submit"]{
    display: block;
    margin-left: auto;
    width: 60px;
    height: 30px;
}

  /* sp */
  @media screen and (max-width: 480px) {
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

<body>

<header class="header">
    <div class="navivon">
        <button class="back" name=Arrow_btn  onclick="history.back()">←</button>
    </div>
</header>


<main>

<div class="main">
    <div class="ren">
        <form>
            <table id="table01">
                <tr>
                    <th class="a">日付</th>
                    <td><input type="text" name="day" id="day" value=""></td>
                </tr>

                <tr>
                    <th class="a">体調</th>
                    <td><input type="text" name="taityou" required></td>
                </tr>

                <tr>
                    <th class="a">体温</th>
                    <td><input type="text" name="taion"></td>
                </tr>

                <tr>
                    <th class="a">排便</th>
                    <td><input type="text" name="excretion"></td>
                </tr>

                <tr>
                    <th class="a">睡眠時間</th>
                    <td><input type="text" name="sleep_neta"> ~ <input type="text" name="sleep_okita"></td>
                </tr>

                <tr>
                    <th class="a">家庭から</th>
                    <td><textarea name="contact_details" required></textarea></td>
                </tr>
            </table>

            <input type="submit">
        </form>
    </div>
</div>
</main>
</body>


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


    window.addEventListener('pageshow',()=>{
        if(window.performance.navigation.type==2) location.reload();
      });
</script>