package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/admin/guardian_create"})
public class GuardianSignupAction extends HttpServlet {
	public void doGet (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

		request.getRequestDispatcher("/admin/guardian_create.jsp")
			.forward(request, response);
	}
}