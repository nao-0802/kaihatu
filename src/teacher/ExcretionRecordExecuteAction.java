package teacher;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ExcretionRecord;
import dao.ExcretionRecordDao;
import tool.Action;

public class ExcretionRecordExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";
		String excretion_id = ""; //排泄記録
		String student_id = ""; //生徒ID
		ExcretionRecordDao excretionrecordDao = new ExcretionRecordDao();
		ExcretionRecord excretionrecord = null;

		//リクエストパラメータ―の取得 2
		excretion_id = req.getParameter("excretion_id");// 排泄記録ID
		student_id = req.getParameter("student_id");//生徒ID

		//DBからデータ取得 3
		excretionrecord = ExcretionRecordDao.login(excretion_id, student_id);//データ取得


		//条件で手順4~7の内容が分岐
		if (excretionrecord != null) {// 認証成功の場合
			// セッション情報を取得
			HttpSession session = req.getSession(true);
			// 認証済みフラグを立てる
			//mealrecord.setAuthenticated(true);
			// セッションにログイン情報を保存
			session.setAttribute("user", excretionrecord);

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
			req.setAttribute("excretion_id", excretion_id);
			//フォワード
			url = "seikatukiroku-error.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}

	}

}

