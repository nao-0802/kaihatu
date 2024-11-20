package scoremanager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;

public class TeacherSignupAction extends HttpServlet {
	public void doGet (
			HttpServletRequest req, HttpServletResponse res
		) throws ServletException{
		HttpSession session = req.getSession();
		Admin admin = (Admin)session.getAttribute("user");


		req.getRequestDispatcher("/admin/teacher_create.jsp")
			.forward(req, res);
	}
}
