<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

        <div class="error-message">
            <h2>エラーが発生しました</h2>
            <p>
                <%-- リクエストスコープからエラーメッセージを取得 --%>
                <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "予期しないエラーが発生しました。" %>
            </p>
        </div>


            <button onclick="location.href='student_list.jsp'" class="back-link">戻る</button>
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
    /* margin-top: px; */
    text-align: center;
}


.error-message {
    background-color: #f8d7da; /* 背景色は薄い赤 */
    border: 1px solid #f5c6cb; /* 境界線の色 */
    margin: 15px;
    margin-bottom: 20px;
    font-size: 18px;
    color: #721c24; /* 文字色を赤に設定 */
}

button{
padding:5px;
font-size:15px;
}
</style>