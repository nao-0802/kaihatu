package scoremanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.Student;
import dao.StudentDao;
import tool.Action;

public class GuardianSignupAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        HttpSession session = req.getSession();
        // Admin情報をセッションから取得
        Admin admin = (Admin) session.getAttribute("user");

        // 初期値設定
        req.setAttribute("guardianId", "保護者IDを入力してください");
        req.setAttribute("guardianName", "氏名を入力してください");
        req.setAttribute("password", "パスワードを入力してください");
        req.setAttribute("studentId", null);

        String guardianId = "";
        String guardianName = "";
        String password = "";
        String studentId = "";

        List<Student> studentList = null;
        StudentDao studentDao = new StudentDao();
        Map<String, String> errors = new HashMap<>();

        // パラメータ取得
        guardianId = req.getParameter("guardian_id");
        guardianName = req.getParameter("guardian_name");
        password = req.getParameter("password");
        studentId = req.getParameter("student_id");

        int chk = 0;
        try {
            // 生徒一覧を取得
            studentList = studentDao.getAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
            errors.put("studentList", "生徒情報の取得中にエラーが発生しました。");
            req.setAttribute("errors", errors);
        }

        // 入力チェック
        if (guardianId == null || guardianId.isEmpty()) {
            if (chk > 0) {
                errors.put("guardianId", "保護者IDを入力してください");
                req.setAttribute("errors1", errors);
            } else {
                chk += 1;
            }
        } else if (guardianName == null || guardianName.isEmpty()) {
            errors.put("guardianName", "氏名を入力してください");
            req.setAttribute("errors2", errors);
        } else if (password == null || password.isEmpty()) {
            errors.put("password", "パスワードを入力してください");
            req.setAttribute("errors3", errors);
        } else if (studentId == null || studentId.equals("0")) {
            errors.put("studentId", "生徒を選択してください");
            req.setAttribute("errors4", errors);
        } else {
            Map<String, String> data = new HashMap<>();
            data.put("guardianId", guardianId);
            data.put("guardianName", guardianName);
            data.put("password", password);
            data.put("studentId", studentId);

            // 次のアクションへフォワード
            req.getRequestDispatcher("GuardianSignupExecute.action").forward(req, res);
            return;
        }

        // 入力データの設定
        req.setAttribute("guardian_id", guardianId);
        req.setAttribute("guardian_name", guardianName);
        req.setAttribute("password", password);
        req.setAttribute("student_id", studentId);

        req.setAttribute("student_list", studentList);
        req.getRequestDispatcher("/admin/guardian_create.jsp").forward(req, res);
    }
}
