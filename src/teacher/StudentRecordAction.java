package teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.StudentRecord;
import dao.ClassDao;
import dao.StudentDao;
import dao.StudentRecordDao;

public class StudentRecordAction {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータから生徒IDを取得
        String studentid = request.getParameter("student_id");
        try {
        StudentDao sdao=new StudentDao();
        Student slist = sdao.get(studentid);

        StudentRecordDao srdao=new StudentRecordDao();
        // 生徒カルテ情報をデータベースから取得
        List<StudentRecord> list;

        ClassDao cdao=new ClassDao();
        List<String> clist = new ArrayList<>();

			list = srdao.search(studentid);

        if (list != null) {
            // 生徒カルテ情報をリクエスト属性に設定
            request.setAttribute("list", list);

            for (StudentRecord record : list) {
		        String classId = record.getClassId();
		        String sclass = cdao.getClassNameById(classId);
		        if (sclass != null) {
		            clist.add(sclass);
		        }
		    }

            // classnameを取得
            request.setAttribute("clist", clist);

            // 生徒カルテ詳細画面にフォワード
            request.getRequestDispatcher("student_record_detail.jsp").forward(request, response);
        } else {
            // 生徒カルテが見つからなかった場合、エラーメッセージを設定して一覧画面にリダイレクト
            request.setAttribute("errorMessage", "指定された生徒カルテが見つかりません。");
            response.sendRedirect("student_directory.jsp");
        }

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    }
}
