package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao extends Dao {
    // SQLクエリ: 学生の情報を取得するための基本SQL
    private String baseSql = "SELECT * FROM students WHERE class_id = ?";

    // ResultSetからStudentリストを生成するメソッド
    private List<Student> postFilter(ResultSet rSet) throws SQLException {
        List<Student> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Student student = new Student();
                student.setStudentId(rSet.getString("student_id"));
                student.setStudentName(rSet.getString("student_name"));
                student.setPassword(rSet.getString("password"));
                student.setClassId(rSet.getString("class_id"));
                student.setGuardianId(rSet.getString("guardian_id"));
                student.setFlag(rSet.getInt("flag"));
                student.setStudentRecordId(rSet.getString("student_record_id"));
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    public Student get(String student_id) throws Exception {
        Student student = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM student WHERE student_id = ?");
            statement.setString(1, student_id);
            ResultSet rSet = statement.executeQuery();
            if (rSet.next()) {
                student = new Student();
                student.setStudentId(rSet.getString("student_id"));
                student.setPassword(rSet.getString("password"));
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
        return student;
    }


    // 指定されたclass_idの学生リストを取得するメソッド
    public List<Student> filter(String classId) throws Exception {
        List<Student> list = new ArrayList<>();
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

    public List<Student> search(String keyword) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            String sql = "SELECT * FROM students WHERE student_name LIKE ? OR student_id LIKE ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            rSet = statement.executeQuery();
            list = postFilter(rSet);
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

        return list;
    }


    // 学生を保存または更新するメソッド
    public boolean save(Student student) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 新しい学生の場合は挿入、既存の学生の場合は更新
            statement = connection.prepareStatement(
                    "INSERT INTO students (student_id, student_name, password, class_id, guardian_id, flag, student_record_id) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, student.getStudentId());
            statement.setString(2, student.getStudentName());
            statement.setString(3, student.getPassword());
            statement.setString(4, student.getClassId());
            statement.setString(5, student.getGuardianId());
            statement.setInt(6, student.getFlag());
            statement.setString(7, student.getStudentRecordId());

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

    // 指定されたstudent_idの学生を削除するメソッド
    public boolean delete(String studentId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM students WHERE student_id = ?");
            statement.setString(1, studentId);
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
