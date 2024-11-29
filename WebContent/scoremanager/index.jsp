<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>JSPからJSPへの遷移</title>
    <script>
        function navigateToPage() {
            var selectedPage = document.getElementById("pageSelect").value;
            if (selectedPage) {
                window.location.href = selectedPage;
            }
        }
    </script>
</head>
<body>
    <h1>ページ選択</h1>
    <form>
        <label for="pageSelect">移動先を選択してください:</label>
        <select id="pageSelect" onchange="navigateToPage()">
            <option value="">選択してください</option>
            <option value="index_admin.jsp">管理者</option>
            <option value="index_teacher.jsp">教職員</option>
            <option value="index_guardian.jsp">保護者</option>
        </select>
    </form>
</body>
</html>