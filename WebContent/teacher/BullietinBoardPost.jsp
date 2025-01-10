<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>




<body>
<header class="header">
    <div class="navtext-container">
      <p class="navtext">全体掲示板</p>
    </div>
<%@include file="../common/T_header.jsp" %>

</header>

<main>
  <div>
    <button class="a" name=Arrow_btn  onclick="history.back()">←</button>
  </div>

  <div>
  <div class="c">
    <p>本文または画像を選択してください。</p>
    <form action="BulletinBoardExecuteAction">
      <div>
        <a>題</a>
        <input type="text" required><br>
        <textarea placeholder="本文を入力"></textarea><br>
        <input type="file" placeholder="画像を選択">
        <button class="b" name=Contribution_btn>投稿</button>
      </div>
    </form>
  </div>
  </div>
</main>
</body>


<style>
main{
    margin-top: 52px;
    margin-left:  auto;
    margin-right: auto;
  }

div{
  width: 100vw;
}

div .c{
  text-align: center;
}

textarea{
  resize: none;
  width: 80%;
  height: 200px;
}

input{
  padding: 3px;
  width: 60%;
  margin-bottom: 5px;
}

button{
  padding: 5px;
  font-size: 12px;
  margin-left: 10px;
}


.a{
  margin-top: 15px;
  font-size: 15px;
}

.b{
  margin: 5px;
}
</style>