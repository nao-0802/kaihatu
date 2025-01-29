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

.back{
  background-color: #757575;
  height: 50px;
  border: none;
  width: 60px;
  border-right: 1px solid #ddd;
  font-size: 30px;
  color: #ddd;
}


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

.a{
  margin-top: 15px;
  font-size: 15px;
}

.b{
  padding: 5px;
  font-size: 12px;
  margin-left: 10px;
  margin: 5px;
}
</style>