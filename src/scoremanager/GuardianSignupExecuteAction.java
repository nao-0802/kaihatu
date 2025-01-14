package scoremanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Guardian;
import dao.GuardianDao;
import dao.StudentDao;
import tool.Action;

public class GuardianSignupExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        HttpSession session = req.getSession();
        
        Map<String, String> errors = new HashMap<>();
        GuardianDao guardianDao = new GuardianDao();
        Guardian guardian = new Guardian();

        // 入力データの取得
        String guardianId = req.getParameter("guardian_id");
        String guardianName = req.getParameter("guardian_name");
        String password = req.getParameter("password");
     

        // バリデーションチェック
        boolean isValid = true;
        if (guardianId == null || guardianId.isEmpty()) {
            errors.put("guardian_id", "保護者IDを入力してください");
            isValid = false;
        } else if (guardianDao.get(guardianId) != null) {
            errors.put("guardian_id", "この保護者IDは既に登録されています");
            isValid = false;
        }

        if (guardianName == null || guardianName.isEmpty()) {
            errors.put("guardian_name", "氏名を入力してください");
            isValid = false;
        }

        if (password == null || password.isEmpty()) {
            errors.put("password", "パスワードを入力してください");
            isValid = false;
        }

        

        // エラーがある場合は再表示
        if (!isValid) {
            req.setAttribute("errors", errors);
            req.setAttribute("guardian_id", guardianId);
            req.setAttribute("guardian_name", guardianName);
            
            // 生徒選択肢の再設定
            StudentDao studentDao = new StudentDao();
            List<bean.Student> studentList = studentDao.getAllStudents();
            req.setAttribute("student_list", studentList);

            req.getRequestDispatcher("/admin/guardian_create.jsp").forward(req, res);
            return;
        }

        // 保護者データを設定
        guardian.setGuardianId(guardianId);
        guardian.setGuardianName(guardianName);
        guardian.setPassword(password);
        
        // データベースに保存
        try {
            guardianDao.save(guardian);
            req.getRequestDispatcher("/admin/guardian_create_done.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            errors.put("database", "登録中にエラーが発生しました。再試行してください。");
            req.setAttribute("errors", errors);

            

            req.getRequestDispatcher("/admin/guardian_create.jsp").forward(req, res);
        }
    }
}
