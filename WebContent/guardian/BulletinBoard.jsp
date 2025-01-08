<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<style>
body{
    overflow: hidden;
  }


  main{
    margin-top: 52px;
    margin-left:  auto;
    margin-right: auto;
  }


  input{
    text-align: center;
    width: 200px;
    height: 30px;
  }

  .test{
    width: 100vw;
    text-align: center;
    display: flex;
    flex-direction: column;
    height: 100%;
  }

  .m{
    font-size: 20px;
      /* flex: 1; */
      overflow-y: scroll;
      padding: 10px 20px;
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
  text-align: center;
    border-bottom: solid 1px red;
  }

  a{
    color: black;
    text-decoration: none;
  }
</style>


<body>
<header class="header">
    <div class="navtext-container">
      <p class="navtext">全体掲示板</p>
    </div>
<%@include file="../common/G_header.jsp" %>


</header>



<main>
  <div class="test">
    <div class="m">
    <table>
      <tr>
        <td><a href="bulletinboard copy.html"><c:forEach var="p" items="${list}">
          <option value="${p.class_id}">$</option>
          </c:forEach></a></td>
      </tr>
      <tr>
        <td>題名</td>
      </tr>


</main>

</body>