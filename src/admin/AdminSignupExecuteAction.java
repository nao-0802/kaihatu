package admin;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Admin;
import dao.AdminDao;

@WebServlet(urlPatterns={"/kouka/create"})
public class AdminSignupExecuteAction {

	public void doGet(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		PrintWriter out=response.getWriter();

		try{
			String Admin_id=request.getParameter("admin_id");
			String Password=request.getParameter("password");

			Admin p = new Admin();
			p.setAdminId(Admin_id);
			p.setPassword(Password);

			AdminDao dao =	new AdminDao();
			int line=dao.insert(p);

			if (line>0) {
//				out.println("追加に成功しました。");
				request.getRequestDispatcher("/admin/createTrue.jsp")
				.forward(request, response);
			}
			else {
				request.getRequestDispatcher("/admin/createFalse.jsp")
				.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

}
