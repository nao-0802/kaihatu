package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentRecordUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(); // セッションを取得
        Teacher teacher = (Teacher) session.getAttribute("user"); // セッションからログイン中の教師情報を取得

        String studentRecordId = req.getParameter("student_record_id"); // 選択した生徒カルテIDを取得
        StudentDao studentDao = new StudentDao(); // StudentDaoのインスタンス
        Student studentRecord = studentDao.getStudentRecord(studentRecordId, teacher.getSchool()); // 生徒カルテ情報を取得

        // 取得した生徒カルテ情報をリクエスト属性にセット
        req.setAttribute("studentRecord", studentRecord);

        // 生徒カルテ更新画面へフォワード
        req.getRequestDispatcher("Student_record_update.jsp").forward(req, res);
    }
}
