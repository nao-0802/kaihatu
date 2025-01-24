package admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class ClassCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        // class_create.jsp にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/class_create.jsp");
        dispatcher.forward(request, response);
    }
}
