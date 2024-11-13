package admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import java.io.IOException;

import bean.Guardian;  // Guardianクラスをインポート
import dao.GuardianDao;  // GuardianDaoをインポート



public class GuardianUpdateExecuteAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(); // セッションを取得
        Guardian guardian = (Guardian) session.getAttribute("user"); // ログインしている保護者情報をセッションから取得

        String guardian_id = "";  // 入力された保護者ID
        String guardian_name = "";  // 入力された保護者名
        String email = "";  // 入力されたメールアドレス
        String password = "";  // 入力されたパスワード
        Guardian updatedGuardian = new Guardian();  // 送信用の保護者情報
        GuardianDao guardianDao = new GuardianDao();  // GuardianDaoのインスタンス

        // リクエストパラメータの取得
        guardian_id = req.getParameter("guardian_id");
        guardian_name = req.getParameter("guardian_name");
        email = req.getParameter("email");
        password = req.getParameter("password");



        // 送信用の保護者情報の作成
        updatedGuardian.setGuardianId(guardian_id);  // `guardian_id`を設定
        updatedGuardian.setGuardianName(guardian_name);  // `guardian_name`を設定
        updatedGuardian.setEmail(email);  // `email`を設定
        updatedGuardian.setPassword(password);  // `password`を設定

        // 保護者情報の更新処理
        boolean success = guardianDao.update(updatedGuardian);

        if (success) {
            // 更新成功した場合
            req.setAttribute("guardian", updatedGuardian);  // 更新された保護者情報をリクエストにセット
            req.getRequestDispatcher("Guardian_Update_done.jsp").forward(req, res);  // 更新完了ページにフォワード
        } else {
            // 更新失敗した場合
            System.out.println("★更新に失敗しました");
            req.setAttribute("guardian_id", guardian_id);  // 入力された保護者IDを再セット
            req.setAttribute("guardian_name", guardian_name);  // 入力された名前を再セット
            req.setAttribute("email", email);  // 入力されたメールアドレスを再セット
            req.getRequestDispatcher("Guardian_error.jsp").forward(req, res);  // エラー表示ページにフォワード
        }
    }
}
