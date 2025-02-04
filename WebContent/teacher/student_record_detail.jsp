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
}

/* 全体の中央配置 */
.zentai {
  width: 90%;
  max-width: 800px; /* 横幅を広げる */
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

/* テーブル中央配置 */
.kihon, #karute2 {
  width: 100%;
  margin: 20px 0;
}

#karute, #karute2 {
  border-collapse: collapse;
  width: 100%;
}

#karute th, #karute2 th {
  border-bottom: 2px solid rgb(128, 183, 225);
  padding: 10px;
  text-align: center;
}

#karute td, #karute2 td {
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

/* ボタンエリア（ページ下部中央） */
.buttons {
  width: 100%;
  display: flex;
  justify-content: center;
  gap: 30px;
  margin-top: 30px;
}

button {
  padding: 12px 24px;
  font-size: 16px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.edit-btn {
  background-color: rgb(0, 135, 208);
  color: white;
}

.back-btn {
  background-color: rgb(128, 183, 225);
  color: white;
}

/* レスポンシブ対応 */
@media screen and (max-width: 600px) {
  .zentai {
    width: 95%;
  }

  .buttons {
    flex-direction: column;
    align-items: center;
  }

  button {
    width: 80%;
    margin-bottom: 10px;
  }
}
</style>
