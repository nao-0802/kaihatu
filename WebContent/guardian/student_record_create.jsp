<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>
<%
    // 生徒リストを取得（controllerから設定されたリスト）
    List<Student> students = (List<Student>) request.getAttribute("students");
    String errorMessage = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>生徒カルテ作成</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            font-weight: bold;
        }

        .error-message {
            color: red;
            font-size: 0.9em;
            margin-bottom: 15px;
        }

        button {
            background-color: #007bff;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .flex-table {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }

        .flex-table label {
            flex: 1 1 calc(33% - 10px);
        }

        .hidden {
            display: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>生徒カルテ作成</h1>

        <%-- エラーメッセージを表示 --%>
        <% if (errorMessage != null) { %>
            <div class="error-message">
                <p><%= errorMessage %></p>
            </div>
        <% } %>

        <form action="../guardian/StudentRecordCreateExecute.action" method="post">
            <%-- 生徒氏名入力欄 --%>
            <div class="form-group">
                <label for="student_name">生徒氏名:</label>
                <input type="text" name="student_name" id="student_name" required>
            </div>

            <%-- 生年月日入力欄 --%>
            <div class="form-group">
                <label for="birthdate">生年月日:</label>
                <input type="date" name="birthdate" id="birthdate" required>
            </div>

            <%-- 食物アレルギーの有無選択 --%>
            <div class="form-group">
                <label>食物アレルギー:</label><br>
                <label><input type="radio" name="allergy" value="yes" required> ある</label>
                <label><input type="radio" name="allergy" value="no" required> ない</label>
            </div>

            <%-- アレルギーリスト（条件表示） --%>
            <div class="form-group hidden" id="allergy-list">
                <label>該当するアレルギーにチェックを入れてください:</label>
                <div class="flex-table">
                    <label><input type="checkbox" name="allergy_id" value="0"> エビ</label>
                    <label><input type="checkbox" name="allergy_id" value="1"> 卵</label>
                    <label><input type="checkbox" name="allergy_id" value="2"> カニ</label>
                    <label><input type="checkbox" name="allergy_id" value="3"> 乳</label>
                    <label><input type="checkbox" name="allergy_id" value="4"> 小麦</label>
                    <label><input type="checkbox" name="allergy_id" value="5"> 落花生</label>
                    <label><input type="checkbox" name="allergy_id" value="6"> そば</label>
                    <label><input type="checkbox" name="allergy_id" value="7"> 牛肉</label>
                    <label><input type="checkbox" name="allergy_id" value="8"> イカ</label>
                    <label><input type="checkbox" name="allergy_id" value="9"> あわび</label>
                    <label><input type="checkbox" name="allergy_id" value="10"> カシューナッツ</label>
                    <label><input type="checkbox" name="allergy_id" value="11"> りんご</label>
                    <label><input type="checkbox" name="allergy_id" value="12"> オレンジ</label>
                    <label><input type="checkbox" name="allergy_id" value="13"> ゴマ</label>
                    <label><input type="checkbox" name="allergy_id" value="14"> 豚肉</label>
                    <label><input type="checkbox" name="allergy_id" value="15"> さば</label>
                    <label><input type="checkbox" name="allergy_id" value="16"> いくら</label>
                    <label><input type="checkbox" name="allergy_id" value="17"> くるみ</label>
                    <label><input type="checkbox" name="allergy_id" value="18"> キウイ</label>
                    <label><input type="checkbox" name="allergy_id" value="19"> まつたけ</label>
                    <label><input type="checkbox" name="allergy_id" value="20"> 大豆</label>
                    <label><input type="checkbox" name="allergy_id" value="21"> 鶏肉</label>
                    <label><input type="checkbox" name="allergy_id" value="22"> さけ</label>
                    <label><input type="checkbox" name="allergy_id" value="23"> アーモンド</label>
                    <label><input type="checkbox" name="allergy_id" value="24"> もも</label>
                    <label><input type="checkbox" name="allergy_id" value="25"> バナナ</label>
                    <label><input type="checkbox" name="allergy_id" value="26"> やまいも</label>
                    <label><input type="checkbox" name="allergy_id" value="27"> ゼラチン</label>
                </div>
            </div>

            <%-- 生徒特徴入力欄 --%>
            <div class="form-group">
                <label for="features">生徒の特徴:</label>
                <textarea name="features" id="features" rows="4" style="width: 100%;" placeholder="例: 活発で社交的、集中力がある等"></textarea>
            </div>

            <%-- 登録ボタン --%>
            <div class="form-group">
                <button type="submit">登録</button>
            </div>
        </form>
    </div>

    <script>
        // アレルギー有無選択時の動作
        document.addEventListener('DOMContentLoaded', () => {
            const allergyRadios = document.querySelectorAll('input[name="allergy"]');
            const allergyList = document.getElementById('allergy-list');

            allergyRadios.forEach(radio => {
                radio.addEventListener('change', () => {
                    if (radio.value === 'yes') {
                        allergyList.classList.remove('hidden');
                    } else {
                        allergyList.classList.add('hidden');
                    }
                });
            });
        });
    </script>
</body>
</html>
