<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/G_header.jsp" %>



<body>
<header class="header">
    <div class="navtext-container">
        <p class="navtext">カルテ</p>
    </div>

    <%@include file="../common/G_header.jsp" %>
</header>

<main>
    <div id="hensyuu">
        <form action=""><button>編集</button></form>
    </div>
<div class="aaa">
<table>
    <tr>
        <th id="dai">アレルギー</th>
    </tr>
    <tr>
        <td colspan="2">えび、かに、乳</td>
    </tr>
    <tr>
        <th id="dai">持病</th>
    </tr>
    <tr>
        <td colspan="2">てんかん</td>
    </tr>
</table>
</div>
</main>
</body>


<style>
main{
    margin-top: 52px;
}

div{
    width: 100vw;
}

table{
    margin-left: auto;
    margin-right: auto;
    margin-top: 10px;
    width: 80%;
    border: 2px solid red;
    border-collapse: collapse;
}

table  th{
    border-bottom: 1px solid red;
}

table  td{
    padding: 5px;
    border-bottom: 2px solid red;
    height: 30px;
}

#dai{
    text-align: left;
}

.hensyuu{
    width: 70px;
    height: auto;
}

#hensyuu{
    text-align: right;
}

button{
    padding: 3px;
    width: 60px;
}

</style>



<body>
    <h2>生徒カルテ詳細</h2>
    <table>
        <tr>
            <th>生徒氏名</th>
            <td>${studentName}</td>
        </tr>
        <tr>
            <th>保護者氏名</th>
            <td>${guardianName}</td>
        </tr>
        <tr>
            <th>生年月日</th>
            <td>${studentRecord.birthdate}</td>
        </tr>
        <tr>
        	<th>クラス</th>
        	<td>${className}</td>
        </tr>
        <tr>
            <th>特徴</th>
            <td>${studentRecord.features}</td>
        </tr>
    </table>
    <div class="allergies">
        <h3>アレルギー情報</h3>
        <ul>
            <c:forEach var="allergyName" items="${allergyNames}">
                <li>${allergyName}</li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
