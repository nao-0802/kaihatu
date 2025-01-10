package teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BulletionBoardDao;
import tool.Action;

public class BulletionBoardExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String teacherId = (String) req.getSession().getAttribute("userId");
        String classId = (String) req.getSession().getAttribute("classId");

        System.out.println(title);
        System.out.println(content);
        System.out.println(teacherId);
        System.out.println(classId);

        if (teacherId == null || title == null || content == null || classId == null) {
            res.sendRedirect("bulletionboard_create.jsp");
            return;
        }

        BulletionBoardDao dao = new BulletionBoardDao();
        boolean isSaved = dao.save(title, content, classId);

        if (isSaved) {
            req.getRequestDispatcher("../teacher/bulletionboard_create_done.jsp").forward(req, res);
        } else {
            res.sendRedirect("error.jsp");
        }
    }
}
