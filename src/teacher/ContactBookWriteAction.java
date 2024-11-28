package teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Guardian;
import dao.GuardianDao;
import tool.Action;

public class ContactBookWriteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String studentId = req.getParameter("studentId");

        // GuardianDao を使用して studentId から guardianId を取得
        GuardianDao guardianDao = new GuardianDao();
        String guardianId = guardianDao.getGuardianIdbyStudentId(studentId);

        if (guardianId != null) {
            // guardianId を使用して Guardian 情報を取得
            Guardian guardian = guardianDao.get(guardianId);

            // リクエストスコープに必要な情報を設定
            req.setAttribute("studentId", studentId);
            req.setAttribute("guardianId", guardianId);
            req.setAttribute("guardianName", guardian.getGuardianName());
        } else {
            // エラー処理: 保護者情報が見つからない場合
            req.setAttribute("errorMessage", "対応する保護者が見つかりませんでした。");
        }

        req.getRequestDispatcher("/teacher/contactbook_create.jsp").forward(req, res);
    }
}
