package scoremanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.Teacher;
import dao.ClassDao;
import dao.TeacherDao;
import tool.Action;

public class TeacherSignupExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("user");
        Map<String, String> errors = new HashMap<>();
        TeacherDao teacherDao = new TeacherDao();
        Teacher teacher = new Teacher();

        // 入力データの取得
        String teacherId = req.getParameter("teacher_id");
        String teacherName = req.getParameter("teacher_name");
        String password = req.getParameter("password");
        String classId = req.getParameter("class_id");

        // バリデーションチェック
        boolean isValid = true;
        if (teacherId == null || teacherId.isEmpty()) {
            errors.put("teacher_id", "教職員IDを入力してください");
            isValid = false;
        } else if (teacherDao.get(teacherId) != null) {
            errors.put("teacher_id", "この教職員IDは既に登録されています");
            isValid = false;
        }

        if (teacherName == null || teacherName.isEmpty()) {
            errors.put("teacher_name", "氏名を入力してください");
            isValid = false;
        }

        if (password == null || password.isEmpty()) {
            errors.put("password", "パスワードを入力してください");
            isValid = false;
        }

        if (classId == null || classId.isEmpty()) {
            errors.put("class_id", "クラスを選択してください");
            isValid = false;
        }

        // エラーがある場合は再表示
        if (!isValid) {
            req.setAttribute("errors", errors);
            req.setAttribute("teacher_id", teacherId);
            req.setAttribute("teacher_name", teacherName);
            req.setAttribute("class_id", classId);

            // クラス選択肢の再設定
            ClassDao classDao = new ClassDao();
            List<bean.Class> classList = classDao.getAllClasses();
            req.setAttribute("class_list", classList);

            req.getRequestDispatcher("/admin/teacher_create.jsp").forward(req, res);
            return;
        }

        // 教職員データを設定
        teacher.setTeacherId(teacherId);
        teacher.setTeacherName(teacherName);
        teacher.setPassword(password);
        teacher.setClassId(classId);

        // データベースに保存
        try {
            teacherDao.save(teacher);
            req.getRequestDispatcher("/admin/teacher_create_done.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            errors.put("database", "登録中にエラーが発生しました。再試行してください。");
            req.setAttribute("errors", errors);

            // クラス選択肢の再設定
            ClassDao classDao = new ClassDao();
            List<bean.Class> classList = classDao.getAllClasses();
            req.setAttribute("class_list", classList);

            req.getRequestDispatcher("/admin/teacher_create.jsp").forward(req, res);
        }
    }
}
