package guardian;

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
        String birthdate = req.getParameter("birthdate");
        String allergy = req.getParameter("allergy");

        // 生徒カルテのDAO
        StudentRecordDao recordDao = new StudentRecordDao();
        StudentRecord studentRecord = new StudentRecord();

        // パラメータの設定
        studentRecord.setStudentRecordId(studentRecordId);
        studentRecord.setName(name);
        studentRecord.setBirthdate(birthdate);
        studentRecord.setAllergy(allergy);

        // 入力内容の検証
        if (name == null || name.isEmpty() || birthdate == null || birthdate.isEmpty()) {
            req.setAttribute("errorMessage", "すべての項目を入力してください。");
            req.getRequestDispatcher("student_record_create.jsp").forward(req, res);
        } else {
            // 入力確認画面に遷移
            req.setAttribute("name", name);
            req.setAttribute("birthdate", birthdate);
            req.setAttribute("allergy", allergy);
            req.getRequestDispatcher("student_record_create_confirm.jsp").forward(req, res);  // 入力内容確認画面
        }
    }
}
