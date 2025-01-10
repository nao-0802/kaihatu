package guardian;

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
            res.sendRedirect("../guardian/BulletionBoardList.action");
            return;
        }

        // 掲示板詳細情報を取得
        BulletionBoardDao bulletionBoardDao = new BulletionBoardDao();
        BulletionBoard board = bulletionBoardDao.findById(postId);

        // リクエストスコープにセット
        req.setAttribute("bulletionBoard", board);

        // JSPへフォワード
        req.getRequestDispatcher("../guardian/bulletionboard_list_detail.jsp").forward(req, res);
    }
}
