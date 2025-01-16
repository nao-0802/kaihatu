package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Guardian;
import dao.GuardianDao;
import tool.Action;

public class GuardianUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {



        GuardianDao gDao = new GuardianDao();

     // フォームから送信されたデータを取得
        String guardianId = req.getParameter("guardianId");  // 保護者ID
         String guardianName = req.getParameter("guardianName");  // 氏名
         String password = req.getParameter("password");  // パスワード




        // Guardianオブジェクトを作成して値を設定
        Guardian guardian = new Guardian();
        guardian.setGuardianId(guardianId);
        guardian.setGuardianName(guardianName);

        guardian.setPassword(password);

        // 保護者情報の更新処理
        boolean success = gDao.save(guardian);

        if (success) {
            // 更新成功時
            req.getRequestDispatcher("/admin/Guardian_update_done.jsp").forward(req, res);
        } else {
            // 更新失敗時（任意でエラーメッセージを表示する場合）
            req.setAttribute("errorMessage", "情報の更新に失敗しました。再度試してください。");
            req.getRequestDispatcher("/admin/Guardian_update.jsp").forward(req, res);
        }
    }
}
