package teacher;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.StudentRecord;
import dao.StudentDao;
import dao.StudentRecordDao;
import tool.Action;

public class StudentListExecuteAction extends Action {
    public void execute(
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PrintWriter out = response.getWriter();

        try {
            // セッションからクラスIDを取得
            HttpSession session = request.getSession();
            String classId = (String) session.getAttribute("classId");

            if (classId == null || classId.isEmpty()) {
                // クラスIDがセッションに存在しない場合、エラーメッセージを表示して終了
                request.setAttribute("error", "クラスIDが指定されていません。");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            // 生徒DAOを使用して、クラスIDに一致する生徒情報を取得
            StudentDao studentDao = new StudentDao();
            List<Student> studentList = studentDao.filterByClassId(classId);
            request.setAttribute("studentList", studentList);

            // 生徒記録DAOを使用して、生徒情報から対応する生徒記録を取得
            StudentRecordDao recordDao = new StudentRecordDao();
            List<StudentRecord> studentRecordList = new ArrayList<>();

            for (Student student : studentList) {
                String studentId = student.getStudentId(); // 生徒IDを取得
                StudentRecord record = recordDao.getByStudentId(studentId); // 生徒記録を取得
                if (record != null) {
                    studentRecordList.add(record); // 生徒記録リストに追加
                }
            }

            // リクエストに生徒記録リストをセット
            request.setAttribute("studentRecordList", studentRecordList);

            // JSPページにフォワード
            request.getRequestDispatcher("student_list.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
