<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>生徒カルテ更新</title>
    <style>
        /* ヘッダーの固定配置 */
        header {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            background-color: #fff;
            z-index: 1000;
            padding: 10px 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        /* 全体レイアウト */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            margin: 0;
            padding-top: 80px; /* ヘッダーと被らないよう調整 */
        }

        /* メインフォームのスタイル */
        main {
            width: 90%;
            max-width: 800px; /* 横幅を広めに */
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        /* ラベル */
        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        /* 入力欄のスタイル */
        input[type="text"], select, textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        /* テキストエリアのリサイズ制限 */
        textarea {
            resize: vertical;
        }

        /* チェックボックスのスタイル */
        .checkbox-group label {
            display: block;
            margin-top: 5px;
        }

        /* ボタンエリア（ページ下部中央） */
        .buttons {
            display: flex;
            justify-content: space-between;
            gap: 15px;
            margin-top: 30px;
        }

        /* ボタンのスタイル */
        button {
            flex: 1;
            padding: 14px;
            font-size: 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            color: white;
            transition: 0.3s ease-in-out;
        }

        /* 更新ボタン */
        .submit-btn {
            background-color: #4CAF50;
        }

        .submit-btn:hover {
            background-color: #45a049;
            transform: scale(1.05);
        }

        /* 戻るボタン */
        .back-btn {
            background-color: #ccc;
        }

        .back-btn:hover {
            background-color: #bbb;
            transform: scale(1.05);
        }

        /* スマホ対応 */
        @media screen and (max-width: 768px) {
            main {
                width: 95%;
            }

            .buttons {
                flex-direction: column;
                align-items: center;
            }

            button {
                width: 100%;
                margin-bottom: 10px;
            }
        }
    </style>
</head>

<body>

<header class="header">
    <div class="navtext-container">
        <p class="navtext">生徒カルテ更新</p>
    </div>
    <%@include file="../common/T_header.jsp" %>
</header>

<main>
<br>
<br>
<br>
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
                        <c:if test="${allergy.isChecked}">checked</c:if> >
                        ${allergy.allergyName}
                    </label>
                </c:forEach>
            </div>
        </div>

        <!-- ボタンエリア -->
        <div class="buttons">
            <button type="submit" class="submit-btn">更新する</button>
        </div>
    </form>
    <!-- ボタンエリア -->
        <div class="buttons">
            <form action="../teacher/StudentRecord.action" method="post">
                <button class="back-btn" name="student_id" value="${studentRecord.studentId}">戻る</button>
            </form>
        </div>
</main>

</body>
</html>
