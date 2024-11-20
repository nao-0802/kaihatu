package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherUpdateAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


    	System.out.println("mes: TeacherUpdateAtion Run");
        HttpSession session = req.getSession();

        // 教師IDを取得
        String teacherId = req.getParameter("teacherId");

        if (teacherId == null || teacherId.isEmpty()) {
            req.setAttribute("error", "教師IDが指定されていません。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        TeacherDao tDao = new TeacherDao();
        Teacher teacher = tDao.get(teacherId);

        if (teacher == null) {
            req.setAttribute("error", "指定された教師が見つかりません。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        req.setAttribute("teacher", teacher);
        req.getRequestDispatcher("/admin/Teacher_update.jsp").forward(req, res);
    }
}
