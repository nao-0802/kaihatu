package teacher;

import java.util.List;

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
		String studentId = req.getParameter("studnet_id");

		StudentDao studentdao=new StudentDao();

		List<Student> list=studentdao.search(studentId);

		req.setAttribute("list", list);
		req.getRequestDispatcher("/lifeRecord.jsp").forward(req, res);
	}
}
