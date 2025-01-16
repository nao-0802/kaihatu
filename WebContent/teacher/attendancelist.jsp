<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<body>
<header class="header">
    <div class="navtext-container">
        <div><a class="navtext" href="student_list.jsp">クラス</a></div>
        <div><a class="here">出欠席状況</a></div>
    </div>
    <%@include file="../common/T_header.jsp" %>
</header>

<main>
    <div id="attendancelist">
        <!-- 現在選択されているクラスの生徒リストから動的にボタンを生成 -->
        <c:forEach var="student" items="${studentList}">
            <div class="student">
                <form action="AttendanceExecute.action" method="post">
                    <!-- 生徒名をボタンに表示 -->
                    <button name="student_id" value="${student.studentId}">${student.studentName}</button>
                </form>
            </div>
        </c:forEach>
    </div>

    <!-- クラスごとに出欠状況を表示 -->
    <c:forEach var="attendance" items="${attendanceList}">
        <div>
            <h3>生徒: ${attendance.studentName}</h3>
            <p>日付: ${attendance.day}</p>
            <p>出席状況: ${attendance.type}</p>
        </div>
        <hr />
    </c:forEach>

</main>
</body>

<style>
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

a.navtext {
    display: inline-block;
    padding: 1px 10px;
    text-decoration: none;
    background-color: rgb(67, 161, 164);
    border-radius: 3px;
    font-size: 15px;
}
</style>
