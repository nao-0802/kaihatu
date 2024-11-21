package guardian;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SleepRecord;
import dao.SleepRecordDao;
import tool.Action;

public class MealRecordExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言
        String student_id = ""; // 生徒ID
        Integer meal_type = 0; // 食事種別
        SleepRecordDao dao = new SleepRecordDao();

        // リクエストパラメータの取得
        student_id = req.getParameter("student_id");  // 生徒ID
        String mealTypeParam = req.getParameter("meal_type");

        // meal_type が null または空でないかを確認
        if (mealTypeParam != null && !mealTypeParam.isEmpty()) {
            try {
                meal_type = Integer.parseInt(mealTypeParam);
            } catch (NumberFormatException e) {
                meal_type = 0;  // デフォルト値
            }
        } else {
            meal_type = 0;
        }

        // 現在の日付と時間を取得
        LocalDate currentDate = LocalDate.now();  // 現在の日付
        LocalTime currentTime = LocalTime.now();  // 現在の時刻

        // LocalTime を java.sql.Time に変換
        java.sql.Time sqlTime = java.sql.Time.valueOf(currentTime);

        // LocalDate を java.sql.Date に変換
        java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);

        // SleepRecord オブジェクトの作成
        SleepRecord p = new SleepRecord();
        p.setStudentId(student_id);
        p.setDay(sqlDate);  // 日付をセット
        p.setTime(sqlTime);  // 時間をセット
        p.setType(meal_type);

        // データ保存処理
        boolean isSaved = dao.save(p);

        // 保存後の処理
        if (isSaved) {
            // 保存成功時の処理
            RequestDispatcher dispatcher = req.getRequestDispatcher("LifeRecord.action");
            req.setAttribute("student_id", student_id);
            dispatcher.forward(req, res);
        } else {
            // 保存失敗時の処理 - エラーメッセージを設定
            req.setAttribute("errorMessage", "データの保存に失敗しました。");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/guardian/error.jsp");
            dispatcher.forward(req, res);
        }
    }
}
