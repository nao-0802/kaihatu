<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<head>
    <meta name="viewport" content="width=device-width, initial-sccle=1">
</head>


<style>
@media only screen and (max-width: 769px){
body{
    overflow: hidden;
 }
  main{
    margin-top: 52px;
    margin-left:  auto;
    margin-right: auto;
   }

   .back{
    padding: 5px;
    /* background-color: yellowgreen; */
  }

  button{
    height: 30px;
  }

.title{
  text-align: left;
  padding-left: 10px;
  background-color: rgb(122, 227, 227);
  font-size: 20px;

}

.more-btn {
  cursor: pointer;
}

.title-more {
  overflow: hidden;
  height: 28px;

}
.more-check {
  display: none;
}

.more-check:checked ~ .title-more {
  height: auto;
}


.bulletin_board{
  display: flex;
  flex-direction: column;
  height: 100%;
}

.text{
  font-size: 20px;
    /* flex: 1; */
    overflow-y: auto;
    padding: 10px 20px;
    word-break: break-all;
}
}
</style>

<body>
<header class="header">
    <div class="navtext-container">
      <p class="navtext">全体掲示板</p>
    </div>
<%@include file="../common/G_header.jsp" %>
</header>


<main>
<div  class="bulletin_board">
    <div class="back">
      <div class="back_left">
        <button class="a" name=Arrow_btn  onclick="history.back()">←</button>
      </div>
    </div>

    <div class="title">
      <label class="more-btn">
        <input type="checkbox" id="btn1" class="more-check">
        <!-- <label class="more-btn" for="btn1"></label> -->
        <div class="title-more" for="btn1">水色のエリアをクリックすると、題名がすべて表示されます。再度クリックすると折りたたみます</div>
      </label>
    </div>

      <div class="text">
        あ半角英数字は指定した横幅に到達しても改行されず、画面からはみ出てしまいます。
          break-allを指定すると、文字の種類関係なくボックス内で自動改行します。break-allを指定すると、文字の種類関係なくボックス内で自動改行します。
          break-allを指定すると、文字の種類関係なくボックス内で自動改行します。break-allを指定すると、文字の種類関係なくボックス内で自動改行します。

        break-allは禁則処理を無効にします。そのため、文字の種類や句読点に関係なく折り返すのでボックス内が文字でいっぱいになるという特徴があります
        break-allを指定すると、文字の種類関係なくボックス内で自動改行します。
        break-allは禁則処理を無効にします。そのため、文字の種類や句読点に関係なく折り返すのでボックス内が文字でいっぱいになるという特徴がありますbreak-allを指定すると、文字の種類関係なくボックス内で自動改行します。
        break-allは禁則処理を無効にします。そのため、文字の種類や句読点に関係なく折り返すのでボックス内が文字でいっぱいになるという特徴がありますbreak-allを指定すると、文字の種類関係なくボックス内で自動改行します。
        break-allは禁則処理を無効にします。そのため、文字の種類や句読点に関係なく折り返すのでボックス内が文字でいっぱいになるという特徴がありますbreak-allを指定すると、文字の種類関係なくボックス内で自動改行します。
        break-allは禁則処理を無効にします。そのため、文字の種類や句読点に関係なく折り返すのでボックス内が文字でいっぱいになるという特徴がありますbreak-allを指定すると、文字の種類関係なくボックス内で自動改行します。
        break-allは禁則処理を無効にします。そのため、文字の種類や句読点に関係なく折り返すのでボックス内が文字でいっぱいになるという特徴がありますbreak-allを指定すると、文字の種類関係なくボックス内で自動改行します。
        break-allは禁則処理を無効にします。そのため、文字の種類や句読点に関係なく折り返すのでボックス内が文字でいっぱいになるという特徴がありますbreak-allを指定すると、文字の種類関係なくボックス内で自動改行します。
        break-allは禁則処理を無効にします。そのため、文字の種類や句読点に関係なく折り返すのでボックス内が文字でいっぱいになるという特徴がありますbreak-allを指定すると、文字の種類関係なくボックス内で自動改行します。
        break-allは禁則処理を無効にします。そのため、文字の種類や句読点に関係なく折り返すのでボックス内が文字でいっぱいになるという特徴がありますbreak-allを指定すると、文字の種類関係なくボックス内で自動改行しません。
      qwertyuiop9765431234567890asdfghjkzxcvbnmertyuioasdfghjkzxcvbnm23456789

      </div>

  </div>
</main>
</body>
