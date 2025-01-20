<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <style>
        /* ボタンスタイル */
        .toggle-button {
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            width: 100%;
            text-align: left;
            border-radius: 5px;
        }

        /* 詳細のスタイル */
        .attendance-details {
            display: none;
            padding: 10px;
            background-color: #f4f4f4;
            margin-top: 10px;
            border-radius: 5px;
        }

        /* 詳細表示時のスタイル */
        .attendance-details.show {
            display: block;
        }

        .attendance-summary {
            margin-bottom: 10px;
        }
    </style>
    <script>
        function toggleDetails(id) {
            var details = document.getElementById(id);
            details.classList.toggle('show');
        }
    </script>
</head>

<body>
<header class="header">
    <h1>出席状況</h1>
</header>

<main>
    <h2>クラスの出席状況</h2>

    <!-- 出席情報が空の場合にメッセージを表示 -->
    <c:if test="${empty attendanceList}">
        <p>現在、出席情報はありません。</p>
    </c:if>

    <!-- 出席情報がある場合、繰り返し表示 -->
    <c:forEach var="attendance" items="${attendanceList}">
        <div class="attendance-summary">
            <p>生徒名: ${attendance.studentName}</p> <!-- studentNameのプロパティ -->
            <p>出席状況: ${attendance.type}</p> <!-- typeのプロパティ -->

            <!-- 詳細をトグルするボタン -->
            <button class="toggle-button" onclick="toggleDetails('details-${attendance.studentId}')">
                詳細を表示
            </button>

            <!-- 詳細情報 -->
            <div id="details-${attendance.studentId}" class="attendance-details">
                <p>生徒ID: ${attendance.studentId}</p> <!-- studentIdのプロパティ -->
                <p>日付: ${attendance.day}</p> <!-- dayのプロパティ -->
                <p>症状: ${attendance.symptom}</p> <!-- symptomのプロパティ -->
                <p>理由: ${attendance.reason}</p> <!-- reasonのプロパティ -->
                <p>備考: ${attendance.notes}</p> <!-- notesのプロパティ -->

                <!-- 遅刻または早退の場合、時間を表示 -->
                <c:choose>
                    <c:when test="${attendance.type == '遅刻'}">
                        <p>遅刻時間: ${attendance.time}</p> <!-- 遅刻時間 -->
                    </c:when>
                    <c:when test="${attendance.type == '早退'}">
                        <p>早退時間: ${attendance.time}</p> <!-- 早退時間 -->
                    </c:when>
                </c:choose>
            </div>
        </div>
        <hr />
    </c:forEach>
</main>
</body>
