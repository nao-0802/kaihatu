package guardian;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Attendance;
import dao.AttendanceDao;
import dao.GuardianDao;
import tool.Action;

public class AttendanceCreateExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        AttendanceDao dao = new AttendanceDao();

        HttpSession session = req.getSession();
        String guardianId = (String) session.getAttribute("guardian_id");

        if (guardianId == null || guardianId.isEmpty()) {
            setErrorAndForward(req, res, "保護者IDが指定されていません。");
            return;
        }

        GuardianDao guardianDao = new GuardianDao();
        String studentId = guardianDao.getStudentIdByGuardianId(guardianId);
        if (studentId == null || studentId.isEmpty()) {
            setErrorAndForward(req, res, "指定された保護者IDに対応する生徒IDが見つかりません。");
            return;
        }

        String type = req.getParameter("type");
        if (type == null || type.isEmpty()) {
            setErrorAndForward(req, res, "出席状況が指定されていません。");
            return;
        }

        Date sqlDate = Date.valueOf(LocalDate.now());

        Time time = null;
        if ("遅刻".equals(type) || "早退".equals(type)) {
            String timeParam = req.getParameter("time");
            time = parseTime(timeParam);
            if (time == null) {
                setErrorAndForward(req, res, "正しい時間を入力してください。(例: 08:30)");
                return;
            }
        }

        String[] symptomsArray = req.getParameterValues("symptoms");
        String symptom = (symptomsArray != null) ? String.join(",", symptomsArray) : null;

        String notes = req.getParameter("notes");
        String reason = req.getParameter("reason");
        if (reason == null || reason.isEmpty()) {
            setErrorAndForward(req, res, "理由が指定されていません。");
            return;
        }

        Attendance record = new Attendance();
        record.setStudentId(studentId);
        record.setDay(sqlDate);
        record.setType(type);
        record.setTime(time);
        record.setSymptom(symptom);
        record.setNotes(notes);
        record.setReason(reason);

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

    private Time parseTime(String timeParam) {
        if (timeParam != null && !timeParam.isEmpty()) {
            try {
                return Time.valueOf(timeParam + ":00");
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid time format: " + timeParam);
                return null;
            }
        }
        return null;
    }

    private void setErrorAndForward(HttpServletRequest req, HttpServletResponse res, String errorMessage) throws Exception {
        req.setAttribute("errorMessage", errorMessage);
        RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
        dispatcher.forward(req, res);
    }
}
