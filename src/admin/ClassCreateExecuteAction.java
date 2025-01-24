package admin;

import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Class;
import dao.ClassDao;
import tool.Action;

public class ClassCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String className = request.getParameter("className");

        // クラスIDを生成 (ランダムな10桁)
        String classId = UUID.randomUUID().toString().replace("-", "").substring(0, 10);

        // DAO を利用して保存
        Class newClass = new Class();
        newClass.setClassId(classId);
        newClass.setClassName(className);

        ClassDao classDao = new ClassDao();
        classDao.save(newClass);

        // 完了画面にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/class_create_done.jsp");
        dispatcher.forward(request, response);
    }
}
