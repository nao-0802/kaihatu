<%@page contentType="text/html; charset=UTF-8" %>
<!-- %@include file="../header.html" % -->

<form action="LoginAdminExecute.action" method="post">
<h2 name="title">管理者ログイン</h2>
<p>管理者ID:<input type="text" name="admin_id"></p>
<p>パスワード:<input type="password" name="password"></p>
<button name="login_btn">ログイン</button>
</form>

<!--  %@include file="../footer.html" % -->
