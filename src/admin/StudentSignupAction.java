package admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Class;
import dao.ClassDao;
import tool.Action;

public class StudentSignupAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        // 初期値設定
        req.setAttribute("studentId", "生徒IDを入力してください");
        req.setAttribute("studentName", "氏名を入力してください");
        req.setAttribute("classId", null);

        String studentId = "";
        String studentName = "";
        String classId = "";

        List<Class> classList = null;
        ClassDao classDao = new ClassDao();
        Map<String, String> errors = new HashMap<>();

        // パラメータ取得
        studentId = req.getParameter("student_id");
        studentName = req.getParameter("student_name");
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
        if (studentId == null || studentId.isEmpty()) {
            if (chk > 0) {
                errors.put("studentId", "教職員IDを入力してください");
                req.setAttribute("errors1", errors);
            } else {
                chk += 1;
            }
        } else if (studentName == null || studentName.isEmpty()) {
            errors.put("studentName", "氏名を入力してください");
            req.setAttribute("errors2", errors);
        } else if (classId == null || classId.equals("0")) {
            errors.put("classId", "クラスを選択してください");
            req.setAttribute("errors4", errors);
        } else {
            Map<String, String> data = new HashMap<>();
            data.put("studentId", studentId);
            data.put("studentName", studentName);
            data.put("classId", classId);

            // 次のアクションへフォワード
            req.getRequestDispatcher("../admin/StudentSignupExecute.action").forward(req, res);
            return;
        }

        // 入力データの設定
        req.setAttribute("student_id", studentId);
        req.setAttribute("student_name", studentName);
        req.setAttribute("class_id", classId);

        req.setAttribute("class_list", classList);
        req.getRequestDispatcher("../admin/student_create.jsp").forward(req, res);
    }
}
