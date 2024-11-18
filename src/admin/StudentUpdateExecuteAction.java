package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String studentId = req.getParameter("student_id");
        String studentName = req.getParameter("student_name");
        String password = req.getParameter("password");
        String classId = req.getParameter("class_id");
        String flagStr = req.getParameter("flag");

        if (studentId == null || studentId.isEmpty() || studentName == null || studentName.isEmpty()) {
            req.setAttribute("error", "学生IDまたは名前が入力されていません。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        int flag = (flagStr != null && !flagStr.isEmpty()) ? Integer.parseInt(flagStr) : 0;

        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentName(studentName);
        student.setPassword(password);
        student.setClassId(classId);
        student.setFlag(flag);

        StudentDao sDao = new StudentDao();
        if (sDao.save(student)) {
            req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
        } else {
            req.setAttribute("error", "学生情報の更新に失敗しました。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
