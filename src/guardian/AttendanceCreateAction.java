package guardian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/guardian/AttendanceCreate"})
public class AttendanceCreateAction extends HttpServlet {

	public void doGet (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {
		request.getRequestDispatcher("/guardian/Attendance.jsp")
			.forward(request, response);
	}
}
