<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="bean.Student, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>生活記録</title>
</head>
<body>
    <div class="container">
        <!-- 戻るボタン -->
        <div class="box blue">
        <form action="ClassSelectExecute.action" method="post">
        <button name="class_id" value="${list.classId}">←</button>
        </form></div>

        <!-- 学生情報 -->
        <p>名前:
                ${list.studentName} (${list.studentId})
        </p>

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

        <!-- 連絡帳 -->
        <form action="ContactBookAction">
            <div class="box"><input type="button" value="連絡帳"></div>
        </form>

        <!-- 服薬 -->
        <div class="box">
            <select>
                <option value="服薬なし">服薬なし</option>
                <option value="朝服薬">朝服薬</option>
                <option value="昼服薬">昼服薬</option>
                <option value="夜服薬">夜服薬</option>
            </select>
        </div>

        <!-- 睡眠 -->
        <div class="box">
            <form action="SleepRecordExecuteAction">
            	<input type="hidden" name="student_id" value="${student.studentId}">
                <select>
                    <option name="sleep_type" value="1">寝た</option>
                    <option name="sleep_type" value="0">起きた</option>
                </select>
                <input type="button" value="登録">
            </form>
        </div>

        <!-- 食事 -->
        <div class="box">
            <form action="MealRecordExecuteAction">
            	<input type="hidden" name="student_id" value="${student.studentId}">
                <select>
                    <option name="meal_type" value="0">大量</option>
                    <option name="meal_type" value="1">半量</option>
                    <option name="meal_type" value="2">少量</option>
                    <option name="meal_type" value="3">食べてない</option>
                </select>
                <input type="button" value="登録">
            </form>
        </div>

        <!-- 排泄 -->
        <div class="box">
            <form action="ExcretionRecordExecuteAction">
            	<input type="hidden" name="student_id" value="${student.studentId}">
                <select>
                    <option name="excretion_type" value="0">排泄あり</option>
                    <option name="excretion_type" value="1">排泄なし</option>
                </select>
                <input type="button" value="登録">
            </form>
        </div>
    </div>
</body>
</html>
