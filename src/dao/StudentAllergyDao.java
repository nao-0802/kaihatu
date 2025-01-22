package dao;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Allergy;

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

    // 生徒IDに基づいてアレルギー情報を取得（Allergyオブジェクトリスト）
    public List<Allergy> getAllergiesByStudentId(String studentId) throws Exception {
        List<Allergy> allergies = new ArrayList<>();
        String sql = "SELECT T_ALLERGY_MASTER.ALLERGY_ID, T_ALLERGY_MASTER.ALLERGY_NAME " +
                     "FROM T_STUDENT_ALLERGY " +
                     "JOIN T_ALLERGY_MASTER ON T_STUDENT_ALLERGY.ALLERGY_ID = T_ALLERGY_MASTER.ALLERGY_ID " +
                     "WHERE T_STUDENT_ALLERGY.STUDENT_ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Allergy allergy = new Allergy();
                    allergy.setAllergyId(rs.getInt("ALLERGY_ID")); // 列名を正確に一致
                    allergy.setAllergyName(rs.getString("ALLERGY_NAME"));
                    allergies.add(allergy);
                }
            }
        }
        return allergies;
    }

    // 新しいアレルギー情報を保存するメソッド
    public void save(String studentId, int allergyId) throws Exception {
        String sql = "INSERT INTO T_STUDENT_ALLERGY (STUDENT_ALLERGY_ID, STUDENT_ID, ALLERGY_ID) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String studentAllergyId = generateRandomId(); // 10桁のランダムなIDを生成
            stmt.setString(1, studentAllergyId);
            stmt.setString(2, studentId);
            stmt.setInt(3, allergyId);
            stmt.executeUpdate();
        }
    }



	public Allergy getAllergy(String studentId) throws Exception {
	    Allergy allergy = null;
	    String sql = "SELECT ALLERGY_ID, ALLERGY_NAME FROM T_ALLERGY_MASTER WHERE STUDENT_ID = ?";

	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, studentId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                allergy = new Allergy();
	                allergy.setAllergyId(rs.getInt("ALLERGY_ID"));
	                allergy.setAllergyName(rs.getString("ALLERGY_NAME"));
	            }
	        }
	    }
	    return allergy;
	}

}
