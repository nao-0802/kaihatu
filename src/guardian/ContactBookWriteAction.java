package guardian;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ContactBook;
import dao.ContactBookDao;
import tool.Action;

public class ContactBookWriteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {

		// 現在日時を取得
		LocalDateTime nowDate = LocalDateTime.now();

		SimpleDateFormat sdf1= new SimpleDateFormat("yyyy/MM/dd");

		// ローカル変数
		ContactBookDao dao=new ContactBookDao();
		List<ContactBook> list=dao.findByGuardianIdAndDate("sdf1");

		request.setAttribute("list", list);


		request.getRequestDispatcher("/guardian/contactbookwrite.jsp")
			.forward(request, response);
	}
}
