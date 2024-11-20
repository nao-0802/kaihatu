package guardian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BulletinBoardDao;
import tool.Action;

public class BulletinBoardListAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String post_id = req.getParameter("post_id");
		BulletinBoardDao dao = new BulletinBoardDao();

		//JSPへフォワード 7
		req.getRequestDispatcher("BulletinBoard.jsp").forward(req, res);
	}

}
