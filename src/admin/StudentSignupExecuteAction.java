package admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassDao;
import dao.StudentDao;
import tool.Action;

public class StudentSignupExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {


        Map<String, String> errors = new HashMap<>();
        StudentDao studentDao = new StudentDao();
        Student student = new Student();

        // 入力データの取得
        String studentId = req.getParameter("student_id");
        String studentName = req.getParameter("student_name");
        String classId = req.getParameter("class_id");
        int flag = 0;

        // バリデーションチェック
        boolean isValid = true;
        if (studentId == null || studentId.isEmpty()) {
            errors.put("student_id", "生徒IDを入力してください");
            isValid = false;
        } else if (studentDao.get(studentId) != null) {
            errors.put("student_id", "この生徒IDは既に登録されています");
            isValid = false;
        }

        if (studentName == null || studentName.isEmpty()) {
            errors.put("student_name", "氏名を入力してください");
            isValid = false;
        }


        if (classId == null || classId.isEmpty()) {
            errors.put("class_id", "クラスを選択してください");
            isValid = false;
        }

        // エラーがある場合は再表示
        if (!isValid) {
            req.setAttribute("errors", errors);
            req.setAttribute("student_id", studentId);
            req.setAttribute("student_name", studentName);
            req.setAttribute("class_id", classId);

            // クラス選択肢の再設定
            ClassDao classDao = new ClassDao();
            List<bean.Class> classList = classDao.getAllClasses();
            req.setAttribute("class_list", classList);

            req.getRequestDispatcher("../admin/student_create.jsp").forward(req, res);
            return;
        }

        // 教職員データを設定
        student.setStudentId(studentId);
        student.setStudentName(studentName);
        student.setClassId(classId);
        student.setFlag(flag);

        // データベースに保存
        try {
            studentDao.save(student);
            req.getRequestDispatcher("../admin/student_create_done.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            errors.put("database", "登録中にエラーが発生しました。再試行してください。");
            req.setAttribute("errors", errors);

            // クラス選択肢の再設定
            ClassDao classDao = new ClassDao();
            List<bean.Class> classList = classDao.getAllClasses();
            req.setAttribute("class_list", classList);

            req.getRequestDispatcher("/admin/student_create_done.jsp").forward(req, res);
        }
    }
}
