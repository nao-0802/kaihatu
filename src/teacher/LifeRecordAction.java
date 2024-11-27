package teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class LifeRecordAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		@SuppressWarnings("unused")
		String studentId = req.getParameter("student_id");

		StudentDao studentdao=new StudentDao();

		Student list=studentdao.get(studentId);

		req.setAttribute("list", list);
		req.getRequestDispatcher("LifeRecord.jsp")
		.forward(req, res);
	}
}
