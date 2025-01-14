package guardian;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Attendance; // AttendanceRecord Beanクラス
import dao.AttendanceDao;
import dao.GuardianDao;
import tool.Action;

public class AttendanceCreateExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String student_id = null;
        Integer type = null;
        AttendanceDao dao = new AttendanceDao(); // AttendanceRecordDaoを使用

        // セッションから保護者IDを取得
        HttpSession session = req.getSession();
        String guardianId = (String) session.getAttribute("guardian_id"); // セッションから保護者IDを取得

        // 保護者ID取得とチェック
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

        // 出席状況(type)取得
        String typeParam = req.getParameter("type");
        if (typeParam != null && !typeParam.isEmpty()) {
            try {
                type = Integer.parseInt(typeParam);
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "無効な出席状況が指定されました。");
                forwardToError(req, res);
                return;
            }
        } else {
            req.setAttribute("errorMessage", "出席状況が指定されていません。");
            forwardToError(req, res);
            return;
        }

        // 日付取得
        Date sqlDate = Date.valueOf(LocalDate.now());

        // 時間取得 (notification_time)
        Time notificationTime = Time.valueOf(LocalTime.now());

        // 備考取得
        String notes = req.getParameter("notes"); // 備考を取得 (任意項目)

        // 症状取得 (symptom)
        String symptom = req.getParameter("symptom"); // フォームで選択された症状

        // AttendanceRecordオブジェクト作成
        Attendance record = new Attendance();
        record.setStudentId(student_id);
        record.setDay(sqlDate);
        record.setType(type); // 出席状況をセット
        record.setNotificationTime(notificationTime); // 登校または早退の時間をセット
        record.setSymptom(symptom); // 症状をセット
        record.setNotes(notes); // 備考をセット

        // データ保存
        boolean isSaved = dao.save(record);
        if (isSaved) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("AttendanceCompleat.jsp"); // 保存後のページ
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
