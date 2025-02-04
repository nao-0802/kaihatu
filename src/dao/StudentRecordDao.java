package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Allergy;
import bean.StudentRecord;

public class StudentRecordDao extends Dao {
    // SQLクエリ: 学生記録の情報を取得する基本SQL
    private String baseSql = "SELECT * FROM t_student_record WHERE class_id = ?";

    // ResultSetからStudentRecordリストを生成するメソッド
    private List<StudentRecord> postFilter(ResultSet rSet) throws SQLException {
        List<StudentRecord> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                StudentRecord studentRecord = new StudentRecord();
                studentRecord.setStudentRecordId(rSet.getString("student_record_id"));
                studentRecord.setClassId(rSet.getString("class_Id"));
                studentRecord.setGuardianId(rSet.getString("guardian_id"));
                studentRecord.setBirthdate(rSet.getDate("birthdate"));
                studentRecord.setString(rSet.getString("allergy"));
                studentRecord.setFeatures(rSet.getString("features"));
                list.add(studentRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    public StudentRecord GetStudentRecord(String studentRecordId) throws Exception {
        StudentRecord studentRecord = null;
        String sql = "SELECT r.student_record_id, r.class_id, r.guardian_id, r.birthdate, r.features, "
                   + "r.student_id, s.student_name, g.guardian_name "
                   + "FROM t_student_record r "
                   + "JOIN t_student s ON r.student_id = s.student_id "
                   + "JOIN t_guardian g ON r.guardian_id = g.guardian_id "
                   + "WHERE r.student_record_id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, studentRecordId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    studentRecord = new StudentRecord();
                    studentRecord.setStudentRecordId(rs.getString("student_record_id"));
                    studentRecord.setClassId(rs.getString("class_id"));
                    studentRecord.setGuardianId(rs.getString("guardian_id"));
                    studentRecord.setBirthdate(rs.getDate("birthdate"));
                    studentRecord.setFeatures(rs.getString("features"));
                    studentRecord.setStudentId(rs.getString("student_id"));
                    studentRecord.setStudentName(rs.getString("student_name"));
                    studentRecord.setGuardianName(rs.getString("guardian_name"));
                }
            }
        }
        return studentRecord;
    }

    // アレルギー情報を取得
    public List<Allergy> getAllergiesForStudent(String studentId) throws Exception {
        List<Allergy> allergies = new ArrayList<>();
        String sql = "SELECT am.allergy_id, am.allergy_name, "
                   + "CASE WHEN sa.student_allergy_id IS NOT NULL THEN true ELSE false END AS is_checked "
                   + "FROM t_allergy_master am "
                   + "LEFT JOIN t_student_allergy sa ON am.allergy_id = sa.allergy_id AND sa.student_id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Allergy allergy = new Allergy();
                    allergy.setAllergyId(rs.getInt("allergy_id"));
                    allergy.setAllergyName(rs.getString("allergy_name"));
                    allergy.setChecked(rs.getBoolean("is_checked"));
                    allergies.add(allergy);
                }
            }
        }
        return allergies;
    }

    // 生徒のアレルギー情報を更新
    public void updateStudentAllergies(String studentId, List<Integer> allergyIds) throws Exception {
        String deleteSql = "DELETE FROM t_student_allergy WHERE student_id = ?";
        String insertSql = "INSERT INTO t_student_allergy (allergy_id, student_id) VALUES (?, ?)";

        try (PreparedStatement deletePstmt = getConnection().prepareStatement(deleteSql)) {
            deletePstmt.setString(1, studentId);
            deletePstmt.executeUpdate();
        }

        try (PreparedStatement insertPstmt = getConnection().prepareStatement(insertSql)) {
            for (Integer allergyId : allergyIds) {
                insertPstmt.setInt(1, allergyId);
                insertPstmt.setString(2, studentId);
                insertPstmt.executeUpdate();
            }
        }
    }

    // 生徒レコードの特徴を更新
    public void updateStudentFeatures(String studentRecordId, String features) throws Exception {
        String sql = "UPDATE t_student_record SET features = ? WHERE student_record_id = ?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, features);
            pstmt.setString(2, studentRecordId);
            pstmt.executeUpdate();
        }
    }

    public StudentRecord getStudentRecordByStudentId(String studentId) throws Exception {
        String sql = "SELECT sr.*, g.guardian_name, s.student_name, c.class_name " +
                     "FROM t_student_record sr " +
                     "JOIN t_student s ON sr.student_id = s.student_id " +
                     "JOIN t_guardian g ON sr.guardian_id = g.guardian_id " +
                     "JOIN t_class c ON sr.class_id = c.class_id " +
                     "WHERE sr.student_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                StudentRecord record = new StudentRecord();
                record.setStudentId(rs.getString("student_id"));
                record.setStudentName(rs.getString("student_name"));
                record.setGuardianName(rs.getString("guardian_name"));
                record.setBirthdate(rs.getDate("birthdate"));
                record.setClassName(rs.getString("class_name"));
                record.setFeatures(rs.getString("features"));
                return record;
            }
            return null;
        }
    }

    public List<Allergy> getAllergiesByStudentId(String studentId) throws Exception {
        String sql = "SELECT am.allergy_id, am.allergy_name " +
                     "FROM t_student_allergy sa " +
                     "JOIN t_allergy_master am ON sa.allergy_id = am.allergy_id " +
                     "WHERE sa.student_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            List<Allergy> allergies = new ArrayList<>();
            while (rs.next()) {
                Allergy allergy = new Allergy();
                allergy.setAllergyId(rs.getInt("allergy_id"));
                allergy.setAllergyName(rs.getString("allergy_name"));
                allergies.add(allergy);
            }
            return allergies;
        }
    }

    public List<String> getAllClasses() throws Exception {
        String sql = "SELECT class_id, class_name FROM t_class";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<String> classes = new ArrayList<>();
            while (rs.next()) {
                classes.add(rs.getString("class_name"));
            }
            return classes;
        }
    }

    public void updateStudentRecord(StudentRecord record) throws Exception {
        String sql = "UPDATE t_student_record SET class_id = ?, features = ? WHERE student_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, record.getClassId());
            stmt.setString(2, record.getFeatures());
            stmt.setString(3, record.getStudentId());
            stmt.executeUpdate();
        }
    }

    public void updateAllergies(String studentId, List<Integer> allergyIds) throws Exception {
        String deleteSql = "DELETE FROM t_student_allergy WHERE student_id = ?";
        String insertSql = "INSERT INTO t_student_allergy (student_allergy_id, student_id, allergy_id) VALUES (?, ?, ?)";
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                deleteStmt.setString(1, studentId);
                deleteStmt.executeUpdate();
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                for (int allergyId : allergyIds) {
                    insertStmt.setString(1, generateRandomId(10));
                    insertStmt.setString(2, studentId);
                    insertStmt.setInt(3, allergyId);
                    insertStmt.addBatch();
                }
                insertStmt.executeBatch();
            }
            conn.commit();
        }
    }

    private String generateRandomId(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder id = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            id.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        return id.toString();
    }



    public void createStudentRecord(String studentId, String birthdate, String guardianId, String features) throws Exception {
        String sql = "INSERT INTO t_student_record (student_record_id, class_id, guardian_id, birthdate, features, student_id) " +
                     "SELECT ?, class_id, ?, ?, ?, student_id FROM t_student WHERE student_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // パラメータの設定
            ps.setString(1, generateStudentRecordId()); // student_record_id
            ps.setString(2, guardianId);               // guardian_id
            ps.setString(3, birthdate);                // birthdate
            ps.setString(4, features);                 // features
            ps.setString(5, studentId);                // student_id

            // SQL 実行
            ps.executeUpdate();
        }
    }

 // t_student_record の class_id を更新するメソッド
    public boolean updateClassId(String studentId, String newClassId) throws Exception {
        String sql = "UPDATE t_student_record SET class_id = ? WHERE student_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // パラメータを設定
            statement.setString(1, newClassId); // 新しいクラスID
            statement.setString(2, studentId); // 更新対象の student_record_id

            // 実行
            int affectedRows = statement.executeUpdate();

            // 更新が成功したかを返す
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("t_student_record の class_id 更新に失敗しました: " + e.getMessage());
        }
    }
    public StudentRecord getStudentRecord(String studentId) throws Exception {
        String sql = "SELECT class_id, birthdate, features FROM t_student_record WHERE student_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    StudentRecord record = new StudentRecord();
                    record.setClassId(rs.getString("class_id"));
                    record.setBirthdate(rs.getDate("birthdate"));
                    record.setFeatures(rs.getString("features"));
                    return record;
                }
            }
        }
        return null;
    }

    public void save(StudentRecord record) throws Exception {
        String sql = "INSERT INTO t_student_record (student_record_id, class_id, guardian_id, birthdate, features, student_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, record.getStudentRecordId());
            stmt.setString(2, record.getClassId());
            stmt.setString(3, record.getGuardianId());
            stmt.setDate(4, record.getBirthdate());
            stmt.setString(5, record.getFeatures());
            stmt.setString(6, record.getStudentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private String generateStudentRecordId() {
        // 現在のミリ秒を取得
        String timestamp = String.valueOf(System.currentTimeMillis());

        // 必要な長さに調整（下位10桁を使用）
        String uniquePart = timestamp.substring(timestamp.length() - 8);

        // プレフィックス "SR" を付与して10文字にする
        return "SR" + uniquePart;
    }

    public String findClassIdByGuardianId(String guardianId) throws Exception {
        String classId = null;

        try (Connection connection = getConnection()) {
            String sql = "SELECT class_id FROM t_student_record WHERE guardian_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, guardianId);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        classId = rs.getString("class_id");
                    }
                }
            }
        }
        return classId;
    }

    public String findStudentIdByGuardianId(String guardianId) {
        String studentId = null;
        String sql = "SELECT student_id FROM t_student_record WHERE guardian_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, guardianId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    studentId = rs.getString("student_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // エラーが発生した場合はスタックトレースを表示
        } catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

        return studentId;
    }

    // 指定されたclass_idの学生記録リストを取得するメソッド
    public List<StudentRecord> filter(String classId) throws Exception {
        List<StudentRecord> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, classId);
            rSet = statement.executeQuery();
            list = postFilter(rSet);
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return list;
    }

    // student_idで学生記録リストを取得するメソッド
    public List<StudentRecord> search(String studentId) throws Exception {
        List<StudentRecord> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        String searchSql = "SELECT * FROM t_student_record WHERE student_id = ?"; // student_idの条件を追加

        try {
            statement = connection.prepareStatement(searchSql);
            statement.setString(1, studentId);
            rSet = statement.executeQuery();
            list = postFilter(rSet);
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return list;
    }

    public StudentRecord getByStudentId(String studentId) throws Exception {
        StudentRecord record = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "SELECT * FROM t_student_record WHERE student_id = ?")) {
            ps.setString(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    record = new StudentRecord();
                    record.setStudentRecordId(rs.getString("student_record_id"));
                    record.setStudentId(rs.getString("student_id"));
                    record.setFeatures(rs.getString("features"));

                    // 他のプロパティをセット
                }
            }
        }
        return record;
    }


    // 学生記録を保存または更新するメソッド
    public boolean tsave(StudentRecord studentRecord) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            statement = connection.prepareStatement(
                "INSERT INTO t_student_record (student_record_id, name, class_id, guardian_id, birthdate, allergy, features, student_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, studentRecord.getStudentRecordId());
            statement.setString(2, studentRecord.getName());
            statement.setString(3, studentRecord.getClassId());
            statement.setString(4, studentRecord.getGuardianId());
            statement.setDate(5, studentRecord.getBirthdate());
            statement.setString(6, studentRecord.getAllergy());
            statement.setString(7, studentRecord.getFeatures());
            statement.setString(8, studentRecord.getStudentId());

            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return count > 0;
    }

    // 指定されたstudent_record_idの学生記録を削除するメソッド
    public boolean delete(String studentRecordId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM t_student_record WHERE student_record_id = ?");
            statement.setString(1, studentRecordId);
            statement.executeUpdate();
            result = true;
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return result;
    }

    // 指定された student_record_id で学生記録を取得するメソッド
    public StudentRecord getRecordByStudentRecordId(String studentRecordId) throws Exception {
        StudentRecord studentRecord = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        String query = "SELECT * FROM t_student_record WHERE student_record_id = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, studentRecordId);
            rSet = statement.executeQuery();

            if (rSet.next()) {
                studentRecord = new StudentRecord();  // ここで新しくオブジェクトを作成する
                studentRecord.setStudentRecordId(rSet.getString("student_record_id"));
                studentRecord.setName(rSet.getString("name"));
                studentRecord.setClassId(rSet.getString("classId"));
                studentRecord.setGuardianId(rSet.getString("guardian_id"));
                studentRecord.setBirthdate(rSet.getDate("birthdate"));
                studentRecord.setString(rSet.getString("allergy"));
                studentRecord.setFeatures(rSet.getString("features"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rSet != null) {
                try {
                    rSet.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return studentRecord;
    }

 // allergyとfeaturesを更新するメソッド
    public boolean updateAllergyAndFeatures(String studentRecordId, String allergy, String features) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        String updateSql = "UPDATE t_student_record SET allergy = ?, features = ? WHERE student_record_id = ?";

        try {
            statement = connection.prepareStatement(updateSql);
            statement.setString(1, allergy);
            statement.setString(2, features);
            statement.setString(3, studentRecordId);

            int count = statement.executeUpdate();
            result = count > 0; // 更新が成功したかどうかを確認
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return result;
    }

    public StudentRecord findByStudentId(String studentId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StudentRecord studentRecord = null;

        String selectSql = "SELECT * FROM t_student_record WHERE student_id = ?";

        try {
            connection = getConnection(); // BaseDaoのメソッドで接続取得
            statement = connection.prepareStatement(selectSql);
            statement.setString(1, studentId);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                studentRecord = new StudentRecord();
                studentRecord.setStudentId(resultSet.getString("student_id"));
                studentRecord.setGuardianId(resultSet.getString("guardian_id"));
                // 必要なら他のカラムも設定
            }
        } catch (Exception e) {
            throw e;
        } finally {
        	if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return studentRecord;
    }


 // guardian_id を用いて t_student_record を検索し、対応する student_id を取得するメソッド
    public List<String> getStudentIdsByGuardianId(String guardianId) throws Exception {
        List<String> studentIds = new ArrayList<>();

        // データベース接続
        Connection connection = getConnection();
        String sql = "SELECT student_id FROM t_student_record WHERE guardian_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, guardianId);

        // クエリ実行
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            // student_id をリストに追加
            studentIds.add(resultSet.getString("student_id"));
        }

        // リソース解放
        resultSet.close();
        statement.close();
        connection.close();

        return studentIds;
    }

    public String getGuardianNameById(String guardianId) throws Exception {
        String guardianName = null;

        // JOINを使用して、t_guardianテーブルからguardian_nameを取得
        String query = "SELECT g.guardian_name " +
                       "FROM t_student_record s " +
                       "JOIN t_guardian g ON s.guardian_id = g.guardian_id " +
                       "WHERE s.guardian_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, guardianId);  // guardianIdをセット
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                guardianName = rs.getString("guardian_name");  // guardian_nameを取得
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリング（例: ロギング）
        }

        return guardianName;
    }

}
