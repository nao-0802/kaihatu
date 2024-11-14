package admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Guardian;
import dao.GuardianDao;
@WebServlet(urlPatterns={"/kouka/guardian_delete/"})
public class GuardianDeleteAction {
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
				PrintWriter out=response.getWriter();
				try{
					String keyword=request.getParameter("keyword");
					GuardianDao dao=new GuardianDao();
					List<Guardian> list=dao.search(keyword);
					for(Guardian p : list){
						out.println(p.getGuardianId());
						out.println(p.getGuardianName());
										}
					} catch(Exception e){
						e.printStackTrace(out);
					}


		request.getRequestDispatcher("/admin/teacher_delete.jsp")
		.forward(request, response);
	}


}
