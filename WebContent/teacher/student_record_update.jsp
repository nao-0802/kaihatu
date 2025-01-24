<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../common/T_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>生徒カルテ更新</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f9f9f9;
        }
        h2 {
            color: #333;
        }
        form {
            margin-top: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            font-weight: bold;
        }
        input[type="text"], select, textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        textarea {
            resize: vertical;
        }
        .checkbox-group {
            margin-top: 10px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>生徒カルテ更新</h2>
    <form action="../teacher/StudentRecordUpdateExecute.action" method="post">
        <input type="hidden" name="studentId" value="${studentRecord.studentId}">

        <div class="form-group">
            <label>生徒氏名</label>
            <input type="text" value="${studentRecord.studentName}" readonly>
        </div>

        <div class="form-group">
            <label>保護者氏名</label>
            <input type="text" value="${studentRecord.guardianName}" readonly>
        </div>

        <div class="form-group">
            <label>生年月日</label>
            <input type="text" value="${studentRecord.birthdate}" readonly>
        </div>

        <div class="form-group">
            <label>クラス</label>
            <select name="classId">
                <c:forEach var="classItem" items="${classList}">
                    <option value="${classItem.classId}" ${classItem.classId == studentRecord.classId ? 'selected' : ''}>
                        ${classItem.className}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>特徴</label>
            <textarea name="features" rows="4">${studentRecord.features}</textarea>
        </div>

        <div class="form-group">
            <label>アレルギー情報</label>
            <div class="checkbox-group">
                <c:forEach var="allergy" items="${allergyList}">
                    <label>
                        <input type="checkbox" name="allergy_id" value="${allergy.allergyId}"
                        <c:if test="${allergy.isChecked}">checked</c:if>>
                        ${allergy.allergyName}
                    </label>
                </c:forEach>
            </div>
        </div>

        <div class="form-group">
            <button type="submit">更新する</button>
        </div>
    </form>
</body>
</html>
