package teacher;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;

public class StudentDirectoryAction {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // StudentServiceを使用して生徒情報を取得
            StudentService studentService = new StudentService();
            List<Student> studentList = studentService.getAllStudents();

            // リクエストに生徒情報をセット
            request.setAttribute("studentList", studentList);

            // 生徒一覧画面に転送
            RequestDispatcher dispatcher = request.getRequestDispatcher("student_list.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // エラー画面にリダイレクト（例: error.jsp）
            response.sendRedirect("error.jsp");
        }
    }
}
