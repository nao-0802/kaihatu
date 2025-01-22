package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AllergyDao extends Dao {
    public List<String> getAllergyNamesByStudentId(String studentId) throws Exception {
        String sql = "SELECT allergy_name " +
                     "FROM t_student_allergy sa " +
                     "JOIN t_allergy_master am ON sa.allergy_id = am.allergy_id " +
                     "WHERE sa.student_id = ?";
        List<String> allergies = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    allergies.add(rs.getString("allergy_name"));
                }
            }
        }
        return allergies;
    }
}
