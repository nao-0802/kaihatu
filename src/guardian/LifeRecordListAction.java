package guardian;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ExcretionRecord;
import bean.MealRecord;
import bean.MedicineRecord;
import bean.SleepRecord;
import dao.ExcretionRecordDao;
import dao.MealRecordDao;
import dao.MedicineRecordDao;
import dao.SleepRecordDao;
import tool.Action;

public class LifeRecordListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	HttpSession session = req.getSession();
    	String studentId = (String) session.getAttribute("student_id");
        System.out.println(studentId);
        if (studentId == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        String date = req.getParameter("date");

        // 日付が未指定の場合は今日の日付を設定
        if (date == null || date.isEmpty()) {
            date = LocalDate.now().toString(); // yyyy-MM-dd 形式
        }

        // DAOインスタンスを作成
        MealRecordDao mealRecordDao = new MealRecordDao();
        SleepRecordDao sleepRecordDao = new SleepRecordDao();
        ExcretionRecordDao excretionRecordDao = new ExcretionRecordDao();
        MedicineRecordDao medicineRecordDao = new MedicineRecordDao();

        // 各テーブルのデータを取得（選択した日付のみ）
        List<MealRecord> mealRecords = mealRecordDao.findByStudentIdAndDate(studentId, date);
        List<SleepRecord> sleepRecords = sleepRecordDao.findByStudentIdAndDate(studentId, date);
        List<ExcretionRecord> excretionRecords = excretionRecordDao.findByStudentIdAndDate(studentId, date);
        List<MedicineRecord> medicineRecords = medicineRecordDao.findByStudentIdAndDate(studentId, date);

        // リクエストスコープにセット
        req.setAttribute("mealRecords", mealRecords);
        req.setAttribute("sleepRecords", sleepRecords);
        req.setAttribute("excretionRecords", excretionRecords);
        req.setAttribute("medicineRecords", medicineRecords);
        req.setAttribute("studentId", studentId);
        req.setAttribute("selectedDate", date);

        // JSPへフォワード
        req.getRequestDispatcher("../guardian/life_record_list.jsp").forward(req, res);
    }
}
