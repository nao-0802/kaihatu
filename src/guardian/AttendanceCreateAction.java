package guardian;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentRecord;
import dao.StudentRecordDao;
import tool.Action;

public class AttendanceCreateAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		//セッションオブジェクトの生成
		HttpSession session = req.getSession();
		//セッションからのデータの取得
		String student_id = (String)session.getAttribute("guardian_id");

		StudentRecordDao dao = new StudentRecordDao();
		List<StudentRecord> list = dao.search(student_id);
		req.setAttribute("list", list);

		//JSPへフォワード 7
		req.getRequestDispatcher("Attendance.jsp").forward(req, res);
	}
}




























//package guardian;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet(urlPatterns={"/guardian/AttendanceCreate"})
//public class AttendanceCreateAction extends HttpServlet {
//
//	public void doGet (
//			HttpServletRequest request, HttpServletResponse response
//		) throws ServletException, IOException {
//		request.getRequestDispatcher("/guardian/Attendance.jsp")
//			.forward(request, response);
//	}
//}
