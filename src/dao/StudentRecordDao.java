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
    private String baseSql = "SELECT * FROM student_records WHERE class_id = ?";

    // ResultSetからStudentRecordリストを生成するメソッド
    private List<StudentRecord> postFilter(ResultSet rSet) throws SQLException {
        List<StudentRecord> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                StudentRecord studentRecord = new StudentRecord();
                studentRecord.setStudentRecordId(rSet.getString("student_record_id"));
                studentRecord.setName(rSet.getString("name"));
                studentRecord.setClassId(rSet.getString("class_id"));
                studentRecord.setGuardianId(rSet.getString("guardian_id"));
                studentRecord.setBirthdate(rSet.getDate("birthdate"));
                studentRecord.setAllergy(rSet.getString("allergy"));
                studentRecord.setFeatures(rSet.getString("features"));
                studentRecord.setAttendanceId(rSet.getString("attendance_id"));
                studentRecord.setAnualRecordId(rSet.getString("anual_record_id"));
                list.add(studentRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
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
            // PreparedStatementを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // Connectionを閉じる
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

    // 学生記録を保存または更新するメソッド
    public boolean save(StudentRecord studentRecord) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 新しい学生記録の場合は挿入、既存の学生記録の場合は更新
            statement = connection.prepareStatement(
                    "INSERT INTO student_records (student_record_id, name, class_id, guardian_id, birthdate, allergy, features, attendance_id, anual_record_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, studentRecord.getStudentRecordId());
            statement.setString(2, studentRecord.getName());
            statement.setString(3, studentRecord.getClassId());
            statement.setString(4, studentRecord.getGuardianId());
            statement.setDate(5, studentRecord.getBirthdate());
            statement.setString(6, studentRecord.getAllergy());
            statement.setString(7, studentRecord.getFeatures());
            statement.setString(8, studentRecord.getAttendanceId());
            statement.setString(9, studentRecord.getAnualRecordId());

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
            statement = connection.prepareStatement("DELETE FROM student_records WHERE student_record_id = ?");
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
}
