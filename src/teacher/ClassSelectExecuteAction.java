package teacher;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.StudentRecord;
import dao.StudentDao;
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
			request.setAttribute("list", list);

			StudentDao sdao = new StudentDao();
			List<Student> slist = new ArrayList<>();
//			List<String> slist = new ArrayList<>();

			try {
			    // StudentRecord から student_id を取得し、対応する Student を取得
			    for (StudentRecord record : list) {
			        String studentId = record.getStudent_id();
			        Student student = sdao.get(studentId);
			        if (student != null) {
			            slist.add(student);
			        }
			    }

			    request.setAttribute("slist", slist);
			} catch (Exception e) {
			    e.printStackTrace();
			    // 必要に応じてエラーハンドリングを追加
			}

			request.getRequestDispatcher("student_list.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

}
