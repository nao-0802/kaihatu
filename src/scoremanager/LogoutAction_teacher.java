package scoremanager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction_teacher extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("teacher") != null) {
			session.removeAttribute("teacher");
			RequestDispatcher dispatcher = request.getRequestDispatcher("teacher/logout.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("teacher/logout.jsp");
			dispatcher.forward(request, response);
		}
	}
}
