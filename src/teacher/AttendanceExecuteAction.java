import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Attendance;
import bean.Student;
import dao.AttendanceDao;
import dao.StudentDao;
import tool.Action;

public class TeacherAttendanceViewAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String teacherId = req.getParameter("teacher_id"); // 教師ID

        // 教師のクラスに所属する生徒リストを取得
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = studentDao.getStudentsByTeacherId(teacherId);

        // 各生徒に関連する出席情報を取得
        AttendanceDao attendanceDao = new AttendanceDao();
        for (Student student : studentList) {
            // 各生徒の出席情報を取得
            List<Attendance> attendanceList = attendanceDao.getAttendanceByStudentId(student.getStudentId());
            student.setAttendanceList(attendanceList);  // 生徒オブジェクトに出席情報をセット
        }

        // 生徒リストをリクエストに設定
        req.setAttribute("studentList", studentList);

        // 出席情報表示ページにフォワード
        RequestDispatcher dispatcher = req.getRequestDispatcher("TeacherAttendanceView.jsp");
        dispatcher.forward(req, res);
    }
}
