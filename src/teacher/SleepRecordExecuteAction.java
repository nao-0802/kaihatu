package teacher;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SleepRecord;
import dao.SleepRecordDao;
import tool.Action;


public class SleepRecordExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";
		String sleep_id = ""; //睡眠記録
		String student_id = ""; //生徒ID
		SleepRecordDao sleeprecordDao = new SleepRecordDao();
		SleepRecord sleeprecord = null;

		//リクエストパラメータ―の取得 2
		sleep_id = req.getParameter("sleep_id");// 睡眠記録ID
		student_id = req.getParameter("student_id");//生徒ID

		//DBからデータ取得 3
		sleeprecord = SleepRecordDao.login(sleep_id, student_id);//データ取得


		//条件で手順4~7の内容が分岐
		if (sleeprecord != null) {// 認証成功の場合
			// セッション情報を取得
			HttpSession session = req.getSession(true);
			// 認証済みフラグを立てる
			//mealrecord.setAuthenticated(true);
			// セッションにログイン情報を保存
			session.setAttribute("user", sleeprecord);

			//リダイレクト
			url = "";
			res.sendRedirect(url);
		} else {
			// 認証失敗の場合
			// エラーメッセージをセット
			List<String> errors = new ArrayList<>();
			errors.add("IDまたはパスワードが確認できませんでした");
			req.setAttribute("errors", errors);
			// 入力されたIDをセット
			req.setAttribute("sleep_id", sleep_id);
			//フォワード
			url = "seikatukiroku-error.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}

	}

}
