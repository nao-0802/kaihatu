<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<body>
    <header class="header">
        <div class="navivon">
            <button class="back" name=Arrow_btn  onclick="location.href='contactbook.jsp'">←</button>
        </div>
    </header>
    <main>
        <div class="main">

         <div class="g">
            <table class="book" id="gr">
                <tr>
                    <td>2024-12-24</td>
                    <td>生徒氏名</td>
                </tr>

                <tr>
                    <th class="a">体調</th>
                    <td>良い、鼻水</td>
                </tr>

                <tr>
                    <th class="a">体温</th>
                    <td>36.6</td>
                </tr>

                <tr>
                    <th class="a">排便</th>
                    <td>2024-12-24 <br> 12:00</td>
                </tr>

                <tr>
                    <th class="a">睡眠時間</th>
                    <td>22:00 ~ 7:30</td>
                </tr>

                <tr>
                    <th class="a">家庭から</th>
                    <td>かかれていたことをそのままもってくる。朝ごはんしっかり食べました。</td>
                </tr>
            </table>
         </div>

            <div class="t">
                    <table class="book" id="tr">

                        <tr>
                            <th class="a">給食</th>
                            <td></td>
                        </tr>

                        <tr>
                            <th class="a">排便</th>
                            <td></td>
                        </tr>

                        <tr>
                            <th class="a">学校での様子</th>
                            <td></td>
                        </tr>
                    </table>

            </div>
            </div>
    </main>
</body>


<style>

    body {
        height: 100vh;
        display: flex;
        margin: 0;
    }

    .header {
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
        margin-top: 55px;
        margin-left:  auto;
        margin-right: auto;
    }


.main{
    /* display: flex; */
    height: 100%;
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

    .book th{
        font-weight: normal;
    }


    .book{
        width: 100%;
        border: 1px solid #08a3e5;
        border-collapse: collapse;
    }
    .book td{
        text-align: left;
    }
    .book th {
        width: 35%;
        text-align: center;
    }
    .book td,.book th{
        border: solid 1px #08a3e5;
    }

    #tr{
        border-top: 2px solid #08a3e5;
    }
</style>