package guardian;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ContactBook;
import dao.ContactBookDao;
import tool.Action;

public class ContactBookWriteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションから教師IDと保護者IDを取得
        String teacherId = req.getParameter("teacherId"); // セッションから教師IDを取得
        String guardianId = (String)req.getSession().getAttribute("guardian_id");
        String contactDetails = req.getParameter("contactDetails");

        System.out.println(teacherId);
        System.out.println(guardianId);
        System.out.println(contactDetails);

        // 入力チェック
        if (guardianId == null || guardianId.isEmpty() || contactDetails == null || contactDetails.isEmpty()) {
            req.setAttribute("error", "必要な情報が不足しています。全ての項目を入力してください。");
            req.getRequestDispatcher("/teacher/contactbook_create.jsp").forward(req, res);
            return;
        }

        // 連絡帳データの生成
        ContactBook contactBook = new ContactBook();
        contactBook.setContactBookId(UUID.randomUUID().toString().replace("-", "").substring(0, 10)); // 一意のIDを生成
        contactBook.setTeacherId(teacherId);
        contactBook.setGuardianId(guardianId);
        contactBook.setContactDetails(contactDetails);

        // ContactBookDaoを使ってデータを保存
        ContactBookDao contactBookDao = new ContactBookDao();
        boolean isSaved = contactBookDao.saveNotebook(contactBook);

        // 保存結果に応じてフォワード
        if (isSaved) {
            req.setAttribute("message", "連絡帳が正常に保存されました。");
            req.getRequestDispatcher("/guardian/contactbook_create_done.jsp").forward(req, res);
        } else {
            req.setAttribute("error", "連絡帳の保存中にエラーが発生しました。再試行してください。");
            req.getRequestDispatcher("/guardian/contactbook_create.jsp").forward(req, res);
        }
    }
}
