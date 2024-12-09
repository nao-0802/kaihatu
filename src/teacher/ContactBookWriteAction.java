package teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Guardian;
import bean.StudentRecord;
import dao.GuardianDao;
import dao.StudentRecordDao;
import tool.Action;

public class ContactBookWriteAction extends Action{

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {

            // リクエストから student_id を取得
            String studentId = req.getParameter("student_id");
            if (studentId == null || studentId.isEmpty()) {
                throw new IllegalArgumentException("Student ID is missing.");
            }


            // student_record テーブルから情報を取得
            StudentRecordDao studentRecordDao = new StudentRecordDao();
            StudentRecord studentRecord = studentRecordDao.findByStudentId(studentId);



            if (studentRecord == null) {
                throw new IllegalArgumentException("Student record not found for ID: " + studentId);
            }



            // guardian テーブルから情報を取得
            GuardianDao guardianDao = new GuardianDao();
            Guardian guardian = guardianDao.findByGuardianId(studentRecord.getGuardianId());

            if (guardian == null) {
                throw new IllegalArgumentException("Guardian not found for ID: " + studentRecord.getGuardianId());
            }



            // リクエストスコープに情報をセット
            req.setAttribute("studentRecord", studentRecord);
            req.setAttribute("guardian", guardian);
            req.setAttribute("guardianId", guardian.getGuardianId());
            req.setAttribute("guardianName", guardian.getGuardianName());


            // JSP にフォワード
            req.getRequestDispatcher("/teacher/contactbook_create.jsp").forward(req, res);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
}
