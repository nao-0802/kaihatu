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
        <div class="student">
          <button>
            <a href=""><c:forEach var="p" items="${list}">
              <option value="${p.student_name}">生徒氏名</option>
              </c:forEach>
            </a>
          </button>
        </div>

        <div class="student">
          <button>
            <a href="">test</a>
          </button>
        </div>

        <div class="student">
          <button>
            <a href="">test2</a>
          </button>
        </div>
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
@media screen and (max-width: 769px) {
main{
    margin-top: 52px;
    margin-left:  auto;
    margin-right: auto;
  }

.here{
  /* position: relative; */
  font-size: 25px;
  width: 100px;
  color: aliceblue;
  padding: 2px;
}

#attendancelist{
  display: flex;
  flex-wrap: wrap;
  /* padding: 10px; */
  justify-content: space-around;
}

#attendancelist .student{
  margin: 10px;
}

#attendancelist button{
  width: 200px;
  height: 80px;
  /* border: solid 1px red; */
  /* text-align: center; */
}

#attendancelist div{
  width: 200px;
  height: 80px;
  border: solid 1px red;
  /* text-align: center; */
}

#attendancelist div a{
  text-align: center;
}

a.navtext{
  display: inline-block;
  padding: 1px 10px;
  text-decoration: none;
  background-color: rgb(67, 161, 164);
  /* color:aliceblue; */
  border-radius: 3px;
  font-size: 15px;
  top: 5;
  right: 0;
}

}

</style>

