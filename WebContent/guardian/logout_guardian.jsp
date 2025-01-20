<%@page contentType="text/html; charset=UTF-8" %>


<body>
	<header class="header">
	    <div class="navtext-container">
	        <p class="navtext">ログアウト</p>
	    </div>

	    <%@include file="../common/G_header.jsp" %>
	</header>

  <main>
    <div class="logout">
    <h3 name="conf_message">本当にログアウトしますか？</h3>
    </div>

    <div class="button">
        <form action="../scoremanager/LogoutExecute.action">
            <div><button class="a" name="yes_btn">はい</button></div>
        </form>

        <div><button class="b" name="no_btn" onclick="history.back()">いいえ</button></div>
    </div>
  </main>
</body>




<style>
main{
    margin-top: 52px;
    margin-left:  auto;
    margin-right: auto;
  }

  .logout{
    text-align: center;
  }


  .button{
    display: flex;
    justify-content: center;
  }

.a{
    margin: 10px;
    padding: 5px;
    width: 60px;
}


.b{
    margin: 10px;
    padding: 5px;
    width: 60px;
}

</style>
