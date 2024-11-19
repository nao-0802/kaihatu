package teacher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ContactBook;
import dao.ContactBookDao;
import tool.Action;

public class ContactBookAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String guardianId = request.getParameter("GuardianId");
            String selectedDate = request.getParameter("selectedDate");

            ContactBookDao dao = new ContactBookDao();
            List<ContactBook> list = dao.findByGuardianIdAndDate(guardianId, selectedDate);

            request.setAttribute("list", list);
            request.getRequestDispatcher("/student_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
