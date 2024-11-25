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
                studentRecord.setName(rSet.getString("name"));
                studentRecord.setClassId(rSet.getString("classId"));
                studentRecord.setGuardianId(rSet.getString("guardian_id"));
                studentRecord.setBirthdate(rSet.getDate("birthdate"));
                studentRecord.setAllergy(rSet.getString("allergy"));
                studentRecord.setFeatures(rSet.getString("features"));
                studentRecord.setMealId(rSet.getString("meal_id"));
                studentRecord.setSleepId(rSet.getString("sleep_id"));
                studentRecord.setExcretionId(rSet.getString("excretion_id"));
                studentRecord.setStudent_id(rSet.getString("student_id"));
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

        String searchSql = "SELECT * FROM t_student_record WHERE guardian_id = ?"; // student_idの条件を追加

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

    // 学生記録を保存または更新するメソッド
    public boolean save(StudentRecord studentRecord) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            statement = connection.prepareStatement(
                "INSERT INTO t_student_record (student_record_id, name, class_id, guardian_id, birthdate, allergy, features, meal_id ,sleep_id, excretion_id, student_id, attendance_id, anual_record_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, studentRecord.getStudentRecordId());
            statement.setString(2, studentRecord.getName());
            statement.setString(3, studentRecord.getClassId());
            statement.setString(4, studentRecord.getGuardianId());
            statement.setDate(5, studentRecord.getBirthdate());
            statement.setString(6, studentRecord.getAllergy());
            statement.setString(7, studentRecord.getFeatures());
            statement.setString(8, studentRecord.getMealId()); // 修正: インデックス 8
            statement.setString(9, studentRecord.getSleepId()); // 修正: インデックス 9
            statement.setString(10, studentRecord.getExcretionId()); // 修正: インデックス 10
            statement.setString(11, studentRecord.getStudent_id()); // 修正: インデックス 11
            statement.setString(12, studentRecord.getAttendanceId()); // 修正: インデックス 12
            statement.setString(13, studentRecord.getAnualRecordId()); // 修正: インデックス 13

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
                studentRecord.setMealId(rSet.getString("meal_id"));
                studentRecord.setSleepId(rSet.getString("sleep_id"));
                studentRecord.setExcretionId(rSet.getString("excretion_id"));
                studentRecord.setStudent_id(rSet.getString("student_id"));
                studentRecord.setAttendanceId(rSet.getString("attendance_id"));
                studentRecord.setAnualRecordId(rSet.getString("anual_record_id"));
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
}
