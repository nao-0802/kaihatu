package scoremanager;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.TeacherDao;

public class TeacherSignupExecuteAction{
	public void doGet(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {
			PrintWriter out=response.getWriter();

			try{
				String Teacher_id=request.getParameter("teacher_id");
				String Name=request.getParameter("name");
				String Password=request.getParameter("password");

				Teacher p = new Teacher();
				p.setTeacherId(Teacher_id);
				p.setTeacherName(Name);
				p.setPassword(Password);

				TeacherDao dao =	new TeacherDao();
				dao.save(p);

				//if (line>0) {
//					out.println("追加に成功しました。");
					request.getRequestDispatcher("/admin/createTrue.jsp")
					.forward(request, response);
				//}
				//else {
					//request.getRequestDispatcher("/admin/createFalse.jsp")
					//.forward(request, response);
				//}
			} catch (Exception e) {
				e.printStackTrace(out);
			}
		}
}
