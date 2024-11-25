package guardian;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ContactBook;
import dao.ContactBookDao;
import tool.Action;

public class ContactBookListAction extends Action {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 保護者IDをセッションまたはリクエストから取得
        String guardianId = (String) request.getSession().getAttribute("guardian_id");

        // ContactBookDaoのインスタンスを作成
        ContactBookDao dao = new ContactBookDao();

        // 保護者IDに基づいて連絡帳リストを取得
        List<ContactBook> contactBookList = dao.findByGuardianId(guardianId);

        // 連絡帳のリストをリクエスト属性に設定
        request.setAttribute("contactBookList", contactBookList);

        // JSPに転送
        request.getRequestDispatcher("/guardian/contactBookList.jsp").forward(request, response);
    }
}
