package admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDao;

public class StudentUpdateExecuteAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(); // セッションを取得
        Student student = (Student) session.getAttribute("user"); // ログインしている生徒情報をセッションから取得

        String student_name = "";  // 入力された生徒名 (student_name)
        String password = "";  // 入力されたパスワード
        String class_id = "";  // 入力されたクラスID (class_id)
        Student updatedStudent = new Student();  // 送信用の生徒情報
        StudentDao studentDao = new StudentDao();  // StudentDaoのインスタンス

        // リクエストパラメータの取得
        student_name = req.getParameter("student_name");
        password = req.getParameter("password");
        class_id = req.getParameter("class_id");  // "class"から"class_id"に変更

        // 送信用の生徒情報の作成
        updatedStudent.setStudentName(student_name);  // `student_name`を設定
        updatedStudent.setPassword(password);
        updatedStudent.setClassID(class_id);  // `classID`に変更
        updatedStudent.setSchool(student.getSchool());  // ログイン中の生徒の学校情報を設定

        // 生徒情報の更新処理
        boolean success = studentDao.update(updatedStudent);

        if (success) {
            // 更新成功した場合
            req.setAttribute("student", updatedStudent);  // 更新された生徒情報をリクエストにセット
            req.getRequestDispatcher("Student_update_done.jsp").forward(req, res);  // 更新完了ページにフォワード
        } else {
            // 更新失敗した場合
            System.out.println("★更新に失敗しました");
            req.setAttribute("student_name", student_name);  // 入力された名前を再セット
            req.setAttribute("class_id", class_id);  // 入力されたクラスIDを再セット
            req.getRequestDispatcher("Student_update_error.jsp").forward(req, res);  // エラー表示ページにフォワード
        }
    }
}
