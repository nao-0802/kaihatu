package teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student; // 生徒情報を保持するクラス
import dao.StudentDao; // 生徒情報を取得するDAOクラス

public class StudentDirectory extends HttpServlet {

    // GETリクエストを処理
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 学生情報をデータベースから取得
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = studentDao.getAllStudents(); // すべての生徒情報を取得

        // 生徒情報があればリクエストに設定
        if (studentList != null && !studentList.isEmpty()) {
            request.setAttribute("studentList", studentList);
        } else {
            request.setAttribute("errorMessage", "生徒情報が見つかりませんでした。");
        }

        // student_list.jsp というJSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("student_list.jsp");
        dispatcher.forward(request, response);
    }
}
