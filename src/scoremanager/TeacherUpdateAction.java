package scoremanager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.Class;
import bean.Teacher;
import dao.ClassDao;
import dao.TeacherDao;
import tool.Action;

public class TeacherUpdateAction extends Action {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    System.out.println("mes: TeacherUpdateAtion Run");
	    HttpSession session = req.getSession();
	    Admin admin = (Admin) session.getAttribute("user");

	    // TeacherDao インスタンス作成
	    TeacherDao tDao = new TeacherDao();

	    // リクエストから teacher_id を取得
	    String teacher_id = req.getParameter("teacherId");
	    if (teacher_id == null || teacher_id.isEmpty()) {
	        System.out.println("Error: teacherId is null or empty");
	        req.setAttribute("errorMessage", "教職員IDが指定されていません。");
	        req.getRequestDispatcher("/error.jsp").forward(req, res);
	        return;
	    }

	    // 教職員情報取得
	    Teacher tDaoget = tDao.get(teacher_id);
	    if (tDaoget == null) {
	        System.out.println("Error: Teacher not found for ID: " + teacher_id);
	        req.setAttribute("errorMessage", "指定された教職員IDの情報が見つかりません。");
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
	    req.setAttribute("gid", tDaoget.getTeacherId());
	    req.setAttribute("name", tDaoget.getTeacherName());
	    req.setAttribute("pass", tDaoget.getPassword());
	    req.setAttribute("flag", tDaoget.getFlag());
	    req.setAttribute("gcid", tDaoget.getClassId());
	    req.setAttribute("classNames", classNames);

	    // JSP にフォワード
	    req.getRequestDispatcher("/admin/Teacher_update.jsp").forward(req, res);
	}
}