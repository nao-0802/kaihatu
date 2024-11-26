package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.Guardian;
import dao.ClassDao;
import dao.GuardianDao;
import dao.StudentDao;
import tool.Action;

public class GuardianUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	HttpSession session = req.getSession();

        Admin admin = (Admin) session.getAttribute("user");
        GuardianDao gDao = new GuardianDao();
        ClassDao cDao = new ClassDao(); // ClassDaoのインスタンスを作成
        StudentDao sDao = new StudentDao();
     // フォームから送信されたデータを取得
        String guardianId = req.getParameter("guardianId");  // 保護者ID
         String guardianName = req.getParameter("guardianName");  // 氏名
         String password = req.getParameter("password");  // パスワード
         String studentName = req.getParameter("studentName");  // 生徒氏名名
        
         
      // 生徒氏名からStudnet_idを取得
         String studentId = sDao.findClassIdByClassName(studentName);
         if (classId == null) {
             // 該当クラスが見つからない場合のエラーハンドリング
             req.setAttribute("errorMessage", "指定されたクラス名に対応するクラスが見つかりません。");
             req.getRequestDispatcher("/admin/Teacher_update.jsp").forward(req, res);
             return;
         }

        // 入力チェック（クラス番号や必須項目の確認）
        if (guardianId == null || guardianId.isEmpty() ||
            guardianName == null || guardianName.isEmpty() ||
            email == null || email.isEmpty() ||
            password == null || password.isEmpty()) {
            // 必須項目が入力されていない場合
            req.setAttribute("guardian_id", guardianId);
            req.setAttribute("guardian_name", guardianName);
            req.setAttribute("email", email);
            req.setAttribute("password", password);
            req.setAttribute("errorMessage", "すべての必須項目を入力してください。");
            req.getRequestDispatcher("guardian_update.jsp").forward(req, res);
            return;
        }

        // Guardianオブジェクトを作成して値を設定
        Guardian guardian = new Guardian();
        guardian.setGuardianId(guardianId);
        guardian.setGuardianName(guardianName);
        guardian.setEmail(email);
        guardian.setPassword(password);

        // 保護者情報の更新処理
        boolean success = guardianDao.save(guardian);

        if (success) {
            // 更新成功時に完了ページへ
            req.getRequestDispatcher("guardian_update_done.jsp").forward(req, res);
        } else {
            // 更新失敗時にエラーメッセージを設定して再度編集ページへ
            req.setAttribute("guardian_id", guardianId);
            req.setAttribute("guardian_name", guardianName);
            req.setAttribute("email", email);
            req.setAttribute("password", password);
            req.setAttribute("errorMessage", "保護者情報の更新に失敗しました。再試行してください。");
            req.getRequestDispatcher("guardian_update.jsp").forward(req, res);
        }
    }
}
