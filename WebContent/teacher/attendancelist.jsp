<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<style type="text/css">
/* 全体のレイアウト */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f9f9f9;
  overflow-x: hidden; /* 横スクロールを無効化 */
}



/* ヘッダーのスタイル */
header {
  position: fixed; /* 画面上部に固定 */
  top: 0;
  left: 0;
  width: 100%;
  background-color: white; /* 背景色を指定 */
  z-index: 1000; /* 最前面に配置 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 影を追加して視認性向上 */
}

/* メインコンテンツのスタイル */
main {
  margin: 80px auto 50px; /* ヘッダーの高さ分、上にマージンを追加 */
  width: 90%;
  max-width: 1200px;
  padding: 50px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  position: relative; /* relative に変更 */
  left: 50%;
  transform: translateX(-50%);
  max-height: calc(100vh - 200px);
}



/* 出席リストのコンテナ */
#attendance-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  margin-top: 20px;
  padding-right: 10px;
  overflow-y: auto; /* 縦スクロール */
  max-height: 400px; /* 出席リストの最大高さ */
}

/* 出席情報のカード */
.attendance-item {
  border: 1px solid #ddd; /* 枠線の色は維持 */
  border-radius: 8px;
  width: 220px;
  padding: 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s, box-shadow 0.2s;
  flex-shrink: 0;
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
  background-color: transparent; /* 背景を透明に設定 */
}

.attendance-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.15);
}

/* 詳細ボタン */
.details-btn {
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 8px 12px;
  cursor: pointer;
  font-size: 14px;
  text-align: center;
  margin-top: 10px;
  display: block;
}

.details-btn:hover {
  background-color: #0056b3;
}

/* 詳細の内容 */
.details-content {
  display: none;
  margin-top: 10px;
  font-size: 14px;
  color: #555;
}

.details-content p {
  margin: 5px 0;
}

/* スマートフォン向け */
@media screen and (max-width: 426px) {
  #attendance-list {
    flex-direction: column;
    align-items: center;
  }

  .attendance-item {
    width: 100%;
    max-width: 400px;
  }
}
</style>

<script type="text/javascript">
/* 詳細を表示/非表示に切り替える関数 */
function toggleDetails(button) {
  const content = button.nextElementSibling;
  if (content.style.display === "none" || content.style.display === "") {
    content.style.display = "block";
    button.textContent = "詳細を隠す";
  } else {
    content.style.display = "none";
    button.textContent = "詳細を見る";
  }
}
</script>

</head>

<body>
<header class="header">
    <div class="navtext-container">
      <p class="navtext">出欠席状況</p>
    </div>
<%@include file="../common/T_header.jsp" %>
</header>

<main>
    <h2>クラスの出席状況</h2>

    <!-- 出席情報が空の場合にメッセージを表示 -->
    <c:if test="${empty attendanceList}">
        <p>現在、出席情報はありません。</p>
    </c:if>

    <!-- 出席情報がある場合、繰り返し表示 -->
    <div id="attendance-list">
        <c:forEach var="attendance" items="${attendanceList}">
            <div class="attendance-item">
                <!-- 生徒名と出席状況 -->
                <p><strong>生徒名:</strong> ${attendance.studentName}</p>
                <p><strong>出席状況:</strong> ${attendance.type}
                    <c:choose>
                        <c:when test="${attendance.type == '遅刻' || attendance.type == '早退'}">
                            (${attendance.time})
                        </c:when>
                    </c:choose>
                </p>

                <!-- 詳細ボタン -->
                <button class="details-btn" onclick="toggleDetails(this)">詳細を見る</button>

                <!-- 詳細情報 -->
                <div class="details-content">
                    <p><strong>生徒ID:</strong> ${attendance.studentId}</p>
                    <p><strong>日付:</strong> ${attendance.day}</p>
                    <p><strong>症状:</strong> ${attendance.symptom}</p>
                    <p><strong>理由:</strong> ${attendance.reason}</p>
                    <p><strong>備考:</strong> ${attendance.notes}</p>
                </div>
            </div>
        </c:forEach>
    </div>
</main>
</body>



