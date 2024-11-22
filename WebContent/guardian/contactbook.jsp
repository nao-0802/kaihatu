<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>連絡帳の閲覧</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        /* レイアウト用の簡単なスタイル */
        .container {
            display: flex;
        }
        .left-panel {
            width: 200px;
            padding: 20px;
            border-right: 2px solid #000;
        }
        .right-panel {
            flex-grow: 1;
            padding: 20px;
        }
        .button-container {
            position: fixed;
            bottom: 20px;
            left: 20px;
        }
        .contact-table {
            width: 100%;
            border-collapse: collapse;
        }
        .contact-table th, .contact-table td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
    </style>
    <script>
        function validateDate() {
            const dateInput = document.getElementById("date");
            const dateValue = dateInput.value;
            const regex = /^\d{4}-\d{2}-\d{2}$/; // YYYY-MM-DD 形式を確認

            if (!regex.test(dateValue)) {
                alert("日付は YYYY-MM-DD の形式で入力してください。");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

    <h1>連絡帳の閲覧</h1>

    <div class="container">
        <!-- 左側のパネル：日付選択 -->
        <div class="left-panel">
            <h2>日付選択</h2>
            <form action="ContactBookListExecuteAction" method="get" onsubmit="return validateDate()">
                <label for="date">閲覧する日付を入力してください：</label><br>
                <input type="text" id="date" name="selectedDate" placeholder="YYYY-MM-DD" required><br>
                <button type="submit">確認</button>
            </form>

            <div class="button-container">
                <!-- 連絡帳を書くボタン -->
                <form action="ContactBookWrite.action" method="get">
                    <button type="submit">連絡帳を書く</button>
                </form>
            </div>
        </div>

        <!-- 右側のパネル：連絡帳の内容表示 -->
        <div class="right-panel">
            <c:if test="${not empty list}">
                <h2>選択された日付の連絡帳</h2>
                <table class="contact-table">
                    <thead>
                        <tr>
                            <th>先生名</th>
                            <th>連絡内容</th>
                            <th>確認</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="contact" items="${list}">
                            <tr>
                                <td>${contact.teacherName}</td>
                                <td>${contact.contactDetails}</td>
                                <td>${contact.checked ? '確認済み' : '未確認'}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty list}">
                <p>指定された日付の連絡帳は存在しません。</p>
            </c:if>
        </div>
    </div>

</body>
</html>
