package scoremanager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.Class;
import dao.ClassDao;

public class TeacherSignupAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("user");

        // 初期値設定
        req.setAttribute("teacher_id", "教職員IDを入力してください");
        req.setAttribute("teacher_name", "氏名を入力してください");
        req.setAttribute("password", "パスワードを入力してください");
        req.setAttribute("class_id", null);
        req.setAttribute("flag", true);

        try {
            // ClassDaoを使用して全クラス情報を取得
            ClassDao classDao = new ClassDao();
            List<Class> classList = classDao.getAllClasses();
            req.setAttribute("class_list", classList); // JSPでクラス選択を表示するために設定
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "クラス情報の取得中にエラーが発生しました。");
        }

        req.getRequestDispatcher("/admin/teacher_create.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("user");

        // 入力されたデータを取得
        String teacherId = req.getParameter("teacher_id");
        String teacherName = req.getParameter("teacher_name");
        String password = req.getParameter("password");
        String classId = req.getParameter("class_id");

        // バリデーション処理
        boolean isValid = true;
        if (teacherId == null || teacherId.isEmpty()) {
            req.setAttribute("error_teacher_id", "教職員IDを入力してください");
            isValid = false;
        }
        if (teacherName == null || teacherName.isEmpty()) {
            req.setAttribute("error_teacher_name", "氏名を入力してください");
            isValid = false;
        }
        if (password == null || password.isEmpty()) {
            req.setAttribute("error_password", "パスワードを入力してください");
            isValid = false;
        }
        if (classId == null || classId.isEmpty()) {
            req.setAttribute("error_class_id", "クラスを選択してください");
            isValid = false;
        }

        if (!isValid) {
            doGet(req, res); // エラーがある場合は再表示
            return;
        }

        // 登録処理（仮実装）
        try {
            // 登録処理を実装する（TeacherDaoなどでDBに保存する処理を追加）
            req.setAttribute("success", "教職員が正常に登録されました！");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "登録中にエラーが発生しました。");
        }

        doGet(req, res); // 登録完了後もフォームを再表示
    }
}
