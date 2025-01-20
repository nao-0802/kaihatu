<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <style>
        body {
            overflow: hidden;
        }

        main {
            margin-top: 52px;
            margin-left: auto;
            margin-right: auto;
        }

        .here {
            font-size: 25px;
            width: 100px;
            color: aliceblue;
            padding: 2px;
        }

        #attendancelist {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
        }

        #attendancelist .student {
            margin: 10px;
        }

        #attendancelist button {
            width: 200px;
            height: 80px;
        }

        #attendancelist div {
            width: 200px;
            height: 80px;
            border: solid 1px red;
        }

        #attendancelist div a {
            text-align: center;
        }

        a.navtext {
            display: inline-block;
            padding: 1px 10px;
            text-decoration: none;
            background-color: rgb(67, 161, 164);
            border-radius: 3px;
            font-size: 15px;
        }

        #content2 {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            height: 100%;
        }

        #content2 .stu {
            margin: 10px;
            width: 200px;
            height: 80px;
            border: solid 1px red;
        }

        .title {
            background-color: rgb(122, 227, 227);
        }

        .more-btn {
            cursor: pointer;
        }

        .title-more {
            overflow: hidden;
            height: 20px;
        }

        .more-check {
            display: none;
        }

        .more-check:checked ~ .title-more {
            height: auto;
        }
    </style>
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
    <div id="attendancelist">
        <c:forEach var="attendance" items="${attendanceList}">
            <div class="student">
                <p>生徒ID: ${attendance.studentId}</p> <!-- studentIdのプロパティ -->
                <p>生徒名: ${attendance.studentName}</p> <!-- studentNameのプロパティ -->
                <p>日付: ${attendance.day}</p> <!-- dayのプロパティ -->
                <p>出席状況: ${attendance.type}</p> <!-- typeのプロパティ -->
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
        </c:forEach>
    </div>
</main>
</body>
