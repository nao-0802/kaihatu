package teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ContactBook;
import bean.Student;
import dao.ContactBookDao;
import dao.StudentDao;
import dao.StudentRecordDao;
import tool.Action;

public class ContactBookListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        String teacherId = (String) session.getAttribute("userId");

        // teacherIdがセッションにない場合、ログインページにリダイレクト
        if (teacherId == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        // ContactBookDaoを使用して、teacherIdに基づく連絡帳情報を取得
        ContactBookDao contactBookDao = new ContactBookDao();
        List<ContactBook> contactBookList = contactBookDao.findByTeacherId(teacherId);

        // 生徒情報を取得するためのDAO
        StudentRecordDao studentRecordDao = new StudentRecordDao();
        StudentDao studentDao = new StudentDao();

        // 連絡帳リストを処理し、生徒名を追加
        for (ContactBook book : contactBookList) {
            // guardian_idを使ってt_student_recordからstudent_idを取得
            String studentId = studentRecordDao.findStudentIdByGuardianId(book.getGuardianId());

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
