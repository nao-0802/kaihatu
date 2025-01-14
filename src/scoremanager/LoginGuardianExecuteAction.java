package scoremanager;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Guardian;
import bean.Student;
import dao.GuardianDao;
import dao.StudentDao;
import dao.StudentRecordDao;
import tool.Action;

public class LoginGuardianExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String id = req.getParameter("guardian_id");
        String password = req.getParameter("password");

        GuardianDao guardianDao = new GuardianDao();
        StudentRecordDao srDao = new StudentRecordDao();
        Guardian guardian = guardianDao.login(id, password);

        if (guardian != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", guardian);
            session.setAttribute("guardian_id", id);
            req.setAttribute("guardianID", id);

            List<String> studentIds = srDao.getStudentIdsByGuardianId(id);

            if (studentIds != null && !studentIds.isEmpty()) {
                String studentId = studentIds.get(0);
                session.setAttribute("student_id", studentId);
                req.getRequestDispatcher("../guardian/seikatukiroku.jsp").forward(req, res);
            } else {
            	StudentDao studentDao = new StudentDao();
            	List<Student> students = studentDao.getAllStudents(); // 全ての生徒を取得
            	req.setAttribute("students", students); // リクエストスコープに設定
                req.getRequestDispatcher("../guardian/student_record_create.jsp").forward(req, res);
            }
        } else {
            req.setAttribute("errors", Arrays.asList("IDまたはパスワードが確認できませんでした"));
            req.getRequestDispatcher("../guardian/login-error.jsp").forward(req, res);
        }
    }
}
