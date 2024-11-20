package guardian;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SleepRecord;
import dao.SleepRecordDao;

public class SleepRecordExecuteAction {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";
		String student_id = ""; //生徒ID
		Integer sleep_type = 0; //睡眠種別
		SleepRecordDao dao = new SleepRecordDao();
		SleepRecord sleeprecord = null;

		//リクエストパラメータ―の取得 2
		student_id = req.getParameter("student_id");//生徒ID
		sleep_type = Integer.parseInt(req.getParameter("sleep_type"));
		// 現在の日付と時間を取得
        LocalDate currentDate = LocalDate.now(); // 現在の日付
        LocalTime currentTime = LocalTime.now(); // 現在の時刻

		SleepRecord p=new SleepRecord();
		p.setStudentId(student_id);
		p.setDay(currentDate);
		p.setTime(currentTime);
		p.setType(sleep_type);

		// データ保存処理
        boolean isSaved = dao.save(p); // saveメソッドが成功した場合trueを返すと仮定


        // 未定
        if (isSaved) {
            // 保存成功時の処理 - 別のJavaファイルにフォワード
        	RequestDispatcher dispatcher = req.getRequestDispatcher("LefeRecordAction");
        	req.setAttribute("student_id", student_id);
        	dispatcher.forward(req, res);
        } else {
            // 保存失敗時の処理 - 別のJavaファイルにリダイレクト
        	// 未定
            res.sendRedirect("");
        }
	}
}
