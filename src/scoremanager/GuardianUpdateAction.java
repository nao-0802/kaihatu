package scoremanager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.Class;
import bean.Guardian;
import bean.Student;
import dao.ClassDao;
import dao.GuardianDao;
import dao.StudentDao;
import tool.Action;

public class GuardianUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	System.out.println("mes: TeacherUpdateAtion Run");
	    HttpSession session = req.getSession();
	    Admin admin = (Admin) session.getAttribute("user");

	 // GuardianDao インスタンス作成
	    GuardianDao gDao = new GuardianDao();

	 // リクエストから guardian_id を取得
	    String guardian_id = req.getParameter("guardianId");
	    if (guardian_id == null || guardian_id.isEmpty()) {
	        System.out.println("Error: teacherId is null or empty");
	        req.setAttribute("errorMessage", "保護者IDが指定されていません。");
	        req.getRequestDispatcher("/error.jsp").forward(req, res);
	        return;
	    }

	 // 教職員情報取得
	    Guardian gDaoget = gDao.get(guardian_id);
	    if (gDaoget == null) {
	        System.out.println("Error: Teacher not found for ID: " + guardian_id);
	        req.setAttribute("errorMessage", "指定された保護者IDの情報が見つかりません。");
	        req.getRequestDispatcher("/error.jsp").forward(req, res);
	        return;
	    }

	    ClassDao cDao = new ClassDao();  // ClassDaoインスタンスを作成
	    List<Class> allClasses = cDao.getAllClasses();  // getAllClasses()を使用
        List<String> classNames = new ArrayList<>();
        for (Class clazz : allClasses) {
            classNames.add(clazz.getClassName());  // Classオブジェクトからクラス名をリストに追加
        }

        StudentDao sDao = new StudentDao();
        List<Student> allStudents = sDao.getAllStudents();
        List<String> studentNames = new ArrayList<>();
        for (Student clazz : allStudents){
        	studentNames.add(clazz.getStudentName());
        }

     // 必要な情報をリクエスト属性に設定
	    req.setAttribute("gid", gDaoget.getGuardianId());
	    req.setAttribute("name", gDaoget.getGuardianName());
	    req.setAttribute("pass", gDaoget.getPassword());
	    req.setAttribute("gsid", gDaoget.getStudentId());
	    req.setAttribute("studentNames", studentNames);

	    // JSP にフォワード
	    req.getRequestDispatcher("/admin/Guardian_update.jsp").forward(req, res);
	}
}
