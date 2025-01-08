package scoremanager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Guardian;
import dao.GuardianDao;
import dao.StudentRecordDao;
import tool.Action;

public class LoginGuardianExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言
        String id = req.getParameter("guardian_id"); // 保護者ID
        String password = req.getParameter("password"); // パスワード

        GuardianDao guardianDao = new GuardianDao();
        StudentRecordDao srDao = new StudentRecordDao();
        Guardian guardian = guardianDao.login(id, password); // DBからデータ取得

        if (guardian != null) { // 認証成功の場合
            HttpSession session = req.getSession(true);
            session.setAttribute("user", guardian); // セッションに保存
            session.setAttribute("guardian_id", id); // 追加: guardian_idもセッションに保存
            req.setAttribute("guardianID", id);

            // 保護者IDを基に生徒IDを取得
            List<String> studentIds = srDao.getStudentIdsByGuardianId(id); // guardian_idを基にstudent_idを取得

            if (studentIds != null && !studentIds.isEmpty()) {
                // 最初の student_id を取得しセッションに保存
                String studentId = studentIds.get(0);
                session.setAttribute("student_id", studentId);
            } else {
                // student_idが取得できなかった場合のエラーハンドリング
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Student ID is null.");
                return;  // エラーの場合は処理を終了
            }

            req.getRequestDispatcher("/guardian/seikatukiroku.jsp").forward(req, res);
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
