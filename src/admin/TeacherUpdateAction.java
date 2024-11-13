package admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(); // セッションを取得
        Teacher teacher = (Teacher) session.getAttribute("user"); // セッションからログイン中の教師情報を取得

        String teacherId; // 受信した教師ID
        TeacherDao teacherDao = new TeacherDao(); // TeacherDaoのインスタンス
        Teacher thisTeacher; // 教師情報受け取り用

        // リクエストパラメータの取得
        teacherId = req.getParameter("id"); // 教師IDを取得

        // 教師情報の取得
        thisTeacher = teacherDao.get(teacherId, teacher.getSchool()); // 教師情報をデータベースから取得

        // 取得した教師情報をリクエスト属性にセット
        req.setAttribute("id", teacherId);
        req.setAttribute("teacher_name", thisTeacher.getTeacherName());  // 変更: "name"を"teacher_name"に
        req.setAttribute("class_id", thisTeacher.getClassID()); // 変更: "className"を"class_id"に
        req.setAttribute("password", thisTeacher.getPassword()); // パスワード

        // 更新画面へフォワード
        req.getRequestDispatcher("Teacher_update.jsp").forward(req, res);
    }
  }







































//package admin;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import bean.Teacher;
//import dao.TeacherDao;
//import tool.Action;
//
//public class TeacherUpdateAction extends Action {
//
//    @Override
//    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//        HttpSession session = req.getSession(); // セッションを取得
//        Teacher teacher = (Teacher) session.getAttribute("user"); // セッションからログイン中の教師情報を取得
//
//        String teacherId; // 受信した教師ID
//        TeacherDao teacherDao = new TeacherDao(); // TeacherDaoのインスタンス
//        Teacher thisTeacher; // 教師情報受け取り用
//
//        // リクエストパラメータの取得
//        teacherId = req.getParameter("id"); // 教師IDを取得
//
//        // 教師情報の取得
//        thisTeacher = teacherDao.get(teacherId, teacher.getSchool()); // 教師情報をデータベースから取得
//
//        // 取得した教師情報をリクエスト属性にセット
//        req.setAttribute("id", teacherId);
//        req.setAttribute("name", thisTeacher.getName());
//        req.setAttribute("className", thisTeacher.getClassName()); // クラス名
//        req.setAttribute("password", thisTeacher.getPassword()); // パスワード
//
//        // 更新画面へフォワード
//        req.getRequestDispatcher("teacher_update.jsp").forward(req, res);
//    }
//}
