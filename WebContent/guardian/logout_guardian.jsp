<%@ page contentType="text/html; charset=UTF-8" %>

<body>


    <main>
        <div class="logout">
            <h3 class="conf_message">本当にログアウトしますか？</h3>
        </div>

        <div class="button">
            <form action="../scoremanager/LogoutGuardianExecute.action">
                <div><button class="btn yes-btn" name="yes_btn">はい</button></div>
            </form>

            <div><button class="btn no-btn" name="no_btn" onclick="history.back()">いいえ</button></div>
        </div>
    </main>
</body>

<style>
    /* 全体のスタイル */
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }

    header {
        background-color: #4CAF50;
        color: white;
        padding: 10px 0;
        text-align: center;
    }

    .navtext-container {
        display: inline-block;
    }

    .navtext {
        font-size: 24px;
        font-weight: bold;
    }

    /* メインコンテンツ */
    main {
        margin-top: 60px;
        padding: 20px;
        text-align: center;
    }

    .logout h3 {
        font-size: 22px;
        color: #333;
        margin-bottom: 30px;
    }

    .button {
        display: flex;
        justify-content: center;
        gap: 20px;
        margin-top: 20px;
    }

    .btn {
        padding: 15px 25px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease, transform 0.2s ease;
    }

    .yes-btn {
        background-color: #4CAF50;
        color: white;
    }

    .yes-btn:hover {
        background-color: #45a049;
        transform: scale(1.05);
    }

    .no-btn {
        background-color: #f44336;
        color: white;
    }

    .no-btn:hover {
        background-color: #e53935;
        transform: scale(1.05);
    }

</style>
