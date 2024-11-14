package teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;

public class StudentRecordAction extends HttpServlet {

    // GETリクエストに対応
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // データベースからすべての学生情報を取得
        List<Student> studentList = StudentDAO.getAllStudents();

        // 学生情報が存在すれば、リクエストに設定
        if (studentList != null && !studentList.isEmpty()) {
            request.setAttribute("studentList", studentList);
        } else {
            request.setAttribute("errorMessage", "学生情報が見つかりません。");
        }

        // StudentListActionへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/student_list.jsp");
        dispatcher.forward(request, response);
    }
}
