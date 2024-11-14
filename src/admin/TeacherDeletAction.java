package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.TeacherDao;

@WebServlet(urlPatterns={"/kouka/teacher_delete/"})
public class TeacherDeletAction extends HttpServlet {

	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
				PrintWriter out=response.getWriter();
				try{
					String keyword=request.getParameter("keyword");
					TeacherDao dao=new TeacherDao();
					List<Teacher> list=dao.search(keyword);
					for(Teacher p : list){
						out.println(p.getTeacherId());
						out.println(p.getTeacherName());
										}
					} catch(Exception e){
						e.printStackTrace(out);
					}


		request.getRequestDispatcher("/admin/teacher_delete.jsp")
		.forward(request, response);
	}

}
