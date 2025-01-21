package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Guardian;
import bean.Student;
import bean.Teacher;
import dao.GuardianDao;
import dao.StudentDao;
import dao.TeacherDao;
import tool.Action;

public class AccountListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {
        // DAOインスタンス作成
        TeacherDao tDao = new TeacherDao();
        GuardianDao gDao = new GuardianDao();
        StudentDao sDao = new StudentDao();

     // フラグの取得 (デフォルトは 0)
        String teacherFlag = req.getParameter("teacherFlag");
        if (teacherFlag == null) {
            teacherFlag = "0"; // デフォルトで「有効」
        }
        List<Teacher> teacherList = tDao.getTeachersByFlag(teacherFlag);

     // フラグの取得 (デフォルトは 0)
        String studentFlag = req.getParameter("studentFlag");
        if (studentFlag == null) {
            studentFlag = "0"; // デフォルトで「有効」
        }
        List<Student> studentList = sDao.getStudentsByFlag(studentFlag);

        // 取得した教師情報をリクエストスコープにセット
        req.setAttribute("teacher", teacherList);

        // 取得した教師情報をリクエストスコープにセット
        req.setAttribute("student", studentList);



        List<Guardian> guardian = gDao.getAllGuardians();



        // リクエストスコープにデータをセット
        req.setAttribute("guardian", guardian);


        // 画面遷移
        try {
            req.getRequestDispatcher("/admin/login-out.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
