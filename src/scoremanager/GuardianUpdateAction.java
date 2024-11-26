package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Guardian;
import bean.Student;
import dao.GuardianDao;
import dao.StudentDao;
import tool.Action;

public class GuardianUpdateAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    System.out.println("mes: GuardianUpdateAction Run");

	    HttpSession session = req.getSession();
	    GuardianDao gDao = new GuardianDao();

	    String guardian_id = req.getParameter("guardianId");
	    if (guardian_id == null || guardian_id.isEmpty()) {
	        req.setAttribute("errorMessage", "保護者IDが指定されていません。");
	        System.out.println("Error: guardianId is null or empty");
	        req.getRequestDispatcher("/error.jsp").forward(req, res);
	        return;
	    }

	    Guardian gDaoget = gDao.get(guardian_id);
	    if (gDaoget == null) {
	        req.setAttribute("errorMessage", "指定された保護者IDの情報が見つかりません。");
	        System.out.println("Error: Guardian not found for ID: " + guardian_id);
	        req.getRequestDispatcher("/error.jsp").forward(req, res);
	        return;
	    }

	    StudentDao sDao = new StudentDao();
	    Student student = sDao.get(gDaoget.getStudentId());

	    if (student == null) {
	        req.setAttribute("errorMessage", "紐づく生徒の情報が見つかりません。");
	        System.out.println("Error: Student not found for ID: " + gDaoget.getStudentId());
	        req.getRequestDispatcher("/error.jsp").forward(req, res);
	        return;
	    }

	    req.setAttribute("gid", gDaoget.getGuardianId());
	    req.setAttribute("name", gDaoget.getGuardianName());
	    req.setAttribute("pass", gDaoget.getPassword());
	    req.setAttribute("studentName", student.getStudentName());

	    System.out.println("Forwarding to /admin/Guardian_update.jsp");
	    req.getRequestDispatcher("/admin/Guardian_update.jsp").forward(req, res);
	}

}
