package teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentRecord;
import dao.StudentRecordDao;
import tool.Action;

public class StudentRecordUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(); // セッションを取得
        // ログインしているユーザーの情報が必要であれば、以下のように取得
        // User user = (User) session.getAttribute("user");

        String studentRecordId; // 受信した生徒カルテID
        StudentRecordDao studentRecordDao = new StudentRecordDao(); // StudentRecordDaoのインスタンス
        StudentRecord studentRecord; // 生徒カルテ情報受け取り用

        // リクエストパラメータの取得
        studentRecordId = req.getParameter("id"); // 生徒カルテIDを取得

        // 生徒カルテ情報の取得
        studentRecord = studentRecordDao.get(studentRecordId); // 生徒カルテ情報をデータベースから取得

        // 取得した生徒カルテ情報をリクエスト属性にセット
        req.setAttribute("student_record_id", studentRecord.getStudentRecordId());
        req.setAttribute("name", studentRecord.getName());
        req.setAttribute("class_id", studentRecord.getClassId());
        req.setAttribute("guardian_id", studentRecord.getGuardianId());
        req.setAttribute("birthdate", studentRecord.getBirthdate());
        req.setAttribute("allergy", studentRecord.getAllergy());
        req.setAttribute("features", studentRecord.getFeatures());
        req.setAttribute("annual_record", studentRecord.getAnnualRecord());


        // 更新画面へフォワード
        req.getRequestDispatcher("student_record_update.jsp").forward(req, res);
    }
}
