<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>生徒カルテ更新</title>
    <style>
        /* 基本のスタイル設定 */
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f4f4f9;
            color: #333;
        }

        h1 {
            text-align: center;
            color: #4CAF50;
        }

        form {
            width: 60%;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            margin-bottom: 20px;
        }

        td {
            padding: 10px;
            text-align: left;
        }

        label {
            font-size: 16px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"],
        input[type="number"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        button {
            padding: 12px 20px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            background-color: #45a049;
        }

        /* エラーメッセージ用スタイル */
        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <h1>生徒カルテ更新</h1>

    <!-- エラーメッセージ表示 -->
    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>

    <form action="StudentRecordUpdateExecuteAction" method="POST">
        <table>
            <tr>
                <td><label for="student_record_id">生徒カルテID:</label></td>
                <td><input type="text" id="student_record_id" name="student_record_id" value="${studentRecord.studentRecordId}" required maxlength="10" placeholder="srから始まるIDを入力してください。" /></td>
            </tr>
            <tr>
                <td><label for="name">生徒氏名:</label></td>
                <td><input type="text" id="name" name="name" value="${studentRecord.name}" required maxlength="100" /></td>
            </tr>
            <tr>
                <td><label for="class_id">クラスID:</label></td>
                <td><input type="text" id="class_id" name="class_id" value="${studentRecord.classId}" required maxlength="50" /></td>
            </tr>
            <tr>
                <td><label for="guardian_id">保護者ID:</label></td>
                <td><input type="text" id="guardian_id" name="guardian_id" value="${studentRecord.guardianId}" required maxlength="10" placeholder="gから始まるIDを入力してください。" /></td>
            </tr>
            <tr>
                <td><label for="birthdate">生年月日:</label></td>
                <td><input type="date" id="birthdate" name="birthdate" value="${studentRecord.birthdate}" required /></td>
            </tr>
            <tr>
                <td><label for="allergy">アレルギー:</label></td>
                <td><input type="text" id="allergy" name="allergy" value="${studentRecord.allergy}" maxlength="100" /></td>
            </tr>
            <tr>
                <td><label for="features">特徴:</label></td>
                <td><input type="text" id="features" name="features" value="${studentRecord.features}" maxlength="100" /></td>
            </tr>
            <tr>
                <td><label for="annual_record">年次記録:</label></td>
                <td><input type="text" id="annual_record" name="annual_record" value="${studentRecord.annualRecord}" maxlength="100" /></td>
            </tr>
        </table>
        <button type="submit">更新</button>
    </form>
</body>
</html>
