package guardian;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

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
        AttendanceDao dao = new AttendanceDao();

        // セッションから保護者IDを取得
        HttpSession session = req.getSession();
        String guardianId = (String) session.getAttribute("guardian_id");

        if (guardianId == null || guardianId.isEmpty()) {
            setErrorAndForward(req, res, "保護者IDが指定されていません。");
            return;
        }

        // 保護者IDから生徒IDを取得
        GuardianDao guardianDao = new GuardianDao();
        String student_id = guardianDao.getStudentIdByGuardianId(guardianId);
        if (student_id == null || student_id.isEmpty()) {
            setErrorAndForward(req, res, "指定された保護者IDに対応する生徒IDが見つかりません。");
            return;
        }

        // 出席状況(type)取得
        Integer type = parseInteger(req.getParameter("type"));
        if (type == null) {
            setErrorAndForward(req, res, "出席状況が指定されていません。または無効な値です。");
            return;
        }

        // 日付取得
        Date sqlDate = Date.valueOf(LocalDate.now());

        // 遅刻時間と早退時間を取得
        Time time = null;
        if (type == 1 || type == 2) { // 遅刻または早退の場合
            time = parseTime(req.getParameter("time"));
        }
        System.out.println(time);

        // 症状取得 (複数選択対応)
        String[] symptomsArray = req.getParameterValues("symptoms");
        String symptom = (symptomsArray != null) ? String.join(",", symptomsArray) : null;

        // 備考取得
        String notes = req.getParameter("notes");

        // 理由(reason)取得 (int型に変更)
        Integer reason = parseInteger(req.getParameter("reason"));

        if (reason == null) {
            setErrorAndForward(req, res, "理由が指定されていません。または無効な値です。");
            return;
        }

        // Attendanceオブジェクト作成
        Attendance record = new Attendance();
        record.setStudentId(student_id);
        record.setDay(sqlDate);
        record.setType(type);
        record.setTime(time); // 早退時間を設定（遅刻または早退の場合のみ）
        record.setSymptom(symptom);
        record.setNotes(notes);
        record.setReason(reason); // 理由をセット

        // データ保存
        try {
            boolean isSaved = dao.save(record);
            if (isSaved) {
                res.sendRedirect("AttendanceCompleat.jsp");
            } else {
                setErrorAndForward(req, res, "データの保存に失敗しました。");
            }
        } catch (Exception e) {
            setErrorAndForward(req, res, "システムエラーが発生しました: " + e.getMessage());
        }
    }

    // 時間のパース処理 (共通化)
    private Time parseTime(String timeParam) {
        if (timeParam != null && !timeParam.isEmpty()) {
            try {
                return Time.valueOf(timeParam + ":00");
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    // 整数のパース処理 (共通化)
    private Integer parseInteger(String intParam) {
        if (intParam != null && !intParam.isEmpty()) {
            try {
                return Integer.parseInt(intParam);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    // エラーメッセージを設定してエラーページに遷移
    private void setErrorAndForward(HttpServletRequest req, HttpServletResponse res, String errorMessage) throws Exception {
        req.setAttribute("errorMessage", errorMessage);
        RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
        dispatcher.forward(req, res);
    }
}
