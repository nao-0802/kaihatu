package teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;

public class StudentRecordUpdateExecuteAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(); // セッションを取得
        Teacher teacher = (Teacher) session.getAttribute("user"); // ログインしている教師情報をセッションから取得

        String studentRecordId = req.getParameter("student_record_id"); // 生徒カルテID
        String name = req.getParameter("name"); // 生徒名
        String classId = req.getParameter("class_id"); // クラスID
        String guardianId = req.getParameter("guardian_id"); // 保護者ID
        String birthdate = req.getParameter("birthdate"); // 生年月日
        String allergy = req.getParameter("allergy"); // アレルギー
        String features = req.getParameter("features"); // 特徴
        String annualRecord = req.getParameter("annual_record"); // 年次記録

        Student updatedStudent = new Student(); // 送信用の生徒情報
        StudentDao studentDao = new StudentDao(); // StudentDaoのインスタンス

        // 更新された生徒カルテ情報の作成
        updatedStudent.setStudentRecordId(studentRecordId); // 生徒カルテID
        updatedStudent.setName(name);
        updatedStudent.setClassID(classId);
        updatedStudent.setGuardianId(guardianId); // 保護者ID
        updatedStudent.setBirthdate(birthdate); // 生年月日
        updatedStudent.setAllergy(allergy); // アレルギー
        updatedStudent.setFeatures(features); // 特徴
        updatedStudent.setAnnualRecord(annualRecord); // 年次記録
        updatedStudent.setSchool(teacher.getSchool()); // ログイン中の教師の学校情報を設定

        // 生徒カルテ情報の更新処理
        boolean success = studentDao.updateStudentRecord(updatedStudent);

        if (success) {
            // 更新成功した場合
            req.setAttribute("student", updatedStudent); // 更新された生徒情報をリクエストにセット
            req.getRequestDispatcher("Student_record_update_done.jsp").forward(req, res); // 更新完了ページにフォワード
        } else {
            // 更新失敗した場合
            System.out.println("★更新に失敗しました");
            req.setAttribute("name", name); // 入力された名前を再セット
            req.setAttribute("class_id", classId); // 入力されたクラスIDを再セット
            req.setAttribute("guardian_id", guardianId); // 入力された保護者IDを再セット
            req.setAttribute("birthdate", birthdate); // 入力された生年月日を再セット
            req.setAttribute("allergy", allergy); // 入力されたアレルギーを再セット
            req.setAttribute("features", features); // 入力された特徴を再セット
            req.setAttribute("annual_record", annualRecord); // 入力された年次記録を再セット
            req.getRequestDispatcher("Student_record_error.jsp").forward(req, res); // エラー表示ページにフォワード
        }
    }
}
