package scoremanager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Guardian;
import dao.GuardianDao;
import tool.Action;

public class LoginGuardianExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言
        String id = req.getParameter("guardian_id"); // 管理者ID
        String password = req.getParameter("password"); // パスワード

        GuardianDao guardianDao = new GuardianDao();
        Guardian guardian = guardianDao.login(id, password); // DBからデータ取得

        if (guardian != null) { // 認証成功の場合
            HttpSession session = req.getSession(true);
            session.setAttribute("user", guardian); // セッションに保存
            req.getRequestDispatcher("/guardian/seikatukiroku.jsp")
            .forward(req, res);
        } else { // 認証失敗の場合
            List<String> errors = new ArrayList<>();
            errors.add("IDまたはパスワードが確認できませんでした");
            req.setAttribute("errors", errors);
            req.setAttribute("guardianID", id);

            // エラーログ
            System.out.println("/guardian/login-error.jsp");

            req.getRequestDispatcher("/guardian/login-error.jsp").forward(req, res);
        }
    }
}
