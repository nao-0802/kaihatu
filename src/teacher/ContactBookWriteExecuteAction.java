package teacher;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ContactBook;
import dao.ContactBookDao;
import dao.GuardianDao;
import dao.StudentRecordDao;  // 新しく追加
import tool.Action;

public class ContactBookWriteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストパラメータの取得
        String teacherId = (String) req.getSession().getAttribute("userId"); // セッションから教職員IDを取得

        System.out.println(teacherId);
        String guardianId = req.getParameter("guardianId");
        String contactDetails = req.getParameter("contactDetails");

        GuardianDao guardianDao = new GuardianDao();

        String studentId = guardianDao.getStudentIdByGuardianId(guardianId);

	//        // 入力チェック
	//        if (guardianId == null || guardianId.isEmpty() || contactDetails == null || contactDetails.isEmpty()) {
	//            req.setAttribute("error", "必要な情報が不足しています。全ての項目を入力してください。");
	//
	//            // 保護者の名前を取得してリクエストスコープにセット
	//            String guardianName = getGuardianNameById(guardianId);  // 保護者の名前を取得するメソッドを呼び出し
	//            req.setAttribute("guardianName", guardianName);  // 名前をリクエストスコープにセット
	//
	//            // 同じJSPページにフォワード
	//            req.getRequestDispatcher("/teacher/contactbook_create.jsp").forward(req, res);
	//            return;
	//        }

        // 連絡帳データの生成
        ContactBook notebook = new ContactBook();
        notebook.setContactBookId(UUID.randomUUID().toString().replace("-", "").substring(0, 10)); // 一意のIDを生成
        notebook.setTeacherId(teacherId);
        notebook.setGuardianId(guardianId);
        notebook.setContactDetails(contactDetails);

        // データベースに保存
        ContactBookDao notebookDao = new ContactBookDao();
        boolean success = notebookDao.saveNotebook(notebook);

        // 保存結果に応じてフォワード
        if (success) {
            req.setAttribute("message", "連絡帳が正常に保存されました。");
            req.setAttribute("studentId", studentId);
            req.getRequestDispatcher("/teacher/contactbook_create_done.jsp").forward(req, res);
        } else {
            req.setAttribute("error", "連絡帳の保存中にエラーが発生しました。再試行してください。");
            req.getRequestDispatcher("/teacher/contactbook_create.jsp").forward(req, res);
        }
    }

    // 保護者の名前を取得するメソッド
    private String getGuardianNameById(String guardianId) throws Exception {
        String guardianName = null;

        // StudentRecordDao を使用して guardianId から保護者の名前を取得
        StudentRecordDao studentRecordDao = new StudentRecordDao();
        guardianName = studentRecordDao.getGuardianNameById(guardianId);

        return guardianName;
    }
}
