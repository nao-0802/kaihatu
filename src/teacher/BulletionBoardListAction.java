package teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BulletionBoard;
import dao.BulletionBoardDao;
import tool.Action;

public class BulletionBoardListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        String teacherId = (String) session.getAttribute("userId");

        if (teacherId == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        BulletionBoardDao dao = new BulletionBoardDao();
        List<BulletionBoard> boardList = dao.findByTeacherId(teacherId);

        req.setAttribute("boardList", boardList);
        req.getRequestDispatcher("bulletionboard_list.jsp").forward(req, res);
    }
}
