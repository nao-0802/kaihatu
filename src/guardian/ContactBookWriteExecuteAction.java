package guardian;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ContactBook;
import dao.ContactBookDao;
import tool.Action;

public class ContactBookWriteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();

        try {
            // 現在時刻をDate型で取得
            LocalDateTime nowDateTime = LocalDateTime.now();
            Date nowDate = Date.from(nowDateTime.atZone(ZoneId.systemDefault()).toInstant());

            // パラメータ取得
            String TeacherId = request.getParameter("TeacherId");
            String contactBookWrite = request.getParameter("ContactBookWrite");

            // Daoのインスタンス作成
            ContactBookDao dao = new ContactBookDao();

            // ContactBookオブジェクトの作成
            ContactBook p = new ContactBook();
            p.setTeacherId(TeacherId);
            p.setContactDetails(contactBookWrite);
            p.setDay(nowDate);

         // データ保存処理
            boolean isSaved = dao.save(p); // saveメソッドが成功した場合trueを返すと仮定


            // 未定
            if (isSaved) {
                // 保存成功時の処理 - 別のJavaファイルにリダイレクト
                response.sendRedirect("ContactBookCreateSuccessAction");
            } else {
                // 保存失敗時の処理 - 別のJavaファイルにリダイレクト
                response.sendRedirect("ContactBookCreateFailureAction");
            }

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
