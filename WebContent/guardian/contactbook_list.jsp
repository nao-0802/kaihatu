<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>連絡帳一覧</title>
    <style>

        main {
            margin-top: 40px;
            padding: 20px;
            max-width: 2000px; /* 画面を広く使うために最大幅を広げた */
            margin-left: auto;
            margin-right: auto;
            border-radius: 20px;
            line-height: 1.5;
        }

        table th, table td {
            padding: 15px 100px; /* パディングを広げて、余白を確保 */
            border: 1px solid #ddd;
            text-align: left;
            word-wrap: break-word; /* 長いテキストが折り返されるようにする */
        }

        table th {
            background-color: #e9ecef;
            color: #495057;
            font-size: 1.1em;
        }

        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        form input[type="submit"] {
            padding: 12px 30px; /* ボタンのサイズ */
            background-color: #7FBF7F;
            border: none;
            border-radius: 4px;
            color: white;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.3s ease;
            margin-top: 10px;
            width: 100%; /* 横に広げる */
        }

        /* 戻るボタンのスタイル */
.back-btn {
    padding: 10px 15px;
    background-color: #7FBF7F;
    border: none;
    color: white;
    font-size: 20px;
    cursor: pointer;
    position: absolute;
    top: 20%; /* 画面中央に設定 */
    left: 200px; /* 左側に配置 */
    transform: translateY(-50%); /* 垂直方向に中央揃え */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}



    </style>
</head>
<body>
<button class="back-btn" onclick="history.back()"> 戻る</button>

    <header class="header">
        <div class="navtext-container">
            <p class="navtext">連絡帳閲覧</p>
        </div>

        <%@include file="../common/G_header.jsp" %>
    </header>



    <main>
        <table>
            <tr>
                <th style="width: 50%; text-align: center;">日付</th>
                <th style="width: 50%; text-align: center;">詳細</th>
            </tr>
            <c:forEach var="contactBook" items="${contactBookList}">
                <tr>
                    <td>${contactBook.day}</td>
                    <td>
                        <form action="ContactBookListExecute.action" method="get">
                            <input type="hidden" name="contact_book_id" value="${contactBook.contactBookId}" />
                            <input type="submit" value="詳細を見る" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </main>
</body>
</html>
