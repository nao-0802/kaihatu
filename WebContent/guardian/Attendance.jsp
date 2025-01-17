<%@page contentType="text/html; charset=UTF-8" %>

<body>
<header class="header">
    <div class="navtext-container">
        <p class="navtext">欠席等連絡</p>
    </div>

    <%@include file="../common/G_header.jsp" %>
</header>

<main>

  <div>
    <form action="AttendanceCreateExecute.action" method="post">
        <table id="table0">
            <tr>
                <th class="a">日付</th>
                <td><input type="date" name="day" id="day" value=""></td>
            </tr>
        </table>

        <table id="table01">
            <tr>
                <th class="a">連絡内容</th>
                <td>
                    <select id="type" name="type" required onchange="aviewChange();">
                        <option value="" selected disabled>選択してください</option>
                        <option value="欠席">欠席</option>
                        <option value="遅刻">遅刻</option>
                        <option value="早退">早退</option>
                    </select>
                </td>
            </tr>
        </table>

        <div class="renraku_remarks">
            <div id="1" style="display:none;">
                <table>
                    <tr>
                        <th class="a">登校する時間</th>
                        <td><input type="time" name="time"></td>
                    </tr>
                </table>
            </div>

            <div id="2" style="display:none;">
                <table>
                    <tr>
                        <th class="a">早退する時間</th>
                        <td><input type="time" name="time"></td>
                    </tr>
                </table>
            </div>
        </div>

        <table id="table01">
            <tr>
                <th class="a">理由</th>
                <td>
                    <div class="reason">
                    <select class="form-reason" id="symptom" name="reason" required onchange="viewChange();">
                        <option value="" selected disabled>選択してください</option>
                        <option value="体調不良">体調不良</option>
                        <option value="通院のため">通院のため</option>
                        <option value="家庭の事情">家庭の事情</option>
                    </select>
                    </div>
                </td>
            </tr>
        </table>

        <div class="reason_remarks">
            <div id="Box1" style="display:none;">
                <a>当てはまる症状を選択してください。</a>
                <table>
                    <tr>
                        <td><label><input type="checkbox" value="かぜ" name="symptoms">かぜ</label></td>
                        <td><label><input type="checkbox" value="発熱" name="symptoms">発熱</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="checkbox" value="咳" name="symptoms">咳</label></td>
                        <td><label><input type="checkbox" value="鼻水・くしゃみ" name="symptoms">鼻水・くしゃみ</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="checkbox" value="腹痛" name="symptoms">腹痛</label></td>
                        <td><label><input type="checkbox" value="嘔吐・下痢" name="symptoms">嘔吐・下痢</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="checkbox" value="頭痛" name="symptoms">頭痛</label></td>
                        <td><label><input type="checkbox" value="発作" name="symptoms">発作</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="checkbox" value="感染予防" name="symptoms">感染予防</label></td>
                        <td><label><input type="checkbox" value="その他" name="symptoms">その他</label></td>
                    </tr>
                </table>
            </div>
        </div>

        <a><label for="notes">備考欄</label></a><br>
        <textarea id="notes" name="notes" rows="4" cols="50" placeholder="その他連絡事項をご記入ください。"></textarea>
        <input type="submit">
    </form>
</div>

</main>

<h2 id="selectedSymptom"></h2>

<style>
main {
    margin-top: 52px;
    margin-left: auto;
    margin-right: auto;
}

.back {
  margin-top: 5px;
}

button {
  height: 30px;
}

table {
    width: 300px;
    border-collapse: collapse;
    margin-top: 5px;
    margin-bottom: 5px;
}

th, td {
    vertical-align: middle;
}

th {
    font-weight: normal;
}

#table01 tr {
    border-top: 1px solid #b5b1b1;
}

#table0 th, #table0 td {
    padding: 12px 0;
    border: none;
}

#table0 th {
    width: 50%;
}

#table01 th, #table01 td {
    padding: 12px 0;
    border: none;
}

#table01 th {
    width: 50%;
}

textarea {
    resize: none;
    width: 300px;
    height: 50px;
    margin-bottom: 5px;
}

