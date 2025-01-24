package teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Allergy;
import bean.StudentRecord;
import dao.ClassDao;
import dao.StudentRecordDao;
import tool.Action;

public class StudentRecordUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String studentId = request.getParameter("studentId");
        if (studentId == null || studentId.isEmpty()) {
            throw new IllegalArgumentException("Student ID is missing");
        }


        StudentRecordDao dao = new StudentRecordDao();
        ClassDao cDao = new ClassDao();
        StudentRecord record = dao.getStudentRecordByStudentId(studentId);
        List<Allergy> allergies = dao.getAllergiesByStudentId(studentId);
        List<bean.Class> classList = cDao.getAllClasses();

        System.out.println(record.getGuardianName());
        System.out.println(record.getStudentName());


        request.setAttribute("studentRecord", record);
        request.setAttribute("allergyList", allergies);
        request.setAttribute("classList", classList);

        request.getRequestDispatcher("../teacher/student_record_update.jsp").forward(request, response);
    }
}
