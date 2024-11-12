package admin;

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
				String Email=request.getParameter("email");
				String Password=request.getParameter("password");

				Guardian p = new Guardian();
				p.setGuardianId(Guardian_id);
				p.setGuardianName(Name);
				p.setEmail(Email);
				p.setPassword(Password);

				GuardianDao dao =	new GuardianDao();
				int line=dao.insert(p);

				if (line>0) {
//					out.println("追加に成功しました。");
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
