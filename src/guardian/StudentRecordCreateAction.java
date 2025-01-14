package guardian;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentRecordCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        StudentDao studentDao = new StudentDao();
        List<Student> students = studentDao.getAllStudents(); // 全生徒情報取得
        req.setAttribute("students", students);
        req.getRequestDispatcher("/guardian/student_record_create.jsp").forward(req, res);
    }
}
