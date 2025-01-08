<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<body>
<%@include file="../common/T_header.jsp" %>


<main class="logout">
<h2 name="title">ログアウト確認</h2>
<h3 name="conf_message">本当にログアウトしますか？</h3>


<form action="Logout.action">
<button name="yes_btn">はい</button>
</form>

<button name="no_btn">いいえ</button>
</main>

</body>
</html>



<style>
.logout{
margin-top: 52px;
}
</style>
