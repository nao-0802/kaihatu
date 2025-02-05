<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
<header class="header">
    <div class="navtext-container">
      <p class="navtext">カルテ詳細</p>
    </div>
    <%@include file="../common/T_header.jsp" %>
</header>

<main>

  <div class="zentai">
    <div class="kihon">
      <table id="karute">
        <tr>
          <th class="tate">氏名</th>
          <th class="tate">生年月日</th>
          <th>生徒ID</th>
        </tr>
        <tr>
            <td class="tate">${studentName}</td>
            <td class="tate">${studentRecord.birthdate}</td>
            <td>${studentId}</td>
        </tr>
      </table>
    </div>

    <table id="karute2">
      <tr>
        <th>アレルギー</th>
      </tr>
      <tr>
        <td>
          <c:forEach var="allergy" items="${allergyNames}" varStatus="status">
            ${allergy}<c:if test="${!status.last}">, </c:if>
          </c:forEach>
        </td>
      </tr>
      <tr>
        <th>特徴</th>
      </tr>
      <tr>
        <td>${studentRecord.features}</td>
      </tr>
    </table>
  </div>

  <div class="buttons">
    <form action="../teacher/StudentRecordUpdate.action" method="get">
      <input type="hidden" name="studentId" value="${studentId}">
      <button class="edit-btn">編集</button>
    </form>

    <form action="LifeRecord.action" method="post">
      <button class="back-btn" name="student_id" value="${studentId}">戻る</button>
    </form>
  </div>

</main>
</body>

<style>
/* 全体レイアウト */
main {
  margin-top: 52px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  width: 100%;
}

/* 全体の中央配置 */
.zentai {
  width: 100%;
  max-width: 1200px; /* PCでは最大1200pxまで広げる */
  margin: auto;
  text-align: center;
  padding: 20px;
}

/* テーブル中央配置 */
.kihon, #karute2 {
  width: 90%;
  max-width: 900px; /* テーブルの最大幅を制限 */
  margin: 20px auto;
}

#karute, #karute2 {
  border-collapse: collapse;
  width: 100%;
}

#karute th, #karute2 th {
  border-bottom: 2px solid rgb(128, 183, 225);
  padding: 15px;
  text-align: center;
  background-color: #f4f4f4;
}

#karute td, #karute2 td {
  padding: 15px;
  border-bottom: 1px solid #ddd;
  text-align: center;
}

/* ボタンエリア（ページ下部中央） */
.buttons {
  width: 100%;
  max-width: 500px; /* ボタンの最大幅を設定 */
  display: flex;
  justify-content: space-between; /* 均等配置 */
  gap: 20px; /* ボタン間の間隔 */
  margin: 30px auto;
}

button {
  flex: 1; /* ボタンを均等に広げる */
  padding: 14px 20px;
  font-size: 18px;
  border: none;
  cursor: pointer;
  border-radius: 8px;
  font-weight: bold;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  transition: 0.3s ease-in-out;
}

/* 編集ボタン */
.edit-btn {
  background-color: rgb(0, 135, 208);
  color: white;
}

.edit-btn:hover {
  background-color: rgb(0, 100, 180);
  transform: scale(1.05);
}

/* 戻るボタン */
.back-btn {
  background-color: rgb(128, 183, 225);
  color: white;
}

.back-btn:hover {
  background-color: rgb(98, 153, 195);
  transform: scale(1.05);
}

/* スマホ表示時 */
@media screen and (max-width: 768px) {
  .buttons {
    flex-direction: column;
    align-items: center;
    width: 90%;
  }

  button {
    width: 100%;
    margin-bottom: 10px;
  }
}
</style>
