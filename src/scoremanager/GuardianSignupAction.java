package scoremanager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class GuardianSignupAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
    	//デバッグメッセージ
    	System.out.println("mes: GuardianSignupAction Run");


        // 初期値設定
        req.setAttribute("guardianId", "保護者IDを入力してください");
        req.setAttribute("guardianName", "氏名を入力してください");
        req.setAttribute("password", "パスワードを入力してください");


        String guardianId = "";
        String guardianName = "";
        String password = "";



        Map<String, String> errors = new HashMap<>();

        // パラメータ取得
        guardianId = req.getParameter("guardian_id");
        guardianName = req.getParameter("guardian_name");
        password = req.getParameter("password");


        int chk = 0;


        // 入力チェック
        if (guardianId == null || guardianId.isEmpty()) {
            if (chk > 0) {
                errors.put("guardianId", "保護者IDを入力してください");
                req.setAttribute("errors1", errors);
            } else {
                chk += 1;
            }
        } else if (guardianName == null || guardianName.isEmpty()) {
            errors.put("guardianName", "氏名を入力してください");
            req.setAttribute("errors2", errors);
        } else if (password == null || password.isEmpty()) {
            errors.put("password", "パスワードを入力してください");
            req.setAttribute("errors3", errors);
        } else {
            Map<String, String> data = new HashMap<>();
            data.put("guardianId", guardianId);
            data.put("guardianName", guardianName);
            data.put("password", password);


            // 次のアクションへフォワード
            req.getRequestDispatcher("GuardianSignupExecute.action").forward(req, res);
            return;
        }

        // 入力データの設定
        req.setAttribute("guardian_id", guardianId);
        req.setAttribute("guardian_name", guardianName);
        req.setAttribute("password", password);



        req.getRequestDispatcher("/admin/guardian_create.jsp").forward(req, res);
    }
}
