package teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudentRecord;
import dao.StudentRecordDao;
import tool.Action;

public class LifeRecordAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String student_id = req.getParameter("student_id");
		StudentRecordDao dao = new StudentRecordDao();
		List<StudentRecord> list = dao.search(student_id);
		req.setAttribute("list", list);

		//JSPへフォワード 7
		req.getRequestDispatcher("seikatukiroku.jsp").forward(req, res);
	}
}
