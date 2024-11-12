package scoremanager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Guardian;
import dao.GuardianDao;
import tool.Action;


public class LoginExecuteAction_guardian extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";
		String id = "";
		String password = "";
		GuardianDao guardianDao = new GuardianDao();
		Guardian guardian = null;

		//リクエストパラメータ―の取得 2
		id = req.getParameter("guardianID");// 管理者ID
		password = req.getParameter("password");//パスワード

		//DBからデータ取得 3
		guardian = guardianDao.login(id, password);//教員データ取得

		//ビジネスロジック 4
		//DBへデータ保存 5
		//レスポンス値をセット 6
		//フォワード 7
		//条件で手順4~7の内容が分岐
		if (guardian != null) {// 認証成功の場合
			// セッション情報を取得
			HttpSession session = req.getSession(true);
			// 認証済みフラグを立てる
			//guardian.setAuthenticated(true);
			// セッションにログイン情報を保存
			session.setAttribute("user", guardian);

			//リダイレクト
			url = "main/Menu.action";
			res.sendRedirect(url);
		} else {
			// 認証失敗の場合
			// エラーメッセージをセット
			List<String> errors = new ArrayList<>();
			errors.add("IDまたはパスワードが確認できませんでした");
			req.setAttribute("errors", errors);
			// 入力された教員IDをセット
			req.setAttribute("guardianID", id);
			//フォワード
			url = "login-error.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}

//		req.getRequestDispatcher(url).forward(req, res);
	}

}