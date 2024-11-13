package teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDao;

public class ClassSelectAction {
	public void doGet (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			try{
				ClassDao dao=new ClassDao();
				List<Class> list=dao.get();

				request.setAttribute("list", list);

				request.getRequestDispatcher("/teacher/class-selection.jsp")
				.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace(out);
			}
	}
}
