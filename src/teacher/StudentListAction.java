package teacher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudentRecord;

public class StudentListAction {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StudentRecord> studentList = new ArrayList<>();

        try {
            // データベース接続
            Connection conn = getConnection();

            // 生徒情報を取得するSQL文
            String sql = "SELECT * FROM t_student_record";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // SQLを実行して結果を取得
            ResultSet rs = stmt.executeQuery();

            // 結果をリストに格納
            while (rs.next()) {
                StudentRecord record = new StudentRecord();
                record.setStudentRecordId(rs.getString("student_record_id"));
                record.setName(rs.getString("name"));
                record.setClassId(rs.getString("class_id"));
                record.setGuardianId(rs.getString("guardian_id"));
                record.setBirthdate(rs.getDate("birthdate"));
                record.setAllergy(rs.getString("allergy"));
                record.setFeatures(rs.getString("features"));
                record.setAnnualRecord(rs.getString("annual_record"));

                studentList.add(record);
            }

            // リソースの解放
            rs.close();
            stmt.close();
            conn.close();

            // 生徒一覧をリクエスト属性に設定
            request.setAttribute("studentList", studentList);

            // 生徒一覧画面にフォワード
            request.getRequestDispatcher("student_list.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            // エラーが発生した場合はエラーページにリダイレクト
            response.sendRedirect("error.jsp");
        }
    }
}
