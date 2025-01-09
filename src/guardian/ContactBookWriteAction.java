package guardian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import dao.TeacherDao;
import tool.Action;

public class ContactBookWriteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String guardianId = (String) session.getAttribute("guardian_id");
        String studentId = (String) session.getAttribute("student_id");

        System.out.println(guardianId);
        System.out.println(studentId);

        if (guardianId == null || studentId == null) {
            response.sendRedirect("../guardian/login-error.jsp");
            return;
        }

        try {
            // StudentDaoを使用してstudent_idに紐づくclass_idを取得
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.findById(studentId);
            String classId = student.getClassId();

            // TeacherDaoを使用してclass_idに対応するteacher_idとteacher_nameを取得
            TeacherDao teacherDao = new TeacherDao();
            Teacher teacher = teacherDao.findByClassId(classId);
            String teacherId = teacher.getTeacherId();
            String teacherName = teacher.getTeacherName();
            System.out.println(teacherId);

            // リクエストスコープにセット
            request.setAttribute("teacherId", teacherId);
            request.setAttribute("teacherName", teacherName);

            // JSPにフォワード
            request.getRequestDispatcher("../guardian/contactbook_create.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("../guardian/error.jsp");
        }
    }
}
