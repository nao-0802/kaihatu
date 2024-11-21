package guardian;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SleepRecord;
import dao.SleepRecordDao;
import tool.Action;

public class ExcretionRecordExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言
        String student_id = ""; // 生徒ID
        Integer excretion_type = 0; // 排泄種別
        SleepRecordDao dao = new SleepRecordDao();

        // リクエストパラメータの取得
        student_id = req.getParameter("student_id"); // 生徒ID
        String excretionTypeParam = req.getParameter("excretion_type");

        // excretion_type の値を取得（null チェック）
        if (excretionTypeParam != null && !excretionTypeParam.isEmpty()) {
            try {
                excretion_type = Integer.parseInt(excretionTypeParam);
            } catch (NumberFormatException e) {
                excretion_type = 0; // デフォルト値
            }
        }

        // 現在の日付と時間を取得
        LocalDate currentDate = LocalDate.now(); // 現在の日付
        LocalTime currentTime = LocalTime.now(); // 現在の時刻

        // LocalDate と LocalTime を java.sql.Date と java.sql.Time に変換
        Date sqlDate = Date.valueOf(currentDate);
        Time sqlTime = Time.valueOf(currentTime);

        // sqlTime を java.util.Date に変換
        java.util.Date utilTime = new java.util.Date(sqlTime.getTime());

        // SleepRecord オブジェクトを作成
        SleepRecord p = new SleepRecord();
        p.setStudentId(student_id);
        p.setDay(sqlDate); // 日付をセット
        p.setTime(utilTime); // 時間をセット
        p.setType(excretion_type);

        // データ保存処理
        boolean isSaved = dao.save(p);

        // 保存結果に基づき処理を分岐
        if (isSaved) {
            // 保存成功時の処理
            RequestDispatcher dispatcher = req.getRequestDispatcher("LifeRecordAction");
            req.setAttribute("student_id", student_id);
            dispatcher.forward(req, res);
        } else {
            // 保存失敗時の処理
            req.setAttribute("errorMessage", "データの保存に失敗しました。");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/guardian/error.jsp");
            dispatcher.forward(req, res);
        }
    }
}
