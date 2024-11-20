package guardian;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentRecord;
import util.DatabaseConnection;

public class StudentListExecuteAction {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 保護者IDをセッションから取得
        HttpSession session = request.getSession();
        Integer guardianId = (Integer) session.getAttribute("guardianId"); // 保護者IDをセッションから取得

        if (guardianId == null) {
            // 保護者IDがセッションにない場合はエラーページにリダイレクト
            response.sendRedirect("student_reccord_guardian_error.jsp");
            return;
        }

        String studentRecordId = request.getParameter("student_record_id"); // 選択された生徒のID
        StudentRecord studentRecord = null;

        try {
            // データベース接続
            Connection conn = DatabaseConnection.getConnection();

            // 特定の生徒情報を取得するSQL文（保護者IDに一致する生徒情報を取得）
            String sql = "SELECT * FROM t_student_record WHERE student_record_id = ? AND guardian_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentRecordId); // student_record_id を設定
            stmt.setInt(2, guardianId); // 保護者IDを設定

            // SQLを実行して結果を取得
            ResultSet rs = stmt.executeQuery();

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
            conn.close();

            if (studentRecord != null) {
                // 保護者が閲覧できる生徒カルテが見つかった場合
                request.setAttribute("studentRecord", studentRecord);

                // カルテ詳細画面にフォワード
                request.getRequestDispatcher("student_record_detail.jsp").forward(request, response);
            } else {
                // 生徒が見つからない場合はエラーページにリダイレクト
                response.sendRedirect("student_reccord_guardian_error.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーが発生した場合はエラーページにリダイレクト
            response.sendRedirect("student_reccord_guardian_error.jsp");
        }
    }
}
