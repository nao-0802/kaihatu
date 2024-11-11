package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import dao.AdminDao;
import tool.Action;

public class LoginAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session=request.getSession();

		String adminID=request.getParameter("adminID");
		String password=request.getParameter("password");
		AdminDao dao=new AdminDao();
		Admin admin=dao.search(adminID, password);

		if (admin!=null) {
			session.setAttribute("adminID", admin);
			return "login-out.jsp";
		}

		return "login-error.jsp";
	}
}
