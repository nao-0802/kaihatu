package teacher;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ExcretionRecord;
import dao.ExcretionRecordDao;
import tool.Action;

public class ExcretionRecordExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        String student_id = null;  // 生徒ID
        Integer type = null;  // 排泄種別
        String excretion_detail = null; // その他の詳細
        ExcretionRecordDao dao = new ExcretionRecordDao();


        // セッションから保護者IDを取得
        HttpSession session = req.getSession();
        String studentId = (String) session.getAttribute("student_id");  // セッションから保護者IDを取得

        // リクエストパラメータから値を取得
        student_id = req.getParameter("student_id");  // 生徒IDを取得
        String typeParam = req.getParameter("type");  // 排泄種別を取得
        excretion_detail = req.getParameter("excretion_detail"); // その他の詳細を取得

        // 生徒IDが未指定の場合のエラー処理
        if (student_id == null || student_id.isEmpty()) {
            req.setAttribute("errorMessage", "生徒IDが指定されていません。");
            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
            dispatcher.forward(req, res);
            return;
        }

        // 排泄種別が指定されていない場合のエラー処理
        if (typeParam != null && !typeParam.isEmpty()) {
            try {
                type = Integer.parseInt(typeParam);  // 排泄種別を数値に変換
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "無効な排泄種別が指定されました。");
                RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
                dispatcher.forward(req, res);
                return;
            }
        } else {
            req.setAttribute("errorMessage", "排泄種別が指定されていません。");
            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
            dispatcher.forward(req, res);
            return;
        }

        // 現在の日付と時刻を取得
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        // LocalDateをSQLのDateに変換
        Date sqlDate = Date.valueOf(currentDate);

        // 時間部分をSQL Timeに変換
        Time sqlTime = Time.valueOf(currentTime);

        // ExcretionRecordオブジェクトの作成
        ExcretionRecord p = new ExcretionRecord();
        p.setStudentId(student_id);          // 生徒IDをセット
        p.setDay(sqlDate);                   // 日付をセット
        p.setTime(sqlTime);                  // 時刻をセット
        p.setType(type);                     // 排泄種別をセット
        p.setExcretionDetail(excretion_detail); // その他の詳細をセット

        // データ保存処理
        boolean isSaved = dao.save(p);  // 保存処理の実行

        // 保存処理後の処理
        if (isSaved) {
            // 保存成功時の処理
            RequestDispatcher dispatcher = req.getRequestDispatcher("LifeRecord.action");
            dispatcher.forward(req, res);
        } else {
            // 保存失敗時の処理
            req.setAttribute("errorMessage", "データの保存に失敗しました。");
            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
            dispatcher.forward(req, res);
        }
    }
}
