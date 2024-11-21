package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Guardian;
import bean.Teacher;
import dao.GuardianDao;
import dao.TeacherDao;
import tool.Action;

public class AccountListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {
        // DAOインスタンス作成
        TeacherDao tDao = new TeacherDao();
        GuardianDao gDao = new GuardianDao();


        // 教職員データ、保護者データ、管理者データを取得
        List<Teacher> teacher = tDao.getAllTeachers();
        List<Guardian> guardian = gDao.getAllGuardians();


        // リクエストスコープにデータをセット
        req.setAttribute("teacher", teacher);
        req.setAttribute("guardian", guardian);

        // 画面遷移
        try {
            req.getRequestDispatcher("/admin/login-out.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
