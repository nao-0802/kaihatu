package teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ContactBook;
import bean.Student;
import dao.ContactBookDao;
import dao.GuardianDao;
import dao.StudentDao;
import tool.Action;

public class ContactBookListReturnAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        String teacherId = (String) session.getAttribute("userId");

        // teacherIdがセッションにない場合、ログインページにリダイレクト
        if (teacherId == null) {
            res.sendRedirect("login.jsp");
            return;
        }

     // リクエストから student_id を取得
        String guardianId = req.getParameter("guardian_id");
        if (guardianId == null || guardianId.isEmpty()) {
            throw new IllegalArgumentException("Guardian ID is missing.");
        }

        GuardianDao guardianDao = new GuardianDao();

        String studentId = guardianDao.getStudentIdByGuardianId(guardianId);



        // ContactBookDaoを使用して、teacherIdに基づく連絡帳情報を取得
        ContactBookDao contactBookDao = new ContactBookDao();
        List<ContactBook> contactBookList = contactBookDao.findByGuardianId(guardianId);

        // 生徒情報を取得するためのDAO
        StudentDao studentDao = new StudentDao();

        // 連絡帳リストを処理し、生徒名を追加
        for (ContactBook book : contactBookList) {

            // student_idを使ってt_studentからstudent_nameを取得
            if (studentId != null) {
                Student student = studentDao.findStudentById(studentId);
                book.setStudentName(student != null ? student.getStudentName() : "不明");
            } else {
                book.setStudentName("不明");
            }
        }

        System.out.println(contactBookList);

        // リクエスト属性に連絡帳リストを設定
        req.setAttribute("contactBookList", contactBookList);

        // JSPへフォワード
        req.getRequestDispatcher("contactbook_list.jsp").forward(req, res);
    }
}
