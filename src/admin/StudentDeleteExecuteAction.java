package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import tool.Action;

public class StudentDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // StudentDaoのインスタンスを作成
        StudentDao sDao = new StudentDao();

        // リクエストから削除対象のstudent_idを取得
        String studentId = req.getParameter("student_id");

        // 削除操作を実行
        boolean isDeleted = sDao.delete(studentId);

        // 削除が成功した場合、完了ページに転送
        if (isDeleted) {
            req.getRequestDispatcher("student_delete_done.jsp").forward(req, res);
        } else {
            // 削除に失敗した場合、エラーページまたはメッセージを設定
            req.setAttribute("message", "Failed to delete Student.");
            req.getRequestDispatcher("student_delete_failed.jsp").forward(req, res);
        }
    }
}
