<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<body>
<header class="header">
    <div class="navtext-container">
      <p class="navtext">全体掲示板</p>
    </div>
<%@include file="../common/T_header.jsp" %>
  </header>

<main>
  <div class="div">
    <div class="b">
      <button name="Newer_Posts_btn" onclick="location.href='BullietinBoard.jsp'">お知らせを追加</button>
      <div class="aa"><p class="a" name=Notice>お知らせ一覧</p></div>
    </div>

    <div class="news">
      <table id="newstable">
        <tr>
          <td><a href=""><c:forEach var="p" items="${list}">
            <option value="${p.class_id}">${p.class_name}</option>
            </c:forEach></a></td>
        </tr>
        <tr>
          <td>pu-ru</td>
        </tr>
      </table>
  </div>
</div>
</main>
</body>


<style>
body{
    overflow: hidden;
  }

  main{
    margin-top: 52px;
    margin-left:  auto;
    margin-right: auto;
    width: 100vw;
  }


  .a{
    text-align: left;
    /* border-bottom: 3px solid #757575; */
    font-size: 20px;
    margin-left: 10px;
  }

  .aa{
    height: 30px;
    border-bottom: 3px solid #757575;
  }

  button{
    display: inline-block;
    padding: 5px;
    width: 150px;
    margin-top: 5px;
    margin-right: 5px;
  }

.b{
  text-align: right;
}

  .div{
    /* width: 100vw; */
    text-align: center;
    /* display: flex; */
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  .news{
    font-size: 20px;
      /* flex: 1; */
    overflow-y: scroll;
    padding: 5px 5px;
  }

table{
  width: 90%;
  font-size: 20px;
  padding: 5px;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 10px;
}

table td{
  border-bottom: 1px solid red;
  padding: 3px;
}

</style>