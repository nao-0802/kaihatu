<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
	<header class="header">
    	<div class="navtext-container">
        	<p class="navtext">連絡帳閲覧</p>
    	</div>

    	<%@include file="../common/G_header.jsp" %>
	</header>
	<main>
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
	    <br>
	</main>
</body>


<style>
body{
  overflow-y: scroll;
}

main {
    margin-top: 52px;
    margin-left: auto;
    margin-right: auto;
}
</style>
</html>
