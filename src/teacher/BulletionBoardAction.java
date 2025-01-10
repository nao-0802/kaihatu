package teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class BulletionBoardAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        String teacherId = (String) session.getAttribute("userId");

        if (teacherId == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        req.setAttribute("teacherId", teacherId);
        req.getRequestDispatcher("bulletionboard_create.jsp").forward(req, res);
    }
}
