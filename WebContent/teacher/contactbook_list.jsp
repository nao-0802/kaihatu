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
                // リクエストスコープから contactBookList を取得
                List<ContactBook> contactBooks = (List<ContactBook>) request.getAttribute("contactBookList");
                if (contactBooks != null && !contactBooks.isEmpty()) {
                    for (ContactBook book : contactBooks) {
            %>
                <tr>
                    <td><%= book.getStudentName() %></td>
                    <td><%= book.getDay() %></td>
                    <td>
                        <a href="ContactBookListExecute.action?contactBookId=<%= book.getContactBookId() %>">詳細</a>
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

</body>

<style>
    /* Body全体のスタイル */
    body {
        font-family: 'Arial', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f9;
        line-height: 1.6;
        padding-top: 0.5px; /* ヘッダー分の余白を追加 */
        text-align: center; /* 全体のテキストを中央寄せ */
    }

    /* ヘッダーのスタイル */
    header {
        text-align: center;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        z-index: 1000; /* ヘッダーを最前面に表示 */

        color: white;
        padding: 5px 0;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .navtext-container {
        margin: 0;
    }

    /* テーブルのスタイル */
    table.contact-table {
        width: 90%;
        margin: 120px auto 20px; /* ヘッダー下に余白を追加 */
        border-collapse: collapse;
        background-color: #ffffff;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        text-align: center; /* テーブル内全体を中央寄せ */
    }

    table.contact-table th, table.contact-table td {
        padding: 15px;
        text-align: center; /* ヘッダーとデータのテキストを中央寄せ */
        border-bottom: 1px solid #ddd;
    }

    table.contact-table th {
        background-color: #fff; /* 背景色を白に設定 */
        color: black; /* 文字色を黒に設定 */
        font-weight: bold; /* 文字を太字に設定 */
    }

    table.contact-table td {
        color: #555;
    }

    table.contact-table a {
        color: #007bff;
        text-decoration: none;
        padding: 5px;
        border: 1px solid #007bff;
        border-radius: 5px;
        transition: all 0.3s ease;
    }

    table.contact-table a:hover {
        background-color: #007bff;
        color: #fff;
        text-decoration: none;
    }

    /* 連絡帳がない場合のメッセージ */
    table.contact-table td[colspan="3"] {
        color: #888;
        font-style: italic;
    }

    /* スマホ対応 */
    @media (max-width: 768px) {
        table.contact-table {
            width: 100%;
            font-size: 14px;
        }

        .navtext {
            font-size: 2em;
        }

        body {
            padding-top: 100px; /* スマホでヘッダー分余白を調整 */
        }
    }
</style>
