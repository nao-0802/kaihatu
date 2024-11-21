<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>カルテ詳細画面</title>
    <style>
        /* 全体のスタイル */
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f6f9;
            color: #333;
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        /* ヘッダー部分の削除後、空きスペースを調整 */
        h1 {
            text-align: center;
            font-size: 24px;
            color: #007BFF;
            margin-bottom: 20px;
        }

        /* ボタンセクション */
        .button-container {
            display: flex;
            gap: 20px;
            margin-bottom: 30px;
        }

        .button-container button {
            padding: 12px 25px;
            font-size: 16px;
            border: 2px solid #007BFF;
            background-color: #fff;
            color: #007BFF;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
            width: 150px;
            text-align: center;
        }

        .button-container button:hover {
            background-color: #007BFF;
            color: #fff;
        }

        /* カルテ表示セクション */
        .text-container {
            text-align: center;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 10px;
        }

        .text-container label {
            font-size: 18px;
            font-weight: bold;
            margin-right: 10px;
        }

        .text-container input[type="text"] {
            width: 350px;
            padding: 12px;
            font-size: 16px;
            border: 2px solid #007BFF;
            border-radius: 5px;
            background-color: #f9f9f9;
            text-align: center;
            cursor: not-allowed;
        }

        /* レスポンシブ対応 */
        @media (max-width: 768px) {
            .button-container {
                flex-direction: column;
                align-items: center;
            }

            .text-container input[type="text"] {
                width: 90%;
            }

            .button-container button {
                width: 100%;
            }
        }
    </style>
</head>
<body>

    <h1>カルテ詳細画面</h1>

    <!-- ボタンセクション（画面上部中央） -->
    <div class="button-container">
        <button id="Chart_Read_btn" onclick="location.href='chart.jsp'">カルテ</button>
        <button id="Chart_Read_btn" onclick="location.href='annual_record.jsp'">年次記録</button>
    </div>

    <!-- カルテ表示セクション（ボタンセクションの下） -->
    <div class="text-container">
        <label for="Chart_Indication">カルテ表示:</label>
        <input type="text" id="Chart_Indication" name="Chart_Indication" value="${studentRecord.name}" readonly />
    </div>

</body>
</html>
