package teacher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudentRecord;
import util.DatabaseConnection;

@WebServlet("/teacher/StudentRecordUpdateExecuteAction")
public class StudentRecordUpdateExecuteAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // フォームから送信されたデータを取得
            String studentRecordId = request.getParameter("studentRecordId");
            String name = request.getParameter("name");
            String classId = request.getParameter("classId");
            int guardianId = Integer.parseInt(request.getParameter("guardianId"));
            java.sql.Date birthdate = java.sql.Date.valueOf(request.getParameter("birthdate"));
            String allergy = request.getParameter("allergy");
            String features = request.getParameter("features");
            String annualRecord = request.getParameter("annualRecord");

            // StudentRecord オブジェクトにフォームデータを設定
            StudentRecord record = new StudentRecord();
            record.setStudentRecordId(studentRecordId);
            record.setName(name);
            record.setClassId(classId);
            record.setGuardianId(guardianId);
            record.setBirthdate(birthdate);
            record.setAllergy(allergy);
            record.setFeatures(features);
            record.setAnnualRecord(annualRecord);

            // データベース接続
            conn = DatabaseConnection.getConnection();

            // 更新用SQL文
            String sql = "UPDATE t_student_record SET name = ?, class_id = ?, guardian_id = ?, birthdate = ?, allergy = ?, features = ?, annual_record = ? WHERE student_record_id = ?";

            // PreparedStatementを作成し、パラメータを設定
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, record.getName());
            stmt.setString(2, record.getClassId());
            stmt.setInt(3, record.getGuardianId());
            stmt.setDate(4, record.getBirthdate());
            stmt.setString(5, record.getAllergy());
            stmt.setString(6, record.getFeatures());
            stmt.setString(7, record.getAnnualRecord());
            stmt.setString(8, record.getStudentRecordId());

            // SQLを実行して更新
            stmt.executeUpdate();

            // 更新完了後、生徒一覧画面にリダイレクト
            response.sendRedirect("student_list.jsp");

        } catch (SQLException e) {
            // エラー発生時はエラーログを記録し、エラーページに遷移
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } finally {
            // リソースの解放
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
