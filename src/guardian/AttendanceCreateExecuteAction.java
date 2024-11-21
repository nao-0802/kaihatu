package guardian;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Attendance;

@WebServlet(urlPatterns={"/guardian/AttendanceCompleat"})
public class AttendanceCreateExecuteAction extends HttpServlet{

	public void doPost(
			HttpServletRequest request,HttpServletResponse response
			)throws ServletException,IOException{
		PrintWriter out=response.getWriter();

		Date date=new java.sql.Date(0);

		int status=Integer.parseInt(request.getParameter("status"));
		String notes=request.getParameter("notes");


		Attendance p=new Attendance();
		p.setDay(date);
		p.setNotes(notes);
		p.setStatus(status);

		request.getRequestDispatcher("/guardian/AttendanceCompleat.jsp")
	.forward(request, response);
	}

}
