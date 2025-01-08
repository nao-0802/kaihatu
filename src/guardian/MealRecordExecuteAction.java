package guardian;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MealRecord;
import bean.StudentRecord;
import dao.GuardianDao;
import dao.MealRecordDao;  // MealRecordDaoをインポート
import tool.Action;

public class MealRecordExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        String student_id = null;  // 生徒ID
        Integer meal_amount = null;  // 食事種別
        MealRecordDao dao = new MealRecordDao();  // MealRecordDaoのインスタンス化

        // セッションから保護者IDを取得
        HttpSession session = req.getSession();
        String guardianId = (String) session.getAttribute("guardian_id");  // セッションから保護者IDを取得


        student_id = req.getParameter("student_id");  // 生徒IDをリクエストから取得
        String mealamountParam = req.getParameter("meal_amount");  // 食事種別をリクエストから取得

        if (guardianId == null || guardianId.isEmpty()) {
            req.setAttribute("errorMessage", "保護者IDが指定されていません。");
            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
            dispatcher.forward(req, res);
            return;
        }

        // 保護者IDから生徒IDを取得
        GuardianDao guardianDao = new GuardianDao();  // ここでGuardianDaoをインスタンス化
        student_id = guardianDao.getStudentIdByGuardianId(guardianId);  // 生徒IDを取得

        if (student_id == null || student_id.isEmpty()) {
            req.setAttribute("errorMessage", "指定された保護者IDに対応する生徒IDが見つかりません。");
            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
            dispatcher.forward(req, res);
            return;
        }

        // 取得した生徒IDをStudentRecordに設定
        StudentRecord studentRecord = new StudentRecord();
        studentRecord.setStudentId(student_id);  // 生徒IDを設定

        // 食事種別の取得
//        String mealamountParam = req.getParameter("meal_amount");

        if (mealamountParam != null && !mealamountParam.isEmpty()) {
            try {
                meal_amount = Integer.parseInt(mealamountParam);  // 睡眠種別を取得
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



        // 現在の日付と時刻を取得
        LocalDate currentDate = LocalDate.now();  // 現在の日付
        LocalTime currentTime = LocalTime.now();  // 現在の時刻（時間、分、秒）

        // LocalDateをSQLのDateに変換
        Date sqlDate = Date.valueOf(currentDate);  // LocalDateからDateに変換

        // 時間部分を設定するためにTimeを使う
        Time sqlTime = Time.valueOf(currentTime);  // LocalTimeをsql.Timeに変換


        // MealRecordオブジェクトの作成
        MealRecord p = new MealRecord();
        // student_idの登録
        p.setStudentId(studentRecord.getStudentId());
        p.setDay(sqlDate);  // 日付をセット
        p.setTime(sqlTime); // 正しい時間を設定
        p.setMeal_Amount(meal_amount);   // 睡眠種別をセット

        // データ保存処理
        boolean isSaved = dao.save(p);  // saveメソッドが成功した場合trueを返すと仮定

        // 保存処理後の処理
        if (isSaved) {
            // 保存成功時の処理
            RequestDispatcher dispatcher = req.getRequestDispatcher("LifeRecord.action");
            dispatcher.forward(req, res);
        } else {
            // 保存失敗時の処理 - エラーメッセージを設定
            req.setAttribute("errorMessage", "データの保存に失敗しました。");
            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");  // エラーページへリダイレクト
            dispatcher.forward(req, res);
        }

    }
}
