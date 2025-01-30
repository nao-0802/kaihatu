<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>生徒カルテ更新</title>

    <style>
		    header {
		    position: fixed;  /* 画面上部に固定 */
		    top: 0;  /* 上端に配置 */
		    left: 0;  /* 左端に配置 */
		    width: 100%;  /* 幅を100%に設定 */
		    background-color: #fff;  /* 背景色を白に設定 */
		    z-index: 1000;  /* ヘッダーが他の要素の上に表示されるように */
		    padding: 0.5px;  /* ヘッダー内の余白を調整 */
		    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);  /* ヘッダーに影をつけて浮き上がらせる */
		}
		body {
		    font-family: Arial, sans-serif;
		    background-color: #f9f9f9;
		    display: flex;
		    justify-content: center;  /* 横方向に中央寄せ */
		    align-items: center;  /* 縦方向に中央寄せ */
		    height: 100vh;  /* ビューポートの高さに合わせる */
		    margin: 0;  /* bodyの余白をなくす */
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

<header class="header">
    <div class="navtext-container">
        <p class="navtext">生徒カルテ更新</p>
    </div>
    <%@include file="../common/T_header.jsp" %>
</header>




<body>

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
