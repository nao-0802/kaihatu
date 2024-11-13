package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(); // セッションを取得
        Student student = (Student) session.getAttribute("user"); // セッションからログイン中の生徒情報を取得

        String studentId; // 受信した生徒ID
        StudentDao studentDao = new StudentDao(); // StudentDaoのインスタンス
        Student thisStudent; // 生徒情報受け取り用

        // リクエストパラメータの取得
        studentId = req.getParameter("id"); // 生徒IDを取得

        // 生徒情報の取得
        thisStudent = studentDao.get(studentId, student.getSchool()); // 生徒情報をデータベースから取得

        // 取得した生徒情報をリクエスト属性にセット
        req.setAttribute("id", studentId);
        req.setAttribute("student_name", thisStudent.getStudentName());  // 生徒氏名
        req.setAttribute("class_id", thisStudent.getClassID()); // クラスID
        req.setAttribute("password", thisStudent.getPassword()); // パスワード

        // 更新画面へフォワード
        req.getRequestDispatcher("Student_update.jsp").forward(req, res);
    }
}
