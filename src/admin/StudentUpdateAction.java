package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();

        // 学生IDを取得
        String studentId = req.getParameter("student_id");

        if (studentId == null || studentId.isEmpty()) {
            req.setAttribute("error", "学生IDが指定されていません。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        StudentDao sDao = new StudentDao();
        Student student = sDao.get(studentId);

        if (student == null) {
            req.setAttribute("error", "指定された学生が見つかりません。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        req.setAttribute("student", student);
        req.getRequestDispatcher("student_update.jsp").forward(req, res);
    }
}
