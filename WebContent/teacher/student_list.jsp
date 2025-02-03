<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<%@ page import="bean.Student, java.util.List" %>

<body>
<header class="header">
    <div class="navtext-container">
        <div><a class="here">クラス</a></div>
        <div><a class="navtext" href="AttendanceExecute.action">記録確認</a></div>
    </div>
    <%@ include file="../common/T_header.jsp" %>
</header>

<main>


    <div id="studentlist">
        <!-- 生徒リストから動的にボタンを生成 -->
        <c:forEach var="student" items="${studentList}">
            <div class="student">
                <form action="LifeRecord.action" method="post">
                    <!-- 生徒名をボタンに表示 -->
                    <button name="student_id" value="${student.studentId}">${student.studentName}</button>
                </form>
            </div>
        </c:forEach>
    </div>
</main>

</body>

<style>
/* メインコンテンツの調整 */
main {
    margin-top: 52px;
    margin-left: auto;
    margin-right: auto;
    padding-top: 30px; /* ヘッダーと生徒リストの間の余白を追加 */
}

/* ナビゲーションのスタイル */
.here {
    font-size: 25px;
    width: 100px;
    color: aliceblue;
    padding: 2px;
}

a.navtext{
display: inline-block;
padding: 1px 10px;
text-decoration: none;
background-color: rgb(67, 161, 164);
border-radius: 3px;
font-size: 15px;
top: 5;
right: 0;
}


/* 出欠席状況ボタンの中央寄せ */
.attendance-button-container {
    text-align: center;
    margin-top: 0; /* 出欠席状況ボタンの上部余白を完全に削除 */
    margin-bottom: 60px; /* 出欠席状況ボタンと生徒リストの間に余白を追加（50pxに変更） */
    display: flex;
    justify-content: center;
}

/* 出欠席状況ボタンのスタイル */
.attendance-button-container a.navtext {
    display: inline-block;
    padding: 10px 20px;
    text-decoration: none;
    background-color: rgb(67, 161, 164);
    border-radius: 3px;
    font-size: 18px;
    color: white;
}

/* 生徒リストを中央揃え */
#studentlist {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 15px; /* ボタン同士の間隔 */
    margin-top: 20px; /* 生徒リストの上に余白を追加 */
}

/* 各生徒ボタンを中央揃え */
#studentlist .student {
    display: flex;
    justify-content: center;
    align-items: center;
}

/* 生徒名ボタンのデザイン */
#studentlist button {
    width: 220px;
    height: 90px;
    background-color: white; /* 背景を白に */
    color: #333; /* 文字色を黒系に */
    font-size: 18px;
    font-weight: bold;
    border: 3px solid #3498db; /* 青色の枠線 */
    border-radius: 8px; /* 角を少し丸く */
    box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1); /* 軽い影 */
    transition: all 0.3s ease-in-out;
    cursor: pointer;
}

/* ホバー時のスタイル */
#studentlist button:hover {
    background-color: #3498db; /* 背景色を青に */
    color: white; /* 文字色を白に */
    box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.2); /* 影を強調 */
}
</style>
