package scoremanager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAdminAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("admin") != null) {
			session.removeAttribute("admin");
			RequestDispatcher dispatcher = request.getRequestDispatcher("logout-out.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("logout-error.jsp");
			dispatcher.forward(request, response);
		}
	}
}
