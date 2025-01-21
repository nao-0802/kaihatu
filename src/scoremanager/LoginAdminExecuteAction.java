package scoremanager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.Guardian;
import bean.Student;
import bean.Teacher;
import dao.AdminDao;
import dao.GuardianDao;
import dao.StudentDao;
import dao.TeacherDao;
import tool.Action;

public class LoginAdminExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言
        String id = req.getParameter("admin_id"); // 管理者ID
        String password = req.getParameter("password"); // パスワード
        AdminDao adminDao = new AdminDao();
        Admin admin = null;

        // 未入力チェック
        List<String> errors = new ArrayList<>();
        if (id == null || id.trim().isEmpty()) {
            errors.add("管理者IDを入力してください。");
        }
        if (password == null || password.trim().isEmpty()) {
            errors.add("パスワードを入力してください。");
        }

        // エラーがある場合はログイン画面に戻す
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("adminId", id); // 入力されたIDを保持
            req.getRequestDispatcher("../scoremanager/login_admin.jsp").forward(req, res);
            return;
        }

        // DBからデータ取得
        admin = adminDao.login(id, password); // 管理者データ取得

        // 認証ロジック
        if (admin != null) { // 認証成功の場合
            // セッション情報を取得
            HttpSession session = req.getSession(true);
            session.setAttribute("user", admin);

            // 次の画面に必要なデータをセット
            TeacherDao teacherDao = new TeacherDao();
            GuardianDao guardianDao = new GuardianDao();
            StudentDao studentDao = new StudentDao();
            List<Teacher> teacher = teacherDao.getAllActiveTeachers();
            List<Guardian> guardian = guardianDao.getAllGuardians();
            List<Student> student = studentDao.getAllStudents();

            req.setAttribute("teacher", teacher);
            req.setAttribute("guardian", guardian);
            req.setAttribute("student", student);

            // 認証成功画面にフォワード
            req.getRequestDispatcher("/admin/login-out.jsp").forward(req, res);
        } else { // 認証失敗の場合
            // エラーメッセージをセット
            errors.add("IDまたはパスワードが確認できませんでした。");
            req.setAttribute("errors", errors);

            // 入力されたIDを保持
            req.setAttribute("adminId", id);

            // 新規登録リンクを表示
            req.setAttribute("signupLink", "AdminSignup.action");

            // 認証失敗画面にフォワード
            req.getRequestDispatcher("../scoremanager/login_admin.jsp").forward(req, res);
        }
    }
}
