package guardian;

import java.sql.Date;  // SQLのDateをインポート
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ExcretionRecord;
import bean.StudentRecord;
import dao.ExcretionRecordDao;  // ExcretionRecordDaoをインポート
import dao.GuardianDao;
import tool.Action;

public class ExcretionRecordExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言
        String student_id = null; // 生徒ID
        Integer type = null; // 排泄種別
        ExcretionRecordDao dao = new ExcretionRecordDao();  // ExcretionRecordDaoのインスタンスを生成

        // リクエストパラメータの取得とnullチェック
        student_id = req.getParameter("student_id");  // 生徒ID


        String guardianId = req.getParameter("guardian_id"); // 宣言と初期化を追加


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
        studentRecord.setStudent_id(student_id);  // 生徒IDを設定

        // 睡眠種別の取得
        String typeParam = req.getParameter("type");

        if (typeParam != null && !typeParam.isEmpty()) {
            try {
                type = Integer.parseInt(typeParam);  // 睡眠種別を取得
            } catch (NumberFormatException e) {
                // 数値変換に失敗した場合
                req.setAttribute("errorMessage", "無効な種別が指定されました。");
                RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
                dispatcher.forward(req, res);
                return;
            }
        } else {
            req.setAttribute("errorMessage", "睡眠種別が指定されていません。");
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


        // SleepRecordオブジェクトの作成
        ExcretionRecord p = new ExcretionRecord();
        // student_idの登録
        p.setStudentId(studentRecord.getStudent_id());
        p.setDay(sqlDate);  // 日付をセット
        p.setTime(sqlTime); // 正しい時間を設定
        p.setType(type);   // 睡眠種別をセット

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
