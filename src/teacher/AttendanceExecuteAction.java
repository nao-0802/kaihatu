package teacher;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AttendanceDao;
import tool.Action;

public class AttendanceExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out=response.getWriter();
		try{
			AttendanceDao dao=new AttendanceDao();
			List<attendance> list=dao.search("");

			request.setAttribute("list", list);

			request.getRequestDispatcher("attendancelist.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}
}

