package guardian;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ContactBook;
import dao.ContactBookDao;

public class ContactBookWriteAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		String guardian_id = req.getParameter("guardian_id");
		ContactBookDao dao = new ContactBookDao();
		List<ContactBook> list=dao.serch("");
		req.setAttribute("list", list);

		//JSPへフォワード 7
		req.getRequestDispatcher("contactbook.jsp").forward(req, res);
	}

}
