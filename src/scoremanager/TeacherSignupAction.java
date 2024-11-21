package scoremanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.Class;
import dao.ClassDao;
import tool.Action;

public class TeacherSignupAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("user");

        // 初期値設定
        req.setAttribute("teacherId", "教職員IDを入力してください");
        req.setAttribute("teacherName", "氏名を入力してください");
        req.setAttribute("password", "パスワードを入力してください");
        req.setAttribute("classId", null);

        String teacherId = "";
        String teacherName = "";
        String password = "";
        String classId = "";

        List<Class> classList = null;
        ClassDao classDao = new ClassDao();
        Map<String, String> errors = new HashMap<>();

        // パラメータ取得
        teacherId = req.getParameter("teacher_id");
        teacherName = req.getParameter("teacher_name");
        password = req.getParameter("password");
        classId = req.getParameter("class_id");

        int chk = 0;
        try {
            // クラス一覧を取得
            classList = classDao.getAllClasses();
        } catch (Exception e) {
            e.printStackTrace();
            errors.put("classList", "クラス情報の取得中にエラーが発生しました。");
            req.setAttribute("errors", errors);
        }

        // 入力チェック
        if (teacherId == null || teacherId.isEmpty()) {
            if (chk > 0) {
                errors.put("teacherId", "教職員IDを入力してください");
                req.setAttribute("errors1", errors);
            } else {
                chk += 1;
            }
        } else if (teacherName == null || teacherName.isEmpty()) {
            errors.put("teacherName", "氏名を入力してください");
            req.setAttribute("errors2", errors);
        } else if (password == null || password.isEmpty()) {
            errors.put("password", "パスワードを入力してください");
            req.setAttribute("errors3", errors);
        } else if (classId == null || classId.equals("0")) {
            errors.put("classId", "クラスを選択してください");
            req.setAttribute("errors4", errors);
        } else {
            Map<String, String> data = new HashMap<>();
            data.put("teacherId", teacherId);
            data.put("teacherName", teacherName);
            data.put("password", password);
            data.put("classId", classId);

            // 次のアクションへフォワード
            req.getRequestDispatcher("TeacherSignupExecute.action").forward(req, res);
            return;
        }

        // 入力データの設定
        req.setAttribute("teacher_id", teacherId);
        req.setAttribute("teacher_name", teacherName);
        req.setAttribute("password", password);
        req.setAttribute("class_id", classId);

        req.setAttribute("class_list", classList);
        req.getRequestDispatcher("/admin/teacher_create.jsp").forward(req, res);
    }
}
