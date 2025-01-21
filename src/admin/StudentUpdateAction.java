package admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Class;
import bean.Student;
import dao.ClassDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    System.out.println("mes: StudentUpdateAtion Run");


	    // TeacherDao インスタンス作成
	    StudentDao sDao = new StudentDao();

	    // リクエストから teacher_id を取得
	    String student_id = req.getParameter("studentId");
	    if (student_id == null || student_id.isEmpty()) {
	        System.out.println("Error: studentId is null or empty");
	        req.setAttribute("errorMessage", "教職員IDが指定されていません。");
	        req.getRequestDispatcher("/error.jsp").forward(req, res);
	        return;
	    }

	    // 教職員情報取得
	    Student sDaoget = sDao.get(student_id);
	    if (sDaoget == null) {
	        System.out.println("Error: Student not found for ID: " + student_id);
	        req.setAttribute("errorMessage", "指定された生徒IDの情報が見つかりません。");
	        req.getRequestDispatcher("/error.jsp").forward(req, res);
	        return;
	    }

	    ClassDao cDao = new ClassDao();  // ClassDaoインスタンスを作成
	    List<Class> allClasses = cDao.getAllClasses();  // getAllClasses()を使用
        List<String> classNames = new ArrayList<>();
        for (Class clazz : allClasses) {
            classNames.add(clazz.getClassName());  // Classオブジェクトからクラス名をリストに追加
        }

	    // 必要な情報をリクエスト属性に設定
	    req.setAttribute("gid", sDaoget.getStudentId());
	    req.setAttribute("name", sDaoget.getStudentName());
	    req.setAttribute("flag", sDaoget.getFlag());
	    req.setAttribute("gcid", sDaoget.getClassId());
	    req.setAttribute("classNames", classNames);

	    // JSP にフォワード
	    req.getRequestDispatcher("../admin/student_update.jsp").forward(req, res);
	}
}