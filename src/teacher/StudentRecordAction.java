package teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Guardian;
import bean.Student;
import bean.StudentRecord;
import dao.ClassDao;
import dao.GuardianDao;
import dao.StudentDao;
import dao.StudentRecordDao;
import tool.Action;

public class StudentRecordAction extends Action{

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータから生徒IDを取得
        String studentid = request.getParameter("student_id");
        try {
        // 生徒情報の取得
        StudentDao sdao=new StudentDao();
        Student slist = sdao.get(studentid);
        // 生徒情報をリクエスト属性に設定
        request.setAttribute("slist", slist);

        // 保護者情報の取得
        GuardianDao gdao=new GuardianDao();
        List<Guardian> glist=gdao.getGuardianIdByStudentId(studentid);

        request.setAttribute("glist", glist);


        StudentRecordDao srdao=new StudentRecordDao();
        // 生徒カルテ情報をデータベースから取得
        List<StudentRecord> list=srdao.search(studentid);

        // 生徒カルテ情報をリクエスト属性に設定
        request.setAttribute("list", list);

        ClassDao cdao=new ClassDao();
        List<String> clist = new ArrayList<>();



        for (StudentRecord record : list) {
	        String ClassId = record.getClassId();
	        String student = cdao.getClassNameById(ClassId);
	        clist.add(student);
	    }

        // クラス情報をリクエスト属性に設定
        request.setAttribute("clist", clist);

        System.out.println("glist: " + glist);

        // 生徒カルテ詳細画面にフォワード
        request.getRequestDispatcher("student_record_detail.jsp").forward(request, response);


		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    }
}
