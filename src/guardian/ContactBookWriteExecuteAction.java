package guardian;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GuardianDao;

public class ContactBookWriteExecuteAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		String guardian_id = req.getParameter("guardian_id");
		GuardianDao guardianDao = new GuardianDao();
		ContactBookDao contactbookDao = new ContactBookDao;

		//フォワード
		req.getRequestDispatcher("contactbook_out.jsp").forward(req, res);
	}

}
