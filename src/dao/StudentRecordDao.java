package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                studentRecord.setAllergy(rSet.getString("allergy"));
                studentRecord.setFeatures(rSet.getString("features"));
                list.add(studentRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    public void createStudentRecord(String studentId, String birthdate, String allergy, String guardianId) throws Exception {
        try (Connection con = getConnection()) {
            String sql = "INSERT INTO t_student_record (student_record_id, class_id, guardian_id, birthdate, allergy, features, student_id) " +
                         "SELECT ?, class_id, ?, ?, ?, NULL, student_id FROM t_student WHERE student_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, generateStudentRecordId());
            ps.setString(2, guardianId);
            ps.setString(3, birthdate);
            ps.setString(4, allergy.isEmpty() ? null : allergy);
            ps.setString(5, studentId);
            ps.executeUpdate();
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
                    record.setAllergy(rs.getString("allergy"));
                    // 他のプロパティをセット
                }
            }
        }
        return record;
    }


    // 学生記録を保存または更新するメソッド
    public boolean save(StudentRecord studentRecord) throws Exception {
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
                studentRecord.setAllergy(rSet.getString("allergy"));
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


}