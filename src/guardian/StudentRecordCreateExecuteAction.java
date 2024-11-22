package guardian;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentRecord;
import dao.StudentRecordDao;
import tool.Action;

public class StudentRecordCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        String studentRecordId = req.getParameter("student_record_id");
        String name = req.getParameter("name");
        String birthdateStr = req.getParameter("birthdate"); // birthdate を String として取得
        String allergy = req.getParameter("allergy");

        // 生徒カルテのDAO
        StudentRecordDao recordDao = new StudentRecordDao();
        StudentRecord studentRecord = new StudentRecord();

        // birthdate を String から java.sql.Date に変換
        Date birthdate = null;
        if (birthdateStr != null && !birthdateStr.isEmpty()) {
            try {
                birthdate = Date.valueOf(birthdateStr); // "YYYY-MM-DD" フォーマットを期待
            } catch (IllegalArgumentException e) {
                req.setAttribute("errorMessage", "誕生日の形式が不正です。YYYY-MM-DD の形式で入力してください。");
                req.getRequestDispatcher("student_record_create.jsp").forward(req, res);
                return;
            }
        }

        // パラメータの設定
        studentRecord.setStudentRecordId(studentRecordId);
        studentRecord.setName(name);
        studentRecord.setBirthdate(birthdate); // 型変換後の birthdate を設定
        studentRecord.setAllergy(allergy);

        // 入力内容の検証
        if (name == null || name.isEmpty() || birthdate == null) {
            req.setAttribute("errorMessage", "すべての項目を入力してください。");
            req.getRequestDispatcher("student_record_create.jsp").forward(req, res);
        } else {
            // 入力確認画面に遷移
            req.setAttribute("name", name);
            req.setAttribute("birthdate", birthdateStr); // フォーマット済みの文字列を設定
            req.setAttribute("allergy", allergy);
            req.getRequestDispatcher("student_record_create_confirm.jsp").forward(req, res);  // 入力内容確認画面
        }
    }
}

