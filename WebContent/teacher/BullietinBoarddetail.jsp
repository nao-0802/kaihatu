<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<head>
    <meta name="viewport" content="width=device-width, initial-sccle=1">
</head>


<body>
<header class="header">
    <div class="navivon">
        <button class="back" name=Arrow_btn  onclick="location.href'../teacher/BulletionBoardList.action'">←</button>
    </div>
</header>


<main>
  <div  class="bulletin_board">


    <div class="title">
      <label class="more-btn">
        <input type="checkbox" id="btn1" class="more-check">
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


<style>
body{
    height: 100vh;
    display: flex;
    margin: 0;
    overflow: hidden;
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
    margin-top: 52px;
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


</style>