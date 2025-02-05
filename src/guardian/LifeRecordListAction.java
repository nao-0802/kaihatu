package guardian;

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

        // DAOインスタンスを作成
        MealRecordDao mealRecordDao = new MealRecordDao();
        SleepRecordDao sleepRecordDao = new SleepRecordDao();
        ExcretionRecordDao excretionRecordDao = new ExcretionRecordDao();
        MedicineRecordDao medicineRecordDao = new MedicineRecordDao();

        // 各テーブルのデータを取得
        List<MealRecord> mealRecords = mealRecordDao.findByStudentId(studentId);
        List<SleepRecord> sleepRecords = sleepRecordDao.findByStudentId(studentId);
        List<ExcretionRecord> excretionRecords = excretionRecordDao.findByStudentId(studentId);
        List<MedicineRecord> medicineRecords = medicineRecordDao.findByStudentId(studentId);


        // リクエストスコープにセット
        req.setAttribute("mealRecords", mealRecords);
        req.setAttribute("sleepRecords", sleepRecords);
        req.setAttribute("excretionRecords", excretionRecords);
        req.setAttribute("medicineRecords", medicineRecords);



        // JSPへフォワード
        req.getRequestDispatcher("../guardian/life_record_list.jsp").forward(req, res);
    }
}
