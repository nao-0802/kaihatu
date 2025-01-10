package teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BulletionBoard;
import dao.BulletionBoardDao;
import tool.Action;

public class BulletionBoardListExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String postId = req.getParameter("postId");

        if (postId == null) {
            res.sendRedirect("../teacher/BulletionBoardList.action");
            return;
        }

        BulletionBoardDao dao = new BulletionBoardDao();
        BulletionBoard board = dao.findById(postId);

        if (board != null) {
            req.setAttribute("bulletionBoard", board);
            req.getRequestDispatcher("bulletionboard_list_detail.jsp").forward(req, res);
        } else {
            res.sendRedirect("error.jsp");
        }
    }
}
