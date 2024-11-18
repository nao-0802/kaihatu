package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Guardian;
import dao.GuardianDao;
import tool.Action;

public class GuardianUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションの取得（必要に応じて使用可能）
        HttpSession session = req.getSession();

        // リクエストパラメータからguardian_idを取得
        String guardianId = req.getParameter("guardian_id");
        GuardianDao guardianDao = new GuardianDao();
        Guardian guardian = guardianDao.get(guardianId);

        if (guardian != null) {
            // 取得した保護者情報をリクエストに設定
            req.setAttribute("guardian_id", guardian.getGuardianId());
            req.setAttribute("guardian_name", guardian.getGuardianName());
            req.setAttribute("email", guardian.getEmail());
            req.setAttribute("password", guardian.getPassword());

            // 保護者情報のリストを取得してリクエストに設定
            List<Guardian> guardians = guardianDao.getAllGuardians();
            req.setAttribute("guardians", guardians);

            // 保護者情報更新ページにフォワード
            req.getRequestDispatcher("guardian_update.jsp").forward(req, res);
        } else {
            // 保護者情報が見つからない場合、エラーメッセージを設定
            req.setAttribute("errorMessage", "指定された保護者が見つかりません。");
            req.getRequestDispatcher("guardian_update.jsp").forward(req, res);
        }
    }
}