input[type="submit"] {
    display: block;
    margin-left: auto;
    width: 60px;
    height: 30px;
}

.reason_remarks {
    margin-bottom: 15px;
}

form {
    padding-bottom: 40px;
}

/* sp */
@media only screen and (max-width: 480px) {
    #table0 th, #table0 td {
        width: 100%;
        display: block;
    }

    #table0 th {
        width: 100%;
    }

    #table0 td {
        padding-top: 0;
        margin-left: auto;
        margin-right: auto;
        text-align: center;
    }

    #table01 th, #table01 td {
        width: 100%;
        display: block;
    }

    #table01 th {
        width: 100%;
    }

    #table01 td {
        padding-top: 0;
        margin-left: auto;
        margin-right: auto;
        text-align: center;
    }
}
</style>

<script>
    function displayDateTime() {
        const now = new Date();
        const date = new Date();
        const yyyy = date.getFullYear();
        const mm = ('0' + (date.getMonth() + 1)).slice(-2);
        const dd = ('0' + date.getDate()).slice(-2);
        document.getElementById("day").value = `${yyyy}-${mm}-${dd}`;
    }

    displayDateTime();

    function aviewChange() {
        const typeSelect = document.getElementById('type');
        const timeInput = document.querySelector('input[name="time"]'); // 時間入力フィールド

        if (typeSelect) {
            const id = typeSelect.value;

            // 遅刻の場合
            if (id === '遅刻') {
                document.getElementById('1').style.display = ""; // 登校する時間を表示
                document.getElementById('2').style.display = "none"; // 早退の時間は非表示
                timeInput.value = ''; // 時間をリセット
            }
            // 早退の場合
            else if (id === '早退') {
                document.getElementById('1').style.display = "none"; // 登校する時間は非表示
                document.getElementById('2').style.display = ""; // 早退する時間を表示
                console.log("Early leave time input is visible.");
                console.log("Initial time input value:", timeInput.value); // 初期値を確認

                if (!timeInput.value) {
                    console.error("Error: Time input for early leave is empty.");
                }
                timeInput.value = ''; // 時間をリセットしない
            }

            // それ以外（欠席）の場合
            else {
                document.getElementById('1').style.display = "none"; // 登校する時間を非表示
                document.getElementById('2').style.display = "none"; // 早退の時間を非表示
                timeInput.value = ''; // 時間をリセット
            }
        }
    }

    window.onload = aviewChange;

    function viewChange() {
        const symptomSelect = document.getElementById('symptom');
        const symptomBox = document.getElementById('Box1');
        const symptomText = document.getElementById('selectedSymptom');

        if (symptomSelect) {
            const id = symptomSelect.value;
            if (id === '体調不良') { // 体調不良
                symptomBox.style.display = "";
                symptomText.innerHTML = ''; // 症状欄をリセット
            } else if (id === '通院のため') { // 通院のため
                symptomBox.style.display = "none";
                symptomText.innerHTML = '通院のため'; // 通院のためを表示
            } else if (id === '家庭の事情') { // 家庭の事情
                symptomBox.style.display = "none";
                symptomText.innerHTML = '家庭の事情'; // 家庭の事情を表示
            }
        }
    }

    window.onload = viewChange;

    window.addEventListener('pageshow', () => {
        if (window.performance.navigation.type == 2) location.reload();
    });

    // 症状がチェックされたときに選ばれた症状をH2に表示
    function updateSymptoms() {
        const symptomText = document.getElementById('selectedSymptom');
        const checkboxes = document.querySelectorAll('#Box1 input[type="checkbox"]:checked');
        let selectedSymptoms = [];
        checkboxes.forEach((checkbox) => {
            selectedSymptoms.push(checkbox.value); // valueを文字列で取得
        });
        symptomText.innerHTML = selectedSymptoms.join(', ');
    }

    // チェックボックスが変更されたときに症状を更新
    document.querySelectorAll('#Box1 input[type="checkbox"]').forEach((checkbox) => {
        checkbox.addEventListener('change', updateSymptoms);
    });
</script>

</body>