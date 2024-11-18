package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherDao;
import tool.Action;

public class TeacherDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // TeacherDaoのインスタンスを作成
        TeacherDao teacherDao = new TeacherDao();

        // リクエストから削除対象のteacher_idを取得
        String teacherId = req.getParameter("teacher_id");

        // 削除操作を実行
        boolean isDeleted = teacherDao.delete(teacherId);

        // 削除が成功した場合、完了ページに転送
        if (isDeleted) {
            req.getRequestDispatcher("teacher_delete_done.jsp").forward(req, res);
        } else {
            // 削除に失敗した場合、エラーページまたはメッセージを設定
            req.setAttribute("message", "Failed to delete Teacher.");
            req.getRequestDispatcher("teacher_delete_failed.jsp").forward(req, res);
        }
    }
}
