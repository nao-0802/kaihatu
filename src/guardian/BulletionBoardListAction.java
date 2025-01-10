package guardian;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BulletionBoard;
import dao.BulletionBoardDao;
import dao.StudentRecordDao;
import dao.TeacherDao;
import tool.Action;

public class BulletionBoardListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        String guardianId = (String) session.getAttribute("guardian_id");
        System.out.println(guardianId);

        if (guardianId == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        // class_idを取得
        StudentRecordDao studentRecordDao = new StudentRecordDao();
        String classId = studentRecordDao.findClassIdByGuardianId(guardianId);
        System.out.println(classId);

        // teacher_idを取得
        TeacherDao teacherDao = new TeacherDao();
        String teacherId = teacherDao.findTeacherIdByClassId(classId);
        System.out.println(teacherId);

        // 全体掲示板データを取得
        BulletionBoardDao bulletionBoardDao = new BulletionBoardDao();
        List<BulletionBoard> bulletionBoardList = bulletionBoardDao.findByTeacherId(teacherId);
        System.out.println(bulletionBoardList);

        // リクエストスコープにセット
        req.setAttribute("bulletionBoardList", bulletionBoardList);

        // JSPへフォワード
        req.getRequestDispatcher("../guardian/bulletionboard_list.jsp").forward(req, res);
    }
}
