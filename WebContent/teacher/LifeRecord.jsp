<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.Student, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>生活記録</title>
    <script>
        // 記録成功時にアラートを表示する関数
        function showSuccessAlert() {
            alert("記録が正常に保存されました！");
        }
    </script>
</head>
<body>
    <div class="container">
        <!-- 戻るボタン -->
        <div class="box blue">
            <form action="ClassSelectExecute.action" method="post">
                <button name="class_id" value="${list.classId}">←</button>
            </form>
        </div>

        <!-- 学生情報 -->
        <p>名前: ${list.studentName} (${list.studentId})</p>

        <!-- 詳細 -->
        <div class="box">
            <select>
                <option value="詳細1">詳細1</option>
                <option value="詳細2">詳細2</option>
                <option value="詳細3">詳細3</option>
            </select>
        </div>

        <!-- メモ -->
        <div class="box">メモ</div>

        <!-- 服薬 -->
        <details name="life" id="medicine">
            <summary>服薬</summary>
            <form action="../teacher/MedicineRecordExecute.action" method="post" onsubmit="showSuccessAlert();">
                <input type="hidden" name="student_id" value="${list.studentId}">
                <div>
                    <label><input type="radio" name="medicine" value="0" required>服薬済み</label><br>
                    <button type="submit">記録</button>
                </div>
            </form>
        </details>

        <!-- 睡眠 -->
        <details name="life" id="a">
            <summary>睡眠</summary>
            <form action="../teacher/SleepRecordExecute.action" method="post" onsubmit="showSuccessAlert();">
                <input type="hidden" name="student_id" value="${list.studentId}">
                <div>
                    <label><input type="radio" name="sleep" value="1">寝た</label><br>
                    <label><input type="radio" name="sleep" value="0">起きた</label><br>
                    <button type="submit">記録</button>
                </div>
            </form>
        </details>

        <!-- 食事 -->
        <details name="life" id="b">
            <summary>食事</summary>
            <form action="../teacher/MealRecordExecute.action" method="post" onsubmit="showSuccessAlert();">
                <input type="hidden" name="student_id" value="${list.studentId}">
                <div>
                    <label><input type="radio" name="meal_amount" value="0">全量</label><br>
                    <label><input type="radio" name="meal_amount" value="1">半量</label><br>
                    <label><input type="radio" name="meal_amount" value="2">少量</label><br>
                    <button type="submit">記録</button>
                </div>
            </form>
        </details>

        <!-- 排泄 -->
        <details name="life" id="c">
            <summary>排泄</summary>
            <form action="../teacher/ExcretionRecordExecute.action" method="post" onsubmit="showSuccessAlert();">
                <input type="hidden" name="student_id" value="${list.studentId}">
                <div>
                    <!-- 排泄種別のラジオボタン -->
                    <label><input type="radio" name="type" value="0">かたい</label><br>
                    <label><input type="radio" name="type" value="1">やわらかい</label><br>

                    <!-- その他の詳細を入力するテキストフィールド -->
                    <label>その他:</label>
                    <input type="text" name="excretion_detail" placeholder="詳細を記入"><br>
                </div>
                <!-- 提出ボタン -->
                <button type="submit">記録</button>
            </form>
        </details>
    </div>
</body>
</html>
