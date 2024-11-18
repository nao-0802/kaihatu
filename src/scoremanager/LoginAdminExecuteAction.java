package scoremanager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.Guardian;
import bean.Teacher;
import dao.AdminDao;
import dao.GuardianDao;
import dao.TeacherDao;
import tool.Action;


public class LoginAdminExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";
		String id = "";
		String password = "";
		AdminDao adminDao = new AdminDao();
		Admin admin = null;
		TeacherDao teacherDao = new TeacherDao();
		GuardianDao guardianDao = new GuardianDao();

		//リクエストパラメータ―の取得 2
		id = req.getParameter("admin_id");// 管理者ID
		password = req.getParameter("password");//パスワード

		//DBからデータ取得 3
		admin = adminDao.login(id, password);//管理者データ取得
		List<Teacher>teacher = teacherDao.getAllTeachers();//教職員データ取得
		List<Guardian>guardian = guardianDao.getAllGuardians();//保護者データ取得

		//ビジネスロジック 4
		//DBへデータ保存 5
		//レスポンス値をセット 6
		//フォワード 7
		//条件で手順4~7の内容が分岐
		if (admin != null) {// 認証成功の場合
			// セッション情報を取得
			HttpSession session = req.getSession(true);
			// 認証済みフラグを立てる
			//admin.setAuthenticated(true);
			// セッションにログイン情報を保存
			session.setAttribute("user", admin);
			req.setAttribute("teacher", teacher);
			req.setAttribute("guardian", guardian);

			req.getRequestDispatcher("login-out.jsp")
				.forward(req, res);
		} else {
			// 認証失敗の場合
			// エラーメッセージをセット
			List<String> errors = new ArrayList<>();
			errors.add("IDまたはパスワードが確認できませんでした");
			req.setAttribute("errors", errors);
			// 入力された教員IDをセット
			req.setAttribute("adminID", id);
			//フォワード
			url = "login-error.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}

//		req.getRequestDispatcher(url).forward(req, res);
	}

}