<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta name="viewport" content="width=device-width, initial-sccle=1">
</head>


<body>
    <header class="header">
<div class="navtext-container">
    <p name="title" class="navtext">すてっぷのーと</p>
</div>

</header>
    <main>
        <p>送信完了</p>

    <div class="button">
            <form action="LifeRecord.action" method="post">
      			<button class="back-btn" name="student_id" value="${studentId}">戻る</button>
    		</form>
        </div>
    </main>

</body>

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
    main{
        margin-top: 60px;
        text-align: center;
    }

    p{
        font-size: 20px;
    }
    .back-btn {
    margin-top: 15px;
    padding: 12px 20px;
    background-color: #f1f1f1;
    color: #6fa3ef;
    border: 1px solid #ddd;
    border-radius: 8px;
    cursor: pointer;
    font-size: 1.1em;
    transition: background-color 0.3s, color 0.3s;
}

.back-btn:hover {
    background-color: #6fa3ef;
    color: white;
}

    button{
padding:5px;
font-size:15px;
}
</style>



