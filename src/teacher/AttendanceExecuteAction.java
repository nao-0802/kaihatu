package teacher;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Attendance;
import bean.Student;
import dao.AttendanceDao;

public class AttendanceExecuteAction {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String resultPage = "attendanceResult.jsp"; // 遷移先のページ
        try {
            // リクエストから教師IDまたは学生リストを取得
            String teacherId = request.getParameter("teacherId");
            String[] studentIds = request.getParameterValues("studentIds");

            // AttendanceDao を初期化
            AttendanceDao attendanceDao = new AttendanceDao();
            List<Attendance> attendanceList = new ArrayList<>();

            // 教師IDが提供されている場合
            if (teacherId != null && !teacherId.isEmpty()) {
                attendanceList = attendanceDao.getAttendancesByTeacherId(teacherId);
            }
            // 学生リストが提供されている場合
            else if (studentIds != null && studentIds.length > 0) {
                List<Student> students = new ArrayList<>();
                for (String studentId : studentIds) {
                    Student student = new Student();
                    student.setStudentId(studentId);
                    students.add(student);
                }
                attendanceList = attendanceDao.getAttendancesByStudents(students);
            }

            // 出席情報をリクエストスコープに設定
            request.setAttribute("attendanceList", attendanceList);

        } catch (Exception e) {
            e.printStackTrace();
            // エラーページを設定
            resultPage = "error.jsp";
            request.setAttribute("errorMessage", "出席情報の取得中にエラーが発生しました。");
        }

        return resultPage; // 結果ページを返す
    }
}
