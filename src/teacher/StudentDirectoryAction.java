package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentDirectoryAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(); // セッションを取得
        Teacher teacher = (Teacher) session.getAttribute("user"); // セッションからログイン中の教師情報を取得

        StudentDao studentDao = new StudentDao(); // StudentDaoのインスタンス
        List<Student> studentList = studentDao.getAllStudents(teacher.getSchool()); // 生徒一覧を取得

        // 生徒一覧をリクエスト属性にセット
        req.setAttribute("studentList", studentList);

        // 生徒一覧画面へフォワード
        req.getRequestDispatcher("Student_list.jsp").forward(req, res);
    }
}
