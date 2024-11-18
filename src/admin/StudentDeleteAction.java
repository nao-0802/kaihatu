package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストから削除対象のstudent_idを取得
        String studentId = req.getParameter("student_id");

        StudentDao sDao = new StudentDao();
        Student student = sDao.get(studentId);

        if (student != null) {
            // 学生情報をリクエストに設定
            req.setAttribute("student_id", student.getStudentId());
            req.setAttribute("student_name", student.getStudentName());
            req.setAttribute("class_id", student.getClassId());

            // 削除操作
            boolean deleted = sDao.delete(studentId);
            req.setAttribute("deleted", deleted);

            // 削除結果に応じてメッセージを設定
            if (deleted) {
                req.setAttribute("message", "Student deleted successfully.");
            } else {
                req.setAttribute("message", "Failed to delete Student.");
            }
        } else {
            req.setAttribute("message", "Student not found.");
        }

        // 削除確認ページにフォワード
        req.getRequestDispatcher("student_delete.jsp").forward(req, res);
    }
}
