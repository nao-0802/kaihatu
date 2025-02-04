<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.ContactBook" %>

<body>
    <header class="header">
        <div class="navtext-container">
            <p class="navtext">連絡帳</p>
        </div>
        <%@include file="../common/T_header.jsp" %>
    </header>

    <main>
        <div class="container">
            <table class="contact-table">
                <thead>
                    <tr>
                        <th>生徒名</th>
                        <th>日付</th>
                        <th>詳細</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<ContactBook> contactBooks = (List<ContactBook>) request.getAttribute("contactBookList");
                        if (contactBooks != null && !contactBooks.isEmpty()) {
                            for (ContactBook book : contactBooks) {
                    %>
                        <tr>
                            <td><%= book.getStudentName() %></td>
                            <td><%= book.getDay() %></td>
                            <td>
                                <a href="ContactBookListExecute.action?contactBookId=<%= book.getContactBookId() %>" class="btn">詳細</a>
                            </td>
                        </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="3">連絡帳がありません。</td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <div class="back-link">
                <form action="LifeRecord.action" method="post">
                    <button name="student_id" value="${studentId}">戻る</button>
                </form>
            </div>
        </div>
    </main>
</body>

<style>
    /* 全体のスタイル */
    body {
        font-family: 'Arial', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f8f9fa;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh; /* 画面全体の高さを確保 */
        text-align: center;
    }

    /* ヘッダーのデザイン（元の色に戻す） */
    .header {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        background-color: #757575; /* 元のグレー色に戻す */
        color: white;
        padding: 15px 0;
        text-align: center;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .navtext {
        font-size: 22px;
        font-weight: bold;
        letter-spacing: 2px;
    }

    /* ページ中央に配置 */
    main {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        width: 100%;
        height: 100%;
    }

    .container {
        width: 90%;
        max-width: 1000px; /* 最大幅を広げる */
        background: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    /* テーブル */
    .contact-table {
        width: 100%;
        border-collapse: collapse;
        background-color: #ffffff;
        text-align: center;
    }

    .contact-table th, .contact-table td {
        padding: 15px;
        border-bottom: 1px solid #ddd;
    }

    .contact-table th {
        background-color: #757575; /* ヘッダーと統一 */
        color: white;
        font-weight: bold;
    }

    .contact-table td {
        color: #555;
    }

    .contact-table td[colspan="3"] {
        color: #888;
        font-style: italic;
    }

    /* ボタン */
    .btn {
        display: inline-block;
        padding: 10px 20px;
        color: white;
        background-color: #007bff;
        text-decoration: none;
        border-radius: 5px;
        transition: background 0.3s ease;
        font-size: 16px;
    }

    .btn:hover {
        background-color: #0056b3;
    }

    /* 戻るボタン */
    .back-link {
        margin-top: 20px;
    }

    .back-btn {
        background-color: #6c757d;
    }

    .back-btn:hover {
        background-color: #5a6268;
    }

    /* スマホ対応 */
    @media (max-width: 768px) {
        .container {
            width: 95%;
        }

        .contact-table {
            font-size: 14px;
        }

        .btn {
            font-size: 14px;
            padding: 8px 15px;
        }
    }
</style>
