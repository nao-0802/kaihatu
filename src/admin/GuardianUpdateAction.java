package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Guardian;
import dao.GuardianDao;
import tool.Action;

public class GuardianUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //HttpSession session = req.getSession(); // セッションを取得
       // Guardian guardian = (Guardian) session.getAttribute("user"); // セッションからログイン中の保護者情報を取得

        String guardianId = "";  // 受信した保護者ID
        String guardianName = ""; // 受信した保護者氏名
        String email = "";       // 受信したメールアドレス
        String password = "";    // 受信したパスワード
        Guardian updatedGuardian = new Guardian();  // 更新する保護者情報
        GuardianDao guardianDao = new GuardianDao(); // GuardianDaoのインスタンス

        // リクエストパラメータの取得
        guardianId = req.getParameter("guardian_id"); // 保護者IDを取得
        guardianName = req.getParameter("guardian_name"); // 保護者氏名を取得
        email = req.getParameter("email");            // メールアドレスを取得
        password = req.getParameter("password");      // パスワードを取得

        // 入力内容の検証（空欄チェック）
        if (guardianId == null || guardianId.isEmpty() || guardianName == null || guardianName.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            // 必須項目が空の場合、エラーメッセージを設定
            req.setAttribute("errorMessage", "すべてのフィールドを入力してください。");
            req.getRequestDispatcher("Guardian_update.jsp").forward(req, res);  // 再度更新ページにフォワード
            return;
        }

        // guardianIdが"g"で始まるか確認（文字数制限はなし）
        if (!guardianId.startsWith("g")) {
            req.setAttribute("errorMessage", "保護者IDは「g」で始まる必要があります。");
            req.getRequestDispatcher("Guardian_update.jsp").forward(req, res);  // 更新ページに戻る
            return;
        }

        // 更新する保護者情報の設定
        updatedGuardian.setGuardianId(guardianId);  // 保護者ID
        updatedGuardian.setGuardianName(guardianName);  // 保護者氏名
        updatedGuardian.setEmail(email);             // メールアドレス
        updatedGuardian.setPassword(password);       // パスワード

        // 保護者情報の更新処理
        boolean success = guardianDao.save(updatedGuardian);  // 更新処理

        if (success) {
            // 更新成功した場合
            req.setAttribute("guardian", updatedGuardian);  // 更新された保護者情報をリクエストにセット
            req.getRequestDispatcher("Guardian_update_done.jsp").forward(req, res);  // 更新完了ページにフォワード
        } else {
            // 更新失敗した場合
            req.setAttribute("errorMessage", "更新に失敗しました。再試行してください。");
            req.getRequestDispatcher("Guardian_update.jsp").forward(req, res);  // 更新ページに戻る
        }
    }
}
