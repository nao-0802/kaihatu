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

<div id="hensyuu">
    <form action="../teacher/StudentRecordUpdate.action" method="get">
    <input type="hidden" name="studentId" value="${studentId}">
    <button>編集</button>
    </form>
  </div>
  <div class="zentai">
    <div class="kihon">
      <table id="karute">
        <tr>
          <th id="dai" class="tate">氏名</th>
          <th id="dai" class="tate">生年月日</th>
          <th id="dai">生徒ID</th>
        </tr>
        <tr>
            <td  class="tate" id="sita">${studentName}</td>
            <td  class="tate" id="sita">${studentRecord.birthdate}</td>
            <td id="sita">${studentId}</td>
        </tr>
      </table>
    </div>
      <table id="karute2">
        <tr>
          <th id="dai">アレルギー</th>
        </tr>
        <tr>
            <td>${allergyNames}</td>
        </tr>
          <tr>
              <th id="dai">特徴</th>
          </tr>
          <tr>
              <td>${studentRecord.features}</td>
          </tr>
      </table>




</main>
</body>


<style>
main{
margin-top:52px;
}

body{
  overflow: hidden;
}

  aside{
    margin-top: 52px;
    background-color: rgb(161, 214, 255);
  }

.side{
  overflow-y: auto;
  height: 100%;
  width: 120px;
}

.name{
  font-size: 18px;
  padding: 5px;
}


input:checked + span{
  background-color: rgb(0, 135, 208);
  color: aliceblue;
}

input{
  display: none;
}

#name td{
  padding: 3px;
  width: 100%;
  border-bottom: 1px solid #757575;
}

.ss{
  padding: 1px;
}

  main{
    margin-top: 52px;
    margin-left: auto;
    margin-right: auto;
    width: 100vw;
  }

.zentai{
  height: 80%;
  overflow-y: auto;
}

#karute {
    margin-left: auto;
    margin-right: auto;
    margin-top: 10px;
    width: 80%;
    border-collapse: collapse;
}

#karute2 {
  margin-left: auto;
  margin-right: auto;
  margin-top: 10px;
  width: 80%;
  border-collapse: collapse;
}

#karute th,#karute2 th{
    border-bottom: 2px solid rgb(128, 183, 225);
    padding-top: 5px;

}

#karute td, #karute2 td{
  padding: 5px;
  height: 30px;
}

#dai{
    text-align: left;
}


.tate{
  border-right: 2px solid rgb(0, 135, 208);
}


#hensyuu{
  margin: 5px;
  text-align: right;
}

button{
    padding: 3px;
    width: 60px;
}


@media screen and (max-width: 426px) {
  aside{
    width: 35vw;
  }

.side{
  width: 100%;
}

#karute{
  -ms-writing-mode : lr-tb;
  writing-mode : vertical-lr;
}

#karute th{
  -ms-writing-mode : lr-tb;
  writing-mode : horizontal-tb;
  height: 30px;
}

#karute td{
  -ms-writing-mode : lr-tb;
  writing-mode : horizontal-tb;
  padding: 0;
}

.tate{
  border:none;
}

#sita{
  border-bottom: 2px solid rgb(128, 183, 225);
}

}


</style>
