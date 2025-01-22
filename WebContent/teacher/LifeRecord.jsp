<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="bean.StudentRecord, java.util.List"%>
<head>
    <meta charset="UTF-8">
    <title>生活記録</title>
    <script>
        // 記録成功時にアラートを表示する関数
        function showSuccessAlert() {
            alert("記録が正常に保存されました！");
        }
    </script>
    <style>
        main {
            margin-top: 52px;
            margin-left: auto;
            margin-right: auto;
            width: 80%;
        }

        .button-container {
            margin-top: 10px;
            margin-bottom: 10px;
            text-align: right;
            width: 100%;
        }

        .button {
            padding: 5px;
            margin-right: 10px;
        }

        .title1, #body1 {
            border: 2px solid rgb(0, 128, 0);
        }
        .title2, #body2 {
            border: 2px solid rgb(230, 230, 0);
        }
        .title3, #body3 {
            border: 2px solid rgb(200, 15, 15);
        }
        .title4, #body4 {
            border: 2px solid rgb(15, 104, 200);
        }

        .tab-title {
            width: 85px;
            padding: 5px 5px;
            text-align: center;
            display: table;
        }

        .tab-body {
            display: none;
            width: 220px;
            height: 150px;
            padding: 10px;
        }

        .container .radio {
            display: none;
        }

        .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center; /* タブを中央に配置 */
            align-items: center;     /* タブを垂直方向にも中央に配置 */
            text-align: center;      /* テキストを中央揃えにする */
        }

        .container::after {
            content: "";
            width: 100%;
        }

        .container .tab-body {
            order: 1;
            margin-top: 10px;
        }

        .add-control .radio:checked + .tab-title {
            color: #000000;
        }

        #tab1:checked ~ .title1 {
            background: rgba(0, 128, 0, 0.5);
        }

        #tab2:checked ~ .title2 {
            background: rgba(255, 255, 0, 0.5);
        }

        #tab3:checked ~ .title3 {
            background: rgba(255, 0, 0, 0.5);
        }

        #tab4:checked ~ .title4 {
            background: rgba(0, 140, 255, 0.5);
        }

        #tab1:checked ~ #body1,
        #tab2:checked ~ #body2,
        #tab3:checked ~ #body3,
        #tab4:checked ~ #body4 {
            display: block;
        }

        form {
            text-align: center;
        }

        .kiroku {
            width: 100px;
            margin-top: 10px;
        }

        .student-info {
            font-size: 18px;
            margin-bottom: 10px;
            text-align: center; /* 中央に表示 */
        }
    </style>
</head>

<script>
    function onlyOne(checkbox) {
        var checkboxes = document.getElementsByName('tab');
        checkboxes.forEach((item) => {
            if (item !== checkbox) item.checked = false;
        });
    }

    function autoCheck(textValue, radioId) {
        document.getElementById(radioId).checked = textValue.length > 0;
    }

    window.addEventListener('pageshow', () => {
        if (window.performance.navigation.type == 2) location.reload();
    });
</script>

<body>
<header class="header">
    <div class="navtext-container">
        <p class="navtext">生活記録</p>
    </div>
    <%@include file="../common/T_header.jsp" %>
</header>

<main>
    <!-- 生徒名を表示 -->
    <div class="student-info">
        <p>名前: ${list.studentName} (${list.studentId})</p>
    </div>

    <div class="button-container">
    <form action="../teacher/ContactBookWrite.action" method="get">
    <input type="hidden" name="student_id" value="${list.studentId}">
    <button type="submit" class="button">連絡帳を書く</button>
</form>


    <!-- カルテ閲覧ボタン -->
    <form action="../teacher/StudentRecord.action" method="get">
        <input type="hidden" name="student_id" value="${list.studentId}">
        <button type="submit" class="button">カルテ閲覧</button>
    </form>



    </div>

    <div class="container add-control">
        <input type="checkbox" id="tab1" class="radio" name="tab" onclick="onlyOne(this)">
        <label class="tab-title title1" id="title1" for="tab1">睡眠</label>
        <input type="checkbox" id="tab2" class="radio" name="tab" onclick="onlyOne(this)">
        <label class="tab-title title2" id="title2" for="tab2">ごはん</label>
        <input type="checkbox" id="tab3" class="radio" name="tab" onclick="onlyOne(this)">
        <label class="tab-title title3" id="title3" for="tab3">排泄</label>
        <input type="checkbox" id="tab4" class="radio" name="tab" onclick="onlyOne(this)">
        <label class="tab-title title4" id="title4" for="tab4">服薬</label>

        <div class="tab-body" id="body1">
            <details name="life" id="a">
                <form action="../teacher/SleepRecordExecute.action" method="post" onsubmit="showSuccessAlert();">
                    <input type="hidden" name="student_id" value="${list.studentId}">
                    <table>
                        <tr>
                            <td><label><input type="radio" name="sleep" value="0" required>寝た</label></td>
                        </tr>
                        <tr>
                            <td><label><input type="radio" name="sleep" value="1" required>起きた</label></td>
                        </tr>
                    </table>
                    <button type="submit">記録</button>
                </form>
            </details>
        </div>

        <div class='tab-body' id="body2">
            <details name="life" id="b">
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
        </div>

        <div class="tab-body" id="body3">
            <details name="life" id="c">
                <form action="../teacher/ExcretionRecordExecute.action" method="post" onsubmit="showSuccessAlert();">
                    <input type="hidden" name="student_id" value="${list.studentId}">
                    <div>
                        <label><input type="radio" name="type" value="0">かたい</label><br>
                        <label><input type="radio" name="type" value="1">やわらかい</label><br>
                        <label>その他:</label>
                        <input type="text" name="excretion_detail" placeholder="詳細を記入"><br>
                    </div>
                    <button type="submit">記録</button>
                </form>
            </details>
        </div>

        <div class="tab-body" id="body4">
            <details name="life" id="medicine">
                <form action="../teacher/MedicineRecordExecute.action" method="post" onsubmit="showSuccessAlert();">
                    <input type="hidden" name="student_id" value="${list.studentId}">
                    <div>
                        <label><input type="radio" name="medicine" value="0" required>服薬済み</label><br>
                        <button type="submit">記録</button>
                    </div>
                </form>
            </details>
        </div>
    </div>
</main>



</body>
