package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.Teacher;
import dao.ClassDao;
import dao.TeacherDao;
import tool.Action;

public class TeacherUpdateExecuteAction extends Action {
   @Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
       HttpSession session = req.getSession();

       Admin admin = (Admin) session.getAttribute("user");
       TeacherDao tDao = new TeacherDao();
       ClassDao cDao = new ClassDao(); // ClassDaoのインスタンスを作成


       // フォームから送信されたデータを取得
       String teacherId = req.getParameter("teacherId");  // 教職員ID
        String teacherName = req.getParameter("teacherName");  // 氏名
        String password = req.getParameter("password");  // パスワード
        String className = req.getParameter("className");  // クラス名
        String flagStr = req.getParameter("flag");  // 有効/無効

        int flag = 0;
        if (flagStr != null && flagStr.equals("0")) {
            flag = 0;  // 有効
        } else if (flagStr != null && flagStr.equals("1")) {
            flag = 1;  // 無効
        }

     // クラス名からClass_idを取得
        String classId = cDao.findClassIdByClassName(className);
        if (classId == null) {
            // 該当クラスが見つからない場合のエラーハンドリング
            req.setAttribute("errorMessage", "指定されたクラス名に対応するクラスが見つかりません。");
            req.getRequestDispatcher("/admin/Teacher_update.jsp").forward(req, res);
            return;
        }

        // Teacherオブジェクトにデータをセット
        Teacher updatedTeacher = new Teacher();
        updatedTeacher.setTeacherId(teacherId);
        updatedTeacher.setTeacherName(teacherName);
        updatedTeacher.setPassword(password);
        updatedTeacher.setClassId(classId); // クラスIDをセット
        updatedTeacher.setFlag(flag);


        // 教職員情報の更新
        boolean success = tDao.save(updatedTeacher);  // 更新メソッドを呼び出す

        if (success) {
            // 更新成功時
            req.getRequestDispatcher("/admin/Teacher_update_done.jsp").forward(req, res);
        } else {
            // 更新失敗時（任意でエラーメッセージを表示する場合）
            req.setAttribute("errorMessage", "情報の更新に失敗しました。再度試してください。");
            req.getRequestDispatcher("/admin/Teacher_update.jsp").forward(req, res);
        }
   }
}