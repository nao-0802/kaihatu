import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Attendance;
import bean.Student;
import dao.AttendanceDao;
import dao.StudentDao;
import tool.Action;

public class AttendanceExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String teacherId = req.getParameter("teacherId");  // 教師IDを取得

        // 教師が担当するクラスの生徒リストを取得
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = studentDao.getStudentsByTeacherId(teacherId);

        // 生徒の出欠情報を取得
        AttendanceDao attendanceDao = new AttendanceDao();
        List<Attendance> attendanceList = attendanceDao.getAttendancesByStudents(studentList);

        // 出欠情報をリクエストに設定
        req.setAttribute("attendanceList", attendanceList);

        // 出欠情報一覧ページにフォワード
        RequestDispatcher dispatcher = req.getRequestDispatcher("teacherAttendanceView.jsp");
        dispatcher.forward(req, res);
    }
}
