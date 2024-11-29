<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="bean.StudentRecord, java.util.List" %>
<%@page import="bean.Student, java.util.List" %>
<%@page import="bean.Guardian, java.util.List" %>
<%@page import="bean.Class, java.util.List" %>


<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>カルテ詳細画面</title>
    <style>
        /* 全体のスタイル */
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f6f9;
            color: #333;
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        /* ヘッダーのスタイル */
        h1 {
            text-align: center;
            font-size: 24px;
            color: #007BFF;
            margin-bottom: 20px;
        }

        /* 戻るボタン */
        #Arrow_btn {
            position: absolute;
            top: 20px;
            left: 20px;
            padding: 10px 20px;
            font-size: 18px;
            border: 2px solid #007BFF;
            background-color: #fff;
            color: #007BFF;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
        }

        #Arrow_btn:hover {
            background-color: #007BFF;
            color: #fff;
        }

        /* ボタンセクション */
        .button-container {
            display: flex;
            gap: 20px;
            margin-bottom: 30px;
        }

        .button-container button {
            padding: 12px 25px;
            font-size: 16px;
            border: 2px solid #007BFF;
            background-color: #fff;
            color: #007BFF;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
            width: 150px;
            text-align: center;
        }

        .button-container button:hover {
            background-color: #007BFF;
            color: #fff;
        }

        /* カルテ表示セクション */
        .text-container {
            text-align: center;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 10px;
        }

        .text-container label {
            font-size: 18px;
            font-weight: bold;
            margin-right: 10px;
        }

        .text-container input[type="text"] {
            width: 350px;
            padding: 12px;
            font-size: 16px;
            border: 2px solid #007BFF;
            border-radius: 5px;
            background-color: #f9f9f9;
            text-align: center;
            cursor: not-allowed;
        }

        /* フッターのボタン */
        .footer-button-container {
            display: flex;
            gap: 20px;
            justify-content: center;
            position: fixed;
            bottom: 20px;
        }

        .footer-button-container button {
            padding: 12px 25px;
            font-size: 16px;
            border: 2px solid #007BFF;
            background-color: #fff;
            color: #007BFF;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
            width: 150px;
            text-align: center;
        }

        .footer-button-container button:hover {
            background-color: #007BFF;
            color: #fff;
        }

        /* レスポンシブ対応 */
        @media (max-width: 768px) {
            .button-container {
                flex-direction: column;
                align-items: center;
            }

            .text-container input[type="text"] {
                width: 90%;
            }

            .button-container button,
            .footer-button-container button {
                width: 100%;
            }
        }
    </style>
</head>
<body>

    <!-- 戻るボタン -->
    <form action="ClassSelectExecute.action">
    	<button id="Arrow_btn" name="class_id" value="${slist.classId}">←</button>
	</form>
    <h1>カルテ詳細画面</h1>

    <!-- ボタンセクション（画面上部中央） -->
    <div class="button-container">
        <button id="Chart_Read_btn" onclick="location.href='<%= request.getContextPath() %>/chart.jsp'">カルテ</button>
        <button id="Chart_Read_btn" onclick="location.href='<%= request.getContextPath() %>/annual_record.jsp'">年次記録</button>
    </div>

    <!-- カルテ表示セクション（ボタンセクションの下） -->
    <!-- <div class="text-container"> -->
        <label for="Chart_Indication">カルテ表示:</label>
        <!-- サーブレットから渡されたstudentRecordを表示 -->
        <p>生徒氏名</p>
        <p>${slist.studentName}</p>
        <c:forEach var="guardian" items="${glist}">
        <p>保護者氏名</p>
        	<p>${guardian.guardianName}</p>
    	</c:forEach>
    	<form action="StudentRecordUpdateExecute.action">
	    <c:forEach var="list" items="${list}">
	    	<p>誕生日<p>
	        <p>${list.birthdate}</p>
		        <p>アレルギー</p>
		        <input type="hidden" name="studentrecordid" value="${list.studentRecordId}">
		        <input type="hidden" name="class_id" value="${slist.classId}">
		        <input type="text" name="allergy"value="${list.allergy}">
		        <p>特徴</p>
		        <input type="text" name="features" value="${list.features}" >
		        <input type="submit" value="更新">
	    </c:forEach>
	    </form>
    <!-- </div>  -->

    <!-- 機能ボタンコンテナ（画面下部） -->
    <div class="footer-button-container">
        <button id="Class_btn" onclick="location.href='<%= request.getContextPath() %>/class.jsp'">クラス</button>
        <button id="Bulletin_Board_btn" onclick="location.href='<%= request.getContextPath() %>/bulletin_board.jsp'">掲示板</button>
        <button id="Contact_Book_btn" onclick="location.href='<%= request.getContextPath() %>/contact_book.jsp'">連絡帳</button>
    </div>

</body>
</html>
