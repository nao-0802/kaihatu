package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import dao.MasterAdminDao;
import tool.Action;

public class AdminSignupExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // パラメータ取得
        String masterId = req.getParameter("master_id");
        String masterPassword = req.getParameter("master_password");
        String adminId = req.getParameter("admin_id");
        String adminPassword = req.getParameter("password");

        MasterAdminDao masterAdminDao = new MasterAdminDao();
        AdminDao adminDao = new AdminDao();

        // マスターアカウント認証
        if (!masterAdminDao.validateMasterAccount(masterId, masterPassword)) {
            req.setAttribute("error", "マスターアカウント認証に失敗しました。");
            req.getRequestDispatcher("../admin/admin_signup.jsp").forward(req, res);
            return;
        }

        // 管理者アカウント保存
        boolean isRegistered = adminDao.registerAdmin(adminId, adminPassword);
        if (isRegistered) {
            req.getRequestDispatcher("../admin/admin_signup_done.jsp").forward(req, res);
        } else {
            req.setAttribute("error", "管理者アカウント登録に失敗しました。");
            req.getRequestDispatcher("admin_signup.jsp").forward(req, res);
        }
    }
}
