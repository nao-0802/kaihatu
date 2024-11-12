<%@page contentType="text/html; charset=UTF-8" %>

<form action="GuardianSignupExecuteAction" method="post">
<h2 name="title">保護者新規登録</h2>
<p>保護者ID:<input type="text" name="guardianID" required></p>
<p>名前:<input type="text" name="name"></p>
<p>Email:<input type="text" name="email"></p>
<p>パスワード:<input type="text" name="register_btn" required></p>
<button name="register_btn">登録</button>
</form>