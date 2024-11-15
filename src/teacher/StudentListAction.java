package teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;

public class StudentListAction extends HttpServlet {

    // GETリクエストに対応
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストから学生情報リストを取得
        List<Student> studentList = (List<Student>) request.getAttribute("studentList");
        String errorMessage = (String) request.getAttribute("errorMessage");

        // 学生情報が存在する場合
        if (studentList != null && !studentList.isEmpty()) {
            request.setAttribute("studentList", studentList);
        }

//        // エラーメッセージがあれば、それもセット
//        if (errorMessage != null && !errorMessage.isEmpty()) {
//            request.setAttribute("errorMessage", errorMessage);
//        }

        // student_list.jspへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("student_list.jsp");
        dispatcher.forward(request, response);
    }
}
