package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentRecord;
import dao.StudentRecordDao;

public class StudentRecordUpdateExecuteAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(); // セッションを取得
        // ログインしているユーザー情報が必要であればセッションから取得
        // User user = (User) session.getAttribute("user");

        String studentRecordId = "";  // 生徒カルテID
        String name = "";  // 生徒氏名
        String classId = "";  // クラスID
        String guardianId = "";  // 保護者ID
        String birthdate = "";  // 生年月日
        String allergy = "";  // アレルギー
        String features = "";  // 特徴
        String annualRecord = "";  // 年次記録
        String attendanceId = "";  // 出欠記録ID
        String anualRecordId = "";  // 年次記録ID

        StudentRecord updatedStudentRecord = new StudentRecord();  // 更新する生徒カルテ情報
        StudentRecordDao studentRecordDao = new StudentRecordDao();  // StudentRecordDaoのインスタンス

        // リクエストパラメータの取得
        studentRecordId = req.getParameter("student_record_id");
        name = req.getParameter("name");
        classId = req.getParameter("class_id");
        guardianId = req.getParameter("guardian_id");
        birthdate = req.getParameter("birthdate");
        allergy = req.getParameter("allergy");
        features = req.getParameter("features");
        annualRecord = req.getParameter("annual_record");

        // 生徒カルテ情報の設定
        updatedStudentRecord.setStudentRecordId(studentRecordId);
        updatedStudentRecord.setName(name);
        updatedStudentRecord.setClassId(classId);
        updatedStudentRecord.setGuardianId(Integer.parseInt(guardianId));
        updatedStudentRecord.setBirthdate(birthdate);
        updatedStudentRecord.setAllergy(allergy);
        updatedStudentRecord.setFeatures(features);
        updatedStudentRecord.setAnnualRecord(annualRecord);

        // 生徒カルテ情報の更新処理
        boolean success = studentRecordDao.update(updatedStudentRecord);

        if (success) {
            // 更新成功時
            req.setAttribute("studentRecord", updatedStudentRecord);  // 更新された生徒カルテ情報をリクエストにセット
            req.getRequestDispatcher("student_record_update_done.jsp").forward(req, res);  // 更新完了ページにフォワード
        } else {
            // 更新失敗時
            System.out.println("★更新に失敗しました");
            req.setAttribute("name", name);  // 入力された生徒氏名を再セット
            req.setAttribute("class_id", classId);  // 入力されたクラスIDを再セット
            req.getRequestDispatcher("student_record_error.jsp").forward(req, res);  // エラー表示ページにフォワード
        }
    }
}
