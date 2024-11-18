package teacher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudentRecord;
import util.DatabaseConnection;

public class StudentRecordAction {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータから生徒IDを取得
        String studentRecordId = request.getParameter("studentRecordId");

        // 生徒カルテ情報をデータベースから取得
        StudentRecord studentRecord = getStudentRecordById(studentRecordId);

        if (studentRecord != null) {
            // 生徒カルテ情報をリクエスト属性に設定
            request.setAttribute("studentRecord", studentRecord);

            // 生徒カルテ詳細画面にフォワード
            request.getRequestDispatcher("student_record_detail.jsp").forward(request, response);
        } else {
            // 生徒カルテが見つからなかった場合、エラーメッセージを設定して一覧画面にリダイレクト
            request.setAttribute("errorMessage", "指定された生徒カルテが見つかりません。");
            response.sendRedirect("student_directory.jsp");
        }
    }

    private StudentRecord getStudentRecordById(String studentRecordId) {
        StudentRecord studentRecord = null;

        // データベース接続
        try (Connection conn = DatabaseConnection.getConnection()) {
            // 生徒情報を取得するSQL文
            String sql = "SELECT * FROM t_student_record WHERE student_record_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentRecordId);

            // SQLを実行して結果を取得
            ResultSet rs = stmt.executeQuery();

            // 結果が存在すれば、StudentRecordオブジェクトに設定
            if (rs.next()) {
                studentRecord = new StudentRecord();
                studentRecord.setStudentRecordId(rs.getString("student_record_id"));
                studentRecord.setName(rs.getString("name"));
                studentRecord.setClassId(rs.getString("class_id"));
                studentRecord.setGuardianId(rs.getInt("guardian_id"));
                studentRecord.setBirthdate(rs.getDate("birthdate"));
                studentRecord.setAllergy(rs.getString("allergy"));
                studentRecord.setFeatures(rs.getString("features"));
                studentRecord.setAnnualRecord(rs.getString("annual_record"));
            }

            // リソースの解放
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentRecord;
    }
}
