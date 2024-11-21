<%@page contentType="text/html; charset=UTF-8" %>
<!-- %@include file="../header.html" % -->

<form action="LoginGuardianExecute.action" method="post">
<h2 name="title">保護者ログイン</h2>
<p>保護者ID:<input type="text" name="guardian_id"></p>
<p>パスワード:<input type="password" name="password"></p>
<button name="login_btn">ログイン</button>
</form>

<!--  %@include file="../footer.html" % -->
