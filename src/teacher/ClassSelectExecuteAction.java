package teacher;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDao;

public class ClassSelectExecuteAction {
	public void doGet(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {
		PrintWriter out=response.getWriter();
		try {
			String classid=request.getParameter("class_id");

			ClassDao dao=new ClassDao();
			List<Class> list=dao.search(classid);

			request.setAttribute("list", list);

			request.getRequestDispatcher("student_list.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}
}
