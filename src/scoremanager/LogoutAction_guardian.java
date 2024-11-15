package scoremanager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction_guardian extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("guardian") != null) {
			session.removeAttribute("guardian");
			RequestDispatcher dispatcher = request.getRequestDispatcher("logout-out.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("logout-error.jsp");
			dispatcher.forward(request, response);
		}
	}
}
