<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.ContactBook, java.util.List" %>

<%
    // サーバーから渡されたリストを取得
    @SuppressWarnings("unchecked")
    List<ContactBook> guardianList = (List<ContactBook>) request.getAttribute("guardianList");
    List<ContactBook> teacherList = (List<ContactBook>) request.getAttribute("teacherList");
%>

<!-- 戻るリンク -->
<a href="student_list.jsp">← 戻る</a>

<!-- 日付と保護者の選択フォーム -->
<form action="ContactBookSearchAction" method="post">
    <p>日付を選択:</p>
    <input type="date" name="selectedDate" required>

    <p>保護者を選択:</p>
    <select name="GuardianId" required>
        <%
            if (guardianList != null) {
                for (ContactBook contact : guardianList) {
        %>
                    <option value="<%= contact.getGuardinaId() %>">
                        <%= contact.getGuardinaId() %>
                    </option>
        <%
                }
            }
        %>
    </select>

    <button type="submit">検索</button>
</form>

<!-- 保護者からの連絡表示 -->
<h3>保護者からの連絡</h3>
<table border="1">
    <tr>
        <th>日付</th>
        <th>連絡内容</th>
    </tr>
    <%
        if (guardianList != null) {
            for (ContactBook contact : guardianList) {
    %>
                <tr>
                    <td><%= contact.getDay() %></td>
                    <td><%= contact.getContactDetails() %></td>
                </tr>
    <%
            }
        } else {
    %>
            <tr>
                <td colspan="2">保護者からの連絡はありません。</td>
            </tr>
    <%
        }
    %>
</table>

<!-- 教職員からの連絡表示 -->
<h3>教職員からの連絡</h3>
<table border="1">
    <tr>
        <th>日付</th>
        <th>連絡内容</th>
    </tr>
    <%
        if (teacherList != null) {
            for (ContactBook contact : teacherList) {
    %>
                <tr>
                    <td><%= contact.getDay() %></td>
                    <td><%= contact.getContactDetails() %></td>
                </tr>
    <%
            }
        } else {
    %>
            <tr>
                <td colspan="2">教職員からの連絡はありません。</td>
            </tr>
    <%
        }
    %>
</table>