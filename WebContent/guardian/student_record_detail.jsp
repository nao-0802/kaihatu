<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
    <header class="header">
        <div class="navtext-container">
            <p class="navtext">連絡帳閲覧</p>
        </div>
        <%@include file="../common/G_header.jsp" %>
    </header>
    <main>
        <h2>生徒カルテ詳細</h2>
        <table>
            <tr>
                <th>生徒氏名</th>
                <td>${studentName}</td>
            </tr>
            <tr>
                <th>保護者氏名</th>
                <td>${guardianName}</td>
            </tr>
            <tr>
                <th>生年月日</th>
                <td>${studentRecord.birthdate}</td>
            </tr>
            <tr>
                <th>クラス</th>
                <td>${className}</td>
            </tr>
            <tr>
                <th>特徴</th>
                <td>${studentRecord.features}</td>
            </tr>
        </table>
        <div class="allergies">
            <h3>アレルギー情報</h3>
            <ul>
                <c:forEach var="allergyName" items="${allergyNames}">
                    <li>${allergyName}</li>
                </c:forEach>
            </ul>
        </div>
        <div class="button-container">
            <button onclick="history.back()">戻る</button>
        </div>
    </main>
</body>

<style>
    /* 全体のスタイル */
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9; /* 背景を薄いグレーに */
        margin: 0;
        padding: 0;
        color: #333;
        line-height: 1.6;
    }

    .navtext-container {
        margin-top: 10px;
    }

    /* メインコンテンツ */
    main {
        max-width: 1200px;
        padding: 40px;
		margin: 44px auto;
        background-color: white;

        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }

    h2 {
        font-size: 2rem;
        color: #333;
        text-align: center; /* 中央寄せ */
        margin-bottom: 20px;
    }

    /* テーブル */
    th, td {
        padding: 15px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f1f1f1; /* 薄いグレー */
        font-size: 1.1rem;
        text-align: left;
    }

    td {
        background-color: #ffffff;
        font-size: 1.1rem;
    }

    /* アレルギー情報 */
    .allergies {
        margin-top: 30px;
    }

    .allergies h3 {
        font-size: 1.3rem;
        color: #4CAF50;
        margin-bottom: 10px;
    }

    .allergies ul {
        list-style-type: none;
        padding-left: 0;
    }

    /* ボタンのスタイル */
    .button-container {
        text-align: center;
        margin-top: 20px;
    }

.button-container button {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 30px; /* 横長にするために幅を増やす */
    font-size: 1rem;
    border-radius: 5px;
    cursor: pointer;
    width: 150px; /* ← ボタンの横幅を指定 */
}


    .button-container button:hover {
        background-color: #45a049;
    }
</style>
</html>
