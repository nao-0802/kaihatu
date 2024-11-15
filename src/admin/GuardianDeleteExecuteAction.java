package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GuardianDao;
import tool.Action;

public class GuardianDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // GuardianDaoのインスタンスを作成
        GuardianDao gDao = new GuardianDao();

        // リクエストから削除対象のguardian_idを取得
        String guardianId = req.getParameter("guardian_id");

        // 削除操作を実行
        Boolean chk = gDao.delete(guardianId);

        // 削除が成功した場合、完了ページに転送
        if (chk) {
            req.getRequestDispatcher("guardian_delete_done.jsp").forward(req, res);
        } else {
            // 削除に失敗した場合、エラーページまたはメッセージを設定
            req.setAttribute("message", "Failed to delete Guardian.");
            req.getRequestDispatcher("guardian_delete_failed.jsp").forward(req, res);
        }
    }
}
