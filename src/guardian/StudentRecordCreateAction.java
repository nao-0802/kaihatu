package guardian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentRecord;
import dao.StudentRecordDao;
import tool.Action;

public class StudentRecordCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        String studentRecordId = req.getParameter("student_record_id");

        // 生徒カルテのDAO
        StudentRecordDao recordDao = new StudentRecordDao();
        StudentRecord studentRecord = recordDao.getRecordByStudentRecordId(studentRecordId);  // student_record_id で検索

        // カルテが存在しない場合、新規登録フォームを表示
        if (studentRecord == null) {
            req.setAttribute("showCreateButton", true);  // 新規登録ボタンを表示
            req.getRequestDispatcher("student_record_create.jsp").forward(req, res);  // 入力フォームを表示
        } else {
            req.setAttribute("studentRecord", studentRecord);  // カルテ情報を表示
            req.getRequestDispatcher("student_record_guardian.jsp").forward(req, res);  // カルテ表示ページへ
        }
    }
}
