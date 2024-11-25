package guardian;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ContactBook;
import dao.ContactBookDao;
import tool.Action;

public class ContactBookAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // リクエストからパラメータを取得
            String guardianId = request.getParameter("GuardianId");
            String selectedDate = request.getParameter("selectedDate");

            // パラメータが不足している場合はエラーページに転送
            if (guardianId == null || selectedDate == null) {
                request.setAttribute("error", "必要なパラメータが不足しています");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }

            // DAOを使ってデータを取得
            ContactBookDao dao = new ContactBookDao();
            List<ContactBook> list = dao.findByGuardianIdAndDate(guardianId, selectedDate);

            // 取得したデータをリクエスト属性に設定
            request.setAttribute("list", list);

            // JSPに転送
            request.getRequestDispatcher("/guardian/contactbookwrite.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "データ取得中にエラーが発生しました");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // mainメソッドはサーブレットでは必要ないため削除
    // サーブレットはWebサーバーにデプロイされてから動作しますので、mainメソッドは使いません
}
