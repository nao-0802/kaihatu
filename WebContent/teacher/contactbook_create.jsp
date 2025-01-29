<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>





<body>
    <header class="header">
        <div class="navtext-container">
            <p class="navtext">連絡帳</p>
    </div>
<%@include file="../common/G_header.jsp" %>
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
            <th>体調</th>
            <td>良い、鼻水</td>
        </tr>

        <tr>
            <th>体温</th>
            <td>36.6</td>
        </tr>

        <tr>
            <th>排便</th>
            <td>2024-12-24 <br> 12:00</td>
        </tr>

        <tr>
            <th>睡眠時間</th>
            <td>22:00 ~ 7:30</td>
        </tr>

        <tr>
            <th>家庭から</th>
            <td>かかれていたことをそのままもってくる。朝ごはんしっかり食べました。</td>
        </tr>
    </table>
 </div>

    <div class="t">
        <form>
            <table class="book" id="tr">

                <tr>
                    <th>給食</th>
                    <td></td>
                </tr>

                <tr>
                    <th>排便</th>
                    <td></td>
                </tr>

                <tr>
                    <th class="a">学校での様子</th>
                    <td><textarea name="contact_details" required></textarea></td>
                </tr>
            </table>
            <div class="sub">
                <input type="submit">
            </div>

        </form>
    </div>
    </div>
</main>

</body>

<style>
body{
    overflow: hidden;
}

main{
    margin-top: 60px;
    margin-left: auto;
    margin-right: auto;
}

.main{
  margin-left: auto;
  margin-right: auto;
    display: flex;
    height: 100%;
    width: 95%;
}

.book{
  border-collapse: collapse;
  width: 100%;
}
.book td{
    text-align: left;
}
.book th {
    width: 35%;
    text-align: center;
    border-bottom: 1px solid blueviolet;
}
.book td,.book th{
    border: solid 1px blueviolet;
}


.book th{
    font-weight: normal;
}

textarea{
    resize: none;
    width: 100%;
    height: 100px;
}

.sub{
  text-align: right;
}
input[type="submit"]{
  margin: 5px;
  width: 60px;
}
.g{
  width: 60%;
}
.t{
  width: 40%;
}
@media screen and (max-width: 426px) {
    .main{
        flex-direction: column;
    }
    .g{
      width: 100%;
      height: 40%;
      overflow-y: scroll;
  }
  .t{
    width: 100%;
  }
  #tr{
    border-top: 2px solid blueviolet;
  }
}

</style>



<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>連絡帳作成</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>連絡帳作成</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="ContactBookWriteExecute.action" method="post">
        <!-- 保護者IDと名前の表示 -->
        <input type="hidden" name="guardianId" value="${guardianId}">

        <h3>保護者名: ${guardianName}</h3>

        <!-- 連絡内容の入力 -->
        <label for="contactDetails">連絡内容</label>
        <textarea name="contactDetails" id="contactDetails" rows="10" cols="50" placeholder="連絡内容を記入してください"></textarea>
        <br><br>

        <button type="submit">送信</button>
    </form>

    <br>
    <a href="./StudentListExecute.action">生徒一覧に戻る</a>
</body>
</html>
