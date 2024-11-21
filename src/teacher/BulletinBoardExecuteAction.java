package teacher;

import bean.BulletinBoard;
import dao.BulletinBoardDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/teacher/BulletinBoard")}
public class BulletinBoardExecuteAction {
	public void doPost(
			HttpServletRequest request,HttpServletResponse response
			)throws ServletException,IOException{
				PrintWriter out=response.getWriter();
				try{
					String title=request.getParameter("title");
					String content=request.getParameter("content");

					BulletinBoard p=new BulletinBoard();
					p.setTitle(title);
					p.setContent(content);

					BulletinBoardDao dao=new BulletinBoardDao();
					int line=dao.insert(p);
				}
				}
	}
