package teacher;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudentRecord;
import dao.StudentRecordDao;
import tool.Action;

public class ClassSelectExecuteAction extends Action {
	public void execute(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out=response.getWriter();
		try {

			String classid=request.getParameter("class_id");

			StudentRecordDao dao=new StudentRecordDao();
//			List<StudentRecord> list=dao.filter(classid);

//			request.setAttribute("list", list);

			List<StudentRecord> list = dao.filter(classid);
			System.out.println("Student List Size: " + list.size());
			request.setAttribute("list", list);


			request.getRequestDispatcher("student_list.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

}
