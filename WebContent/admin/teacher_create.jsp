<%@page contentType="text/html; charset=UTF-8" %>

<form action="AdminSignupExecuteAction" method="post">
<h2 name="title">管理者新規登録</h2>
<p>教職員ID:<input type="text" name="teacher_id" required></p>
<p>名前:<input type="text" name="name" required></p>
<p>パスワード:<input type="text" name="password" required></p>
<button name="register_btn">登録</button>
</form>