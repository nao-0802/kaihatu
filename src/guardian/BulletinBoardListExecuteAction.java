package guardian;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BulletinBoard;
import dao.BulletinBoardDao;
import tool.Action;

public class BulletinBoardListExecuteAction extends Action{
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        String postId = req.getParameter("post_id");
        BulletinBoardDao dao = new BulletinBoardDao();

        // post_idに基づいて詳細情報を取得
        BulletinBoard bulletin = dao.getBulletinBoard(postId);

        // 取得したデータをリクエストに設定
        req.setAttribute("bulletin", bulletin);

        // JSPへフォワード
        req.getRequestDispatcher("BulletinBoardDetail.jsp").forward(req, res);
    }
}

