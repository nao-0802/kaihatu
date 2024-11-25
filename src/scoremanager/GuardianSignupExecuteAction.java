package scoremanager;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Guardian;
import dao.GuardianDao;

public class GuardianSignupExecuteAction {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {
			PrintWriter out=response.getWriter();

			try{
				String Guardian_id=request.getParameter("guardian_id");
				String Name=request.getParameter("name");
				String Password=request.getParameter("password");

				Guardian p = new Guardian();
				p.setGuardianId(Guardian_id);
				p.setGuardianName(Name);
				p.setPassword(Password);

				GuardianDao dao =	new GuardianDao();
				dao.save(p);
			} catch (Exception e) {
				e.printStackTrace(out);
			}
			request.getRequestDispatcher("/admin/createTrue.jsp")
			.forward(request, response);
		}
}
