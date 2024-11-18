package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherUpdateExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String teacherId = req.getParameter("teacher_id");
        String teacherName = req.getParameter("teacher_name");
        String password = req.getParameter("password");
        String classId = req.getParameter("class_id");
        String flagStr = req.getParameter("flag");

        if (teacherId == null || teacherId.isEmpty() || teacherName == null || teacherName.isEmpty()) {
            req.setAttribute("error", "教師IDまたは名前が入力されていません。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        int flag = (flagStr != null && !flagStr.isEmpty()) ? Integer.parseInt(flagStr) : 0;

        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherName(teacherName);
        teacher.setPassword(password);
        teacher.setClassId(classId);
        teacher.setFlag(flag);

        TeacherDao tDao = new TeacherDao();
        if (tDao.save(teacher)) {
            req.getRequestDispatcher("teacher_update_done.jsp").forward(req, res);
        } else {
            req.setAttribute("error", "教師情報の更新に失敗しました。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
