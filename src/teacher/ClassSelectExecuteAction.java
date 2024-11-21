package teacher;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

public class ClassSelectExecuteAction {
	public void doGet(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {
		PrintWriter out=response.getWriter();
		try {

			String classid=request.getParameter("class_id");

			StudentDao dao=new StudentDao();
			List<Student> list=dao.filter(classid);

			request.setAttribute("list", list);

			request.getRequestDispatcher("student_list.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}
}
