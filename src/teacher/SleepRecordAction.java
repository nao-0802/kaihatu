package teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SleepRecordAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		//JSPへフォワード 7
		req.getRequestDispatcher("seikatukiroku.jsp").forward(req, res);
	}
}

