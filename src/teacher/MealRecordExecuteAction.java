package teacher;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.MealRecord;
import dao.MealRecordDao;
import tool.Action;


public class MealExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";
		String meal_id = ""; //食事記録
		String student_id = ""; //生徒ID
		MealRecordDao mealrecordDao = new MealRecordDao();
		MealRecord mealrecord = null;

		//リクエストパラメータ―の取得 2
		meal_id = req.getParameter("meal_id");// 食事記録ID
		student_id = req.getParameter("student_id");//生徒ID

		//DBからデータ取得 3
		mealrecord = MealRecordDao.login(meal_id, student_id);//データ取得


		//条件で手順4~7の内容が分岐
		if (mealrecord != null) {// 認証成功の場合
			// セッション情報を取得
			HttpSession session = req.getSession(true);
			// 認証済みフラグを立てる
			//mealrecord.setAuthenticated(true);
			// セッションにログイン情報を保存
			session.setAttribute("user", mealrecord);

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
			req.setAttribute("meal_id", meal_id);
			//フォワード
			url = "seikatukiroku-error.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}

	}

}
