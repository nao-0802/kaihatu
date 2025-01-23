package teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Allergy;
import bean.StudentRecord;
import dao.StudentRecordDao;
import tool.Action;

public class StudentRecordUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentId = request.getParameter("studentId");

        StudentRecordDao dao = new StudentRecordDao();
        StudentRecord record = dao.getStudentRecordByStudentId(studentId);
        List<Allergy> allergies = dao.getAllergiesByStudentId(studentId);
        List<String> classList = dao.getAllClasses();

        request.setAttribute("studentRecord", record);
        request.setAttribute("allergyList", allergies);
        request.setAttribute("classList", classList);

        request.getRequestDispatcher("../teacher/student_record_update.jsp").forward(request, response);
    }
}
