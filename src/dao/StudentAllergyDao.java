package dao;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentAllergyDao extends Dao {
    // ランダムな10桁の英数字を生成するメソッド
    private String generateRandomId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }

    public void save(String studentId, int allergyId) throws Exception {
        String sql = "INSERT INTO t_student_allergy (student_allergy_id, student_id, allergy_id) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String studentAllergyId = generateRandomId();  // 10桁のランダムなIDを生成
            stmt.setString(1, studentAllergyId);
            stmt.setString(2, studentId);
            stmt.setInt(3, allergyId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
