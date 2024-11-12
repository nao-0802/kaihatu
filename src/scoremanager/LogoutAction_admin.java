package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction_admin extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session=request.getSession();

		if (session.getAttribute("admin")!=null) {
			session.removeAttribute("admin");
			return "logout-out.jsp";
		}
		return "logout-error.jsp";
	}
}
