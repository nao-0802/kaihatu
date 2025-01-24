<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../common/T_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>更新完了</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        h2 {
            color: #333;
        }
        a {
            color: #4CAF50;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>生徒カルテの更新が完了しました。</h2>
    <form action="../teacher/StudentRecord.action" method="post">
                    <!-- 生徒名をボタンに表示 -->
                    <button name="student_id" value="${studentId}">生徒カルテへ</button>
                </form>
</body>
</html>
