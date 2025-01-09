<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<%@ page import="bean.StudentRecord, java.util.List" %>
<%@ page import="bean.Student, java.util.List" %>


<body>
<header class="header">
        <div class="navtext-container">
          <div><a class="here">クラス</a></div>
          <div><a class="navtext" href="attendancelist.jsp">出欠席状況</a></div>
        </div>
<%@include file="../common/T_header.jsp" %>
</header>

<main>
    <div id="studentlist">
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


<table>
    <p>名前</p>
    <c:forEach var="student" items="${studentList}">
        <form action="LifeRecord.action" method="post">
            <p>${student.studentName}</p>
            <button name="student_id" value="${student.studentId}">生活記録</button>
        </form>
        <form action="StudentRecord.action">
            <button name="student_id" value="${student.studentId}">カルテ</button>
        </form>
        <form action="ContactBookWrite.action">
            <button name="student_id" value="${student.studentId}">連絡帳</button>
        </form>
    </c:forEach>

</table>
<br>


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

#studentlist{
  display: flex;
  flex-wrap: wrap;
  /* padding: 10px; */
  justify-content: space-around;
}

#studentlist .student{
  margin: 10px;
}

#studentlist button{
  width: 200px;
  height: 80px;
  /* border: solid 1px red; */
  /* text-align: center; */
}

#studentlist div{
  width: 200px;
  height: 80px;
  border: solid 1px red;
  /* text-align: center; */
}

#studentlist div a{
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
