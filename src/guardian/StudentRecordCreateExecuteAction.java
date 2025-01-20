package guardian;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.StudentRecord;
import dao.StudentAllergyDao;
import dao.StudentDao;
import dao.StudentRecordDao;
import tool.Action;

public class StudentRecordCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentName = request.getParameter("student_name");
        String features = request.getParameter("features");
        Date birthdate = Date.valueOf(request.getParameter("birthdate"));
        String[] allergyIds = request.getParameterValues("allergy_id");
        System.out.println(allergyIds);

        StudentDao studentDao = new StudentDao();
        Student student = studentDao.findStudentByName(studentName);

        if (student == null) {
            request.setAttribute("error", "生徒が見つかりません。");
            request.getRequestDispatcher("../guardian/student_record_create.jsp").forward(request, response);
            return;
        }

        String studentRecordId = generateRandomId();

        StudentRecord record = new StudentRecord();
        record.setStudentRecordId(studentRecordId);
        record.setClassId(student.getClassId());
        record.setGuardianId((String) request.getSession().getAttribute("guardian_id"));
        record.setBirthdate(birthdate);
        record.setFeatures(features);
        record.setStudentId(student.getStudentId());

        StudentRecordDao recordDao = new StudentRecordDao();
        recordDao.save(record);

        if (allergyIds != null) {
            StudentAllergyDao allergyDao = new StudentAllergyDao();
            for (String allergyId : allergyIds) {
                allergyDao.save(student.getStudentId(), Integer.parseInt(allergyId));
            }
        }

        response.sendRedirect("../guardian/student_record_create_done.jsp");
    }

    private String generateRandomId() {
        return Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0, 10);
    }
}
