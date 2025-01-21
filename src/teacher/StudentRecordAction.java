package teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Allergy;
import bean.Guardian;
import bean.Student;
import bean.StudentRecord;
import dao.ClassDao;
import dao.GuardianDao;
import dao.StudentAllergyDao;
import dao.StudentDao;
import dao.StudentRecordDao;
import tool.Action;

public class StudentRecordAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータから生徒IDを取得
        String studentid = request.getParameter("student_id");

        try {
            // 生徒情報の取得
            StudentDao sdao = new StudentDao();
            Student student = sdao.get(studentid);
            // 生徒情報をリクエスト属性に設定
            request.setAttribute("student", student);

            // 保護者情報の取得
            GuardianDao gdao = new GuardianDao();
            List<Guardian> glist = gdao.getGuardianIdByStudentId(studentid);
            request.setAttribute("glist", glist);

            // 生徒カルテ情報をデータベースから取得
            StudentRecordDao srdao = new StudentRecordDao();
            List<StudentRecord> recordList = srdao.search(studentid);
            request.setAttribute("recordList", recordList);

            // アレルギー情報を取得
            StudentAllergyDao allergyDao = new StudentAllergyDao();
            List<Allergy> allergyList = allergyDao.getAllergiesByStudentId(studentid);
            request.setAttribute("allergyList", allergyList);

            // クラス情報を取得
            ClassDao cdao = new ClassDao();
            List<String> classList = new ArrayList<>();
            for (StudentRecord record : recordList) {
                String classId = record.getClassId();
                String className = cdao.getClassNameById(classId);
                classList.add(className);
            }
            // クラス情報をリクエスト属性に設定
            request.setAttribute("classList", classList);

            // 生徒カルテ詳細画面にフォワード
            request.getRequestDispatcher("student_record_detail.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "データの取得に失敗しました");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
