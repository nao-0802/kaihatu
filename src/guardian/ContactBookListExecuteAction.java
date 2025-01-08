package guardian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ContactBook;
import dao.ContactBookDao;
import tool.Action;

public class ContactBookListExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String contactBookId = req.getParameter("contact_book_id");

        if (contactBookId == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Contact Book ID.");
            return;
        }

        // 連絡帳詳細を取得
        ContactBookDao dao = new ContactBookDao();
        ContactBook contactBook = dao.getById(contactBookId);

        if (contactBook == null) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "Contact Book not found.");
            return;
        }

        // リクエスト属性に連絡帳詳細を設定
        req.setAttribute("contactBook", contactBook);

        // JSPへフォワード
        req.getRequestDispatcher("contactbook_list_detail.jsp").forward(req, res);
    }
}
