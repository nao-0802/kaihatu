<%@page contentType="text/html; charset=UTF-8" %>

<head>
    <meta name="viewport" content="width=device-width, initial-sccle=1">
</head>

<style>
body{
    margin: 0;
}

.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 20px;
    background: #757575;
  }

.a button{
    font-size: 15px;
    padding: 5px;
}


.a{
    text-align: center;
    width: fit-content;
    margin: auto;
    padding: 10px;
    margin-top: 30px;
}

.a th{
    vertical-align: top;
    padding: 10px;
}

input[type="text"]{
    width: 200px;
    padding: 5px;
}

input[type="password"]{
    width: 200px;
    padding: 5px;
}

#toggleIcon {
    position: relative;
    left: -2.2em;
  }
  #password {
    box-sizing: border-box;
    padding-right: 2.4em;
  }

.password-container {
    position: relative;
  }

  .toggle-password {
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
    cursor: pointer;
  }

  .navtext-container {
    width: 100%;
    height: 52px;
    box-sizing: border-box;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .navtext {
    letter-spacing: 4px;
    font-size: 25px;
    text-align: center;
    color: aliceblue;
}

</style>

<body>
<header class="header">
<div class="navtext-container">
    <p name="title" class="navtext">すてっぷのーと</p>
</div>

</header>


<main>
<form class="a" action="Login.action" method="post">
    <table>
        <tr>
            <th>
	            <td>
	                <input type="text" name="teacherID" placeholder="保護者ID" required>
	            </td>
	        </th>
        </tr>

        <tr>
            <th>
	            <td>
	                <input type="password" id="password" name="password" placeholder="パスワード" required>
	                <i id="toggleIcon" class="fa-solid fa-eye fa-fw"></i>
	            </td>
	        </th>
        </tr>

    </table>
    <br>
    <button name="login_btn">ログイン</button>
</form>
</main>
</body>



    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

    <script>
        const passwordField = document.getElementById('password');
        const toggleIcon = document.getElementById('toggleIcon');
        let passwordVisible = false;

        toggleIcon.addEventListener('click', function () {
          if (passwordVisible) {
            passwordField.type = 'password';
            toggleIcon.classList.remove('fa-eye-slash');
            toggleIcon.classList.add('fa-eye');
            passwordVisible = false;
          } else {
            passwordField.type = 'text';
            toggleIcon.classList.remove('fa-eye');
            toggleIcon.classList.add('fa-eye-slash');
            passwordVisible = true;
          }
        });
      </script>

<!--  %@include file="../footer.html" % -->
