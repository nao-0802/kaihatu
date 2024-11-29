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

public class StudentListExecuteAction {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentRecordId = request.getParameter("student_record_id"); // 選択された生徒のIDを取得
        StudentRecord studentRecord = null;

        try {
            // データベース接続
        	Connection conn = getConnection();

            // 特定の生徒情報を取得するSQL文
            String sql = "SELECT * FROM t_student_record WHERE student_record_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentRecordId); // student_record_id を設定

            // SQLを実行して結果を取得
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                studentRecord = new StudentRecord();
                studentRecord.setStudentRecordId(rs.getString("student_record_id"));
                studentRecord.setName(rs.getString("name"));
                studentRecord.setClassId(rs.getString("class_id"));
                studentRecord.setGuardianId(rs.getString("guardian_id"));
                studentRecord.setBirthdate(rs.getDate("birthdate"));
                studentRecord.setAllergy(rs.getString("allergy"));
                studentRecord.setFeatures(rs.getString("features"));
            }

            // リソースの解放
            rs.close();
            stmt.close();
            conn.close();

            if (studentRecord != null) {
                // 生徒のカルテ情報をリクエスト属性に設定
                request.setAttribute("studentRecord", studentRecord);

                // カルテ詳細画面にフォワード
                request.getRequestDispatcher("student_record_detail.jsp").forward(request, response);
            } else {
                // 生徒が見つからない場合はエラーページにリダイレクト
                response.sendRedirect("error.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーが発生した場合はエラーページにリダイレクト
            response.sendRedirect("error.jsp");
        }
    }
}
