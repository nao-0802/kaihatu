package guardian;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ContactBook;
import dao.ContactBookDao;
import tool.Action;

public class ContactBookListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        String guardianId = (String) session.getAttribute("guardian_id");

        if (guardianId == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        // 保護者IDに基づく連絡帳の取得
        ContactBookDao dao = new ContactBookDao();
        List<ContactBook> contactBookList = dao.getByGuardianId(guardianId);

        // リクエスト属性に連絡帳リストを設定
        req.setAttribute("contactBookList", contactBookList);

        // JSPへフォワード
        req.getRequestDispatcher("contactbook_list.jsp").forward(req, res);
    }
}
