package guardian;

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
import dao.GuardianDao;
import tool.Action;

public class ExcretionRecordExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String student_id = null;
        Integer type = null;
        ExcretionRecordDao dao = new ExcretionRecordDao();

        // セッションから保護者IDを取得
        HttpSession session = req.getSession();
        String guardianId = (String) session.getAttribute("guardian_id");  // セッションから保護者IDを取得

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

