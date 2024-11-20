<%@page contentType="text/html; charset=UTF-8" %>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

<header class="header">
<div class="navtext-container">
    <h2 name="title" class="navtext">すてっぷのーと</h2>
</div>
</header>

<form class="a" action="Login.action" method="post">
    <table>
        <tr>
            <th></th>
            <td>
                <input type="text" name="teacherID" placeholder="教職員ID(10桁)" required>
            </td>
        </tr>

        <tr>
            <th></th>
            <td>
                <input type="password" id="password" name="password" placeholder="パスワード" required>
                <i id="toggleIcon" class="fa-solid fa-eye fa-fw"></i>
            </td>
        </tr>

    </table>
    <br>
    <button name="login_btn">ログイン</button>
</form>


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

<style>
body{
    background-color: #C5E99B;
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
    color: #919291;
    /* background-color: #daf3df; */
    padding: 5px;
    border-radius: 5px;
}
.a button:hover{
    color: #daf3df;
    background-color: #919291;
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
    color: #E9E9E9;
}

</style>

<!--  %@include file="../footer.html" % -->
