package scoremanager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
		HttpSession session = req.getSession(true);
		if(session != null){
			session.invalidate();
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("../scoremanager/logout.jsp");
		dispatcher.forward(req, res);

	}

}
