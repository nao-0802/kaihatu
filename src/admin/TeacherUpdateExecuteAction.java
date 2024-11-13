
package admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;

public class TeacherUpdateExecuteAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(); // セッションを取得
        Teacher teacher = (Teacher) session.getAttribute("user"); // ログインしている教師情報をセッションから取得

        String teacher_name = "";  // 入力された教師名 (teacher_name)
        String password = "";  // 入力されたパスワード
        String class_id = "";  // 入力されたクラスID (class_id)
        Teacher updatedTeacher = new Teacher();  // 送信用の教師情報
        TeacherDao teacherDao = new TeacherDao();  // TeacherDaoのインスタンス

        // リクエストパラメータの取得
        teacher_name = req.getParameter("teacher_name");
        password = req.getParameter("password");
        class_id = req.getParameter("class_id");  // "class"から"class_id"に変更

        // 送信用の教師情報の作成
        updatedTeacher.setName(teacher_name);  // `teacher_name`を設定
        updatedTeacher.setPassword(password);
        updatedTeacher.setClassName(class_id);  // `className`を`class_id`に変更
        updatedTeacher.setSchool(teacher.getSchool());  // ログイン中の教師の学校情報を設定

        // 教師情報の更新処理
        boolean success = teacherDao.update(updatedTeacher);

        if (success) {
            // 更新成功した場合
            req.setAttribute("teacher", updatedTeacher);  // 更新された教師情報をリクエストにセット
            req.getRequestDispatcher("Teacher_Update_done.jsp").forward(req, res);  // 更新完了ページにフォワード
        } else {
            // 更新失敗した場合
            System.out.println("★更新に失敗しました");
            req.setAttribute("teacher_name", teacher_name);  // 入力された名前を再セット
            req.setAttribute("class_id", class_id);  // 入力されたクラスIDを再セット
            req.getRequestDispatcher("Teacher_error.jsp").forward(req, res);  // エラー表示ページにフォワード
        }
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
//public class TeacherUpdateExecuteAction extends Action {
//    @Override
//    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//        HttpSession session = req.getSession(); // セッションを取得
//        Teacher teacher = (Teacher) session.getAttribute("user"); // ログインしている教師情報をセッションから取得
//
//        String teacher_name = "";  // 入力された教師名 (teacher_name)
//        String password = "";  // 入力されたパスワード
//        String class_id = "";  // 入力されたクラスID (class_id)
//        Teacher updatedTeacher = new Teacher();  // 送信用の教師情報
//        TeacherDao teacherDao = new TeacherDao();  // TeacherDaoのインスタンス
//
//        // リクエストパラメータの取得
//        teacher_name = req.getParameter("teacher_name");
//        password = req.getParameter("password");
//        class_id = req.getParameter("class_id");  // "class"から"class_id"に変更
//
//        // 送信用の教師情報の作成
//        updatedTeacher.setName(teacher_name);  // `teacher_name`を設定
//        updatedTeacher.setPassword(password);
//        updatedTeacher.setClassName(class_id);  // `className`を`class_id`に変更
//        updatedTeacher.setSchool(teacher.getSchool());  // ログイン中の教師の学校情報を設定
//
//        // 教師情報の更新処理
//        boolean success = teacherDao.update(updatedTeacher);
//
//        if (success) {
//            // 更新成功した場合
//            req.setAttribute("teacher", updatedTeacher);  // 更新された教師情報をリクエストにセット
//            req.getRequestDispatcher("Teacher_Update_done.jsp").forward(req, res);  // 更新完了ページにフォワード
//        } else {
//            // 更新失敗した場合
//            System.out.println("★更新に失敗しました");
//            req.setAttribute("teacher_name", teacher_name);  // 入力された名前を再セット
//            req.setAttribute("class_id", class_id);  // 入力されたクラスIDを再セット
////            req.getRequestDispatcher("Teacher_update.jsp").forward(req, res);  // 更新ページに再度フォワード??????
//            req.getRequestDispatcher("Teacher_error.jsp").forward(req, res);  // エラー表示ページにフォワード
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////package admin;
////
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import javax.servlet.http.HttpSession;
////
////import bean.Teacher;
////import dao.TeacherDao;
////import tool.Action;
////
////public class TeacherUpdateExecuteAction extends Action {
////    @Override
////    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
////        HttpSession session = req.getSession(); // セッションを取得
////        Teacher teacher = (Teacher) session.getAttribute("user"); // ログインしている教師情報をセッションから取得
////
////        String name = "";  // 入力された教師名
////        String password = "";  // 入力されたパスワード
////        String className = "";  // 入力されたクラス名
////        Teacher updatedTeacher = new Teacher();  // 送信用の教師情報
////        TeacherDao teacherDao = new TeacherDao();  // TeacherDaoのインスタンス
////
////        // リクエストパラメータの取得
////        name = req.getParameter("name");
////        password = req.getParameter("password");
////        className = req.getParameter("class");
////
////        // 送信用の教師情報の作成
////        updatedTeacher.setName(name);
////        updatedTeacher.setPassword(password);
////        updatedTeacher.setClassName(className);
////        updatedTeacher.setSchool(teacher.getSchool());  // ログイン中の教師の学校情報を設定
////
////        // 教師情報の更新処理
////        boolean success = teacherDao.update(updatedTeacher);
////
////        if (success) {
////            // 更新成功した場合
////            req.setAttribute("teacher", updatedTeacher);  // 更新された教師情報をリクエストにセット
////            req.getRequestDispatcher("Teacher_Update_done.jsp").forward(req, res);  // 更新完了ページにフォワード
////        } else {
////            // 更新失敗した場合
////            System.out.println("★更新に失敗しました");
////            req.setAttribute("name", name);  // 入力された名前を再セット
////            req.setAttribute("class", className);  // 入力されたクラス名を再セット
////            req.getRequestDispatcher("Teacher_update.jsp").forward(req, res);  // 更新ページに再度フォワード
////        }
////    }
////}
