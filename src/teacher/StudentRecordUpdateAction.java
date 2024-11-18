package teacher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.services.StudentRecordService;

import bean.StudentRecord;

public class StudentRecordUpdateAction {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // リクエストパラメータから生徒カルテIDを取得
            String studentRecordId = request.getParameter("studentRecordId");

            // StudentRecordServiceを使用して生徒カルテ情報を取得
            StudentRecordService studentRecordService = new StudentRecordService();
            StudentRecord studentRecord = studentRecordService.getStudentRecordById(studentRecordId);

            if (studentRecord != null) {
                // 生徒カルテ情報をリクエスト属性に設定
                request.setAttribute("studentRecord", studentRecord);

                // 生徒カルテ更新画面にフォワード
                request.getRequestDispatcher("/teacher/student_record_update.jsp").forward(request, response);
            } else {
                // 生徒カルテが見つからなかった場合、エラーメッセージを設定して一覧画面にリダイレクト
                request.setAttribute("errorMessage", "指定された生徒カルテが見つかりません。");
                response.sendRedirect("student_list.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // エラー時はエラーページにリダイレクト
            response.sendRedirect("error.jsp");
        }
    }
}
