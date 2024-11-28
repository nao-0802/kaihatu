//package guardian;
//
//import java.sql.Date;  // SQLのDateをインポート
//import java.sql.Time;
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import bean.ExcretionRecord;
//import bean.StudentRecord;
//import dao.ExcretionRecordDao;  // ExcretionRecordDaoをインポート
//import dao.GuardianDao;
//import tool.Action;
//
//public class ExcretionRecordExecuteAction extends Action {
//
//    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//        // ローカル変数の宣言
//        String student_id = null; // 生徒ID
//        Integer type = null; // 排泄種別
//        ExcretionRecordDao dao = new ExcretionRecordDao();  // ExcretionRecordDaoのインスタンスを生成
//
//        // リクエストパラメータの取得とnullチェック
//        student_id = req.getParameter("student_id");  // 生徒ID
//
//
//        String guardianId = req.getParameter("guardian_id"); // 宣言と初期化を追加
//
//
//        if (guardianId == null || guardianId.isEmpty()) {
//            req.setAttribute("errorMessage", "保護者IDが指定されていません。");
//            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
//            dispatcher.forward(req, res);
//            return;
//        }
//
//        // 保護者IDから生徒IDを取得
//        GuardianDao guardianDao = new GuardianDao();  // ここでGuardianDaoをインスタンス化
//        student_id = guardianDao.getStudentIdByGuardianId(guardianId);  // 生徒IDを取得
//
//        if (student_id == null || student_id.isEmpty()) {
//            req.setAttribute("errorMessage", "指定された保護者IDに対応する生徒IDが見つかりません。");
//            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
//            dispatcher.forward(req, res);
//            return;
//        }
//
//        // 取得した生徒IDをStudentRecordに設定
//        StudentRecord studentRecord = new StudentRecord();
//        studentRecord.setStudent_id(student_id);  // 生徒IDを設定
//
//        // 睡眠種別の取得
//        String typeParam = req.getParameter("type");
//
//        if (typeParam != null && !typeParam.isEmpty()) {
//            try {
//                type = Integer.parseInt(typeParam);  // 睡眠種別を取得
//            } catch (NumberFormatException e) {
//                // 数値変換に失敗した場合
//                req.setAttribute("errorMessage", "無効な排泄種別が指定されました。");
//                RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
//                dispatcher.forward(req, res);
//                return;
//            }
//        } else {
//            req.setAttribute("errorMessage", "排泄種別が指定されていません。");
//            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
//            dispatcher.forward(req, res);
//            return;
//        }
//
//
//
//        // 現在の日付と時刻を取得
//        LocalDate currentDate = LocalDate.now();  // 現在の日付
//        LocalTime currentTime = LocalTime.now();  // 現在の時刻（時間、分、秒）
//
//        // LocalDateをSQLのDateに変換
//        Date sqlDate = Date.valueOf(currentDate);  // LocalDateからDateに変換
//
//        // 時間部分を設定するためにTimeを使う
//        Time sqlTime = Time.valueOf(currentTime);  // LocalTimeをsql.Timeに変換
//
//        String excretion_detail = req.getParameter("excretion_detail");
//
//
//
//        // SleepRecordオブジェクトの作成
//        ExcretionRecord p = new ExcretionRecord();
//        // student_idの登録
//        p.setStudentId(studentRecord.getStudent_id());
//        p.setDay(sqlDate);  // 日付をセット
//        p.setTime(sqlTime); // 正しい時間を設定
//        p.setType(type);   // 排泄種別をセット
//        p.setExcretionDetail(excretion_detail);//その他のとこ
//
//        // データ保存処理
//        boolean isSaved = dao.save(p);  // saveメソッドが成功した場合trueを返すと仮定
//
//        // 保存処理後の処理
//        if (isSaved) {
//            // 保存成功時の処理
//            RequestDispatcher dispatcher = req.getRequestDispatcher("LifeRecord.action");
//            dispatcher.forward(req, res);
//        } else {
//            // 保存失敗時の処理 - エラーメッセージを設定
//            req.setAttribute("errorMessage", "データの保存に失敗しました。");
//            RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");  // エラーページへリダイレクト
//            dispatcher.forward(req, res);
//        }
//
//    }
//}


















package guardian;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ExcretionRecord;
import dao.ExcretionRecordDao;
import dao.GuardianDao;
import tool.Action;

public class ExcretionRecordExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String student_id = null;
        Integer type = null;
        ExcretionRecordDao dao = new ExcretionRecordDao();

        // 保護者ID取得とチェック
        String guardianId = req.getParameter("guardian_id");
        if (guardianId == null || guardianId.isEmpty()) {
            req.setAttribute("errorMessage", "保護者IDが指定されていません。");
            forwardToError(req, res);
            return;
        }

        // 生徒ID取得
        GuardianDao guardianDao = new GuardianDao();
        student_id = guardianDao.getStudentIdByGuardianId(guardianId);
        if (student_id == null || student_id.isEmpty()) {
            req.setAttribute("errorMessage", "指定された保護者IDに対応する生徒IDが見つかりません。");
            forwardToError(req, res);
            return;
        }

        // 排泄種別取得
        String typeParam = req.getParameter("type");
        if (typeParam != null && !typeParam.isEmpty()) {
            try {
                type = Integer.parseInt(typeParam);
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "無効な排泄種別が指定されました。");
                forwardToError(req, res);
                return;
            }
        } else {
            req.setAttribute("errorMessage", "排泄種別が指定されていません。");
            forwardToError(req, res);
            return;
        }

        // 日時取得
        Date sqlDate = Date.valueOf(LocalDate.now());
        Time sqlTime = Time.valueOf(LocalTime.now());

        // "その他"の詳細取得
        String excretion_detail = req.getParameter("excretion_detail"); // name="other_detail"から取得

        // ExcretionRecordオブジェクト作成
        ExcretionRecord record = new ExcretionRecord();
        record.setStudentId(student_id);
        record.setDay(sqlDate);
        record.setTime(sqlTime);
        record.setType(type);
        record.setExcretionDetail(excretion_detail); // "その他"をセット

        // データ保存
        boolean isSaved = dao.save(record);
        if (isSaved) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("LifeRecord.action");
            dispatcher.forward(req, res);
        } else {
            req.setAttribute("errorMessage", "データの保存に失敗しました。");
            forwardToError(req, res);
        }
    }

    // エラーページへの遷移処理
    private void forwardToError(HttpServletRequest req, HttpServletResponse res) throws Exception {
        RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
        dispatcher.forward(req, res);
    }
}

