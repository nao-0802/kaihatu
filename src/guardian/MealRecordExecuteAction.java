package guardian;

import java.sql.Date;  // SQLのDateをインポート
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MealRecord;
import dao.MealRecordDao;
import tool.Action;

public class MealRecordExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言
        String student_id = null; // 生徒ID
        Integer meal_type = null; // 食事種別
        MealRecordDao dao = new MealRecordDao();

        // リクエストパラメータの取得とnullチェック
        student_id = req.getParameter("student_id");  // 生徒ID
        String mealTypeParam = req.getParameter("meal_type");

        if (student_id == null || student_id.isEmpty()) {
            req.setAttribute("errorMessage", "生徒IDが指定されていません。");
            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
            dispatcher.forward(req, res);
            return;
        }

        if (mealTypeParam != null && !mealTypeParam.isEmpty()) {
            try {
                meal_type = Integer.parseInt(mealTypeParam);  // 食事種別を取得
            } catch (NumberFormatException e) {
                // 数値変換に失敗した場合
                req.setAttribute("errorMessage", "無効な食事種別が指定されました。");
                RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
                dispatcher.forward(req, res);
                return;
            }
        } else {
            req.setAttribute("errorMessage", "食事種別が指定されていません。");
            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
            dispatcher.forward(req, res);
            return;
        }

        // 現在の日付と時間を取得
        LocalDate currentDate = LocalDate.now();  // 現在の日付
        LocalTime currentTime = LocalTime.now();  // 現在の時刻

        // LocalDateをSQLのDateに変換
        Date sqlDate = Date.valueOf(currentDate);  // LocalDateからDateに変換
        // LocalTimeをSQLのDateに変換（時刻だけをDateに設定）
        Date sqlTime = Date.valueOf(currentTime.toString());  // LocalTimeをDateに変換

        // MealRecordオブジェクトの作成
        MealRecord p = new MealRecord();
        p.setStudentId(student_id);
        p.setDay(sqlDate);  // 日付をセット
        p.setTime(sqlTime);  // 時間をセット
        p.setType(meal_type);

        // データ保存処理
        boolean isSaved = dao.save(p);  // saveメソッドが成功した場合trueを返すと仮定

        // 保存処理後の処理
        if (isSaved) {
            // 保存成功時の処理
            RequestDispatcher dispatcher = req.getRequestDispatcher("LifeRecord.action");
            req.setAttribute("student_id", student_id);
            dispatcher.forward(req, res);
        } else {
            // 保存失敗時の処理 - エラーメッセージを設定
            req.setAttribute("errorMessage", "データの保存に失敗しました。");
            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");  // エラーページへリダイレクト
            dispatcher.forward(req, res);
        }
    }
}
