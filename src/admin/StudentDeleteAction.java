package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

public class StudentDeleteAction {
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
				PrintWriter out=response.getWriter();
				try{
					String keyword=request.getParameter("keyword");
					StudentDao dao=new StudentDao();
					List<Student> list=dao.search(keyword);
					for(Student p : list){
						out.println(p.getStudentId());
						out.println(p.getStudentName());
										}
					} catch(Exception e){
						e.printStackTrace(out);
					}


		request.getRequestDispatcher("/admin/teacher_delete.jsp")
		.forward(request, response);
	}
}
