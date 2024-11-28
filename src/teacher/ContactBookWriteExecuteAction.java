package teacher;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ContactBook;
import dao.ContactBookDao;
import tool.Action;

public class ContactBookWriteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストパラメータの取得
        String teacherId = (String) req.getSession().getAttribute("userId"); // セッションから教職員IDを取得
        String guardianId = req.getParameter("guardianId");
        String contactDetails = req.getParameter("contactDetails");

        // 入力チェック
        if (guardianId == null || guardianId.isEmpty() || contactDetails == null || contactDetails.isEmpty()) {
            req.setAttribute("error", "必要な情報が不足しています。全ての項目を入力してください。");
            req.getRequestDispatcher("/teacher/contactbook_create.jsp").forward(req, res);
            return;
        }

        // 連絡帳データの生成
        ContactBook notebook = new ContactBook();
        notebook.setContactBookId(UUID.randomUUID().toString()); // 一意のIDを生成
        notebook.setTeacherId(teacherId);
        notebook.setGuardianId(guardianId);
        notebook.setContactDetails(contactDetails);

        // データベースに保存
        ContactBookDao notebookDao = new ContactBookDao();
        boolean success = notebookDao.saveNotebook(notebook);

        // 保存結果に応じてフォワード
        if (success) {
            req.setAttribute("message", "連絡帳が正常に保存されました。");
            req.getRequestDispatcher("/teacher/contactbook_success.jsp").forward(req, res);
        } else {
            req.setAttribute("error", "連絡帳の保存中にエラーが発生しました。再試行してください。");
            req.getRequestDispatcher("/teacher/contactbook_create.jsp").forward(req, res);
        }
    }
}
