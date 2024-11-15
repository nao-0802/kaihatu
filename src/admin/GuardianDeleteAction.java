package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Guardian;
import dao.GuardianDao;
import tool.Action;

public class GuardianDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //HttpSession session = req.getSession();

        //Admin admin = (Admin)session.getAttribute("user");

        // 削除対象のguardian_idを取得
        String guardianId = req.getParameter("guardian_id");

        GuardianDao gDao = new GuardianDao();
        Guardian guardian = gDao.get(guardianId);

        if (guardian != null) {
            // 保護者情報をリクエストに設定
            req.setAttribute("guardian_id", guardian.getGuardianId());
            req.setAttribute("guardian_name", guardian.getGuardianName());
            req.setAttribute("email", guardian.getEmail());

            // 削除操作
            boolean deleted = gDao.delete(guardianId);
            req.setAttribute("deleted", deleted);

            // 削除結果に応じてメッセージを設定
            if (deleted) {
                req.setAttribute("message", "Guardian deleted successfully.");
            } else {
                req.setAttribute("message", "Failed to delete Guardian.");
            }
        } else {
            req.setAttribute("message", "Guardian not found.");
        }

        // 削除確認ページにフォワード
        req.getRequestDispatcher("guardian_delete.jsp").forward(req, res);
    }
}
