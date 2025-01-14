package guardian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentRecordDao;
import tool.Action;

public class StudentRecordCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String studentId = req.getParameter("student_id");
        String birthdate = req.getParameter("birthdate");
        String allergy = req.getParameter("allergy");
        HttpSession session = req.getSession(false);
        String guardianId = (String) session.getAttribute("guardian_id");

        StudentRecordDao srDao = new StudentRecordDao();
        srDao.createStudentRecord(studentId, birthdate, allergy, guardianId);

        req.getRequestDispatcher("../guardian/student_record_create_done.jsp").forward(req, res);
    }
}
