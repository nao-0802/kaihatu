package teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudentRecord;
import dao.AllergyDao;
import dao.ClassDao;
import dao.GuardianDao;
import dao.StudentDao;
import dao.StudentRecordDao;
import tool.Action;

public class StudentRecordAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストパラメータから生徒IDを取得
        String studentId = req.getParameter("student_id");



     // 必要な情報を取得するDAOを準備
        StudentDao sDao = new StudentDao();
        GuardianDao gDao = new GuardianDao();
        StudentRecordDao studentRecordDao = new StudentRecordDao();
        ClassDao cDao = new ClassDao();
        AllergyDao allergyDao = new AllergyDao();

        //student_idからguardian_idを取得
        String guardianId = gDao.getGuardianIdbyStudentId(studentId);

        // student_idからstudent_nameを取得
        String studentName = sDao.getStudentNameByGuardianId(guardianId);

        // guardian_idからguardian_nameを取得
        String guardianName = gDao.getGuardianName(guardianId);

        // student_idからstudent_record情報を取得
        StudentRecord studentRecord = studentRecordDao.getStudentRecord(studentId);

        // student_idからallergy_idを取得し、allergy_nameを取得
        List<String> allergyNames = allergyDao.getAllergyNamesByStudentId(studentId);

        String classId = studentRecord.getClassId();
        System.out.println(classId);
        String className = cDao.getClassNameById(classId);


        // リクエストスコープにデータをセット
        req.setAttribute("studentName", studentName);
        req.setAttribute("guardianName", guardianName);
        req.setAttribute("studentRecord", studentRecord);
        req.setAttribute("allergyNames", allergyNames);
        req.setAttribute("className", className);
        req.setAttribute("studentId", studentId);

     // JSPへフォワード
        req.getRequestDispatcher("../teacher/student_record_detail.jsp").forward(req, res);

    }
}
