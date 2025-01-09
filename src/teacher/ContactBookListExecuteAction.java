package teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ContactBook;
import dao.ContactBookDao;
import tool.Action;

public class ContactBookListExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String contactBookId = request.getParameter("contactBookId");

        // contactBookIdがnullの場合のエラーハンドリング
        if (contactBookId == null) {
            response.sendRedirect("ContactBookListAction"); // 連絡帳一覧にリダイレクト
            return;
        }

        // 連絡帳情報の取得
        ContactBookDao contactBookDao = new ContactBookDao();
        ContactBook contactBook = contactBookDao.findById(contactBookId);

        // リクエストスコープに連絡帳詳細情報をセット
        request.setAttribute("contactBook", contactBook);

        // 詳細ページにフォワード
        request.getRequestDispatcher("../teacher/contactbook_list_detail.jsp").forward(request, response);
    }
}
