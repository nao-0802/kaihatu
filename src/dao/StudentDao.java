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
    private String baseSql = "SELECT * FROM t_student WHERE class_id = ?";

    private static final String SELECT_ALL_STUDENTS_SQL =
            "SELECT student_id, student_name, class_id, flag " +
            "FROM t_student";

    // ResultSetからStudentリストを生成するメソッド
    private List<Student> postFilter(ResultSet rSet) throws SQLException {
        List<Student> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Student student = new Student();
                student.setStudentId(rSet.getString("student_id"));
                student.setStudentName(rSet.getString("student_name"));
                student.setClassId(rSet.getString("class_id"));
                student.setFlag(rSet.getInt("flag"));
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

 // すべての生徒を取得
    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STUDENTS_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getString("student_id"));
                student.setStudentName(resultSet.getString("student_name"));
                student.setClassId(resultSet.getString("class_id"));
                student.setFlag(resultSet.getInt("flag"));
                list.add(student);
            }
        }
        return list;
    }

    public String findStudentIdByStudentName(String studentName) {
        String studentId = null;

        try (Connection conn = getConnection()) {
            String sql = "SELECT student_id FROM t_student WHERE student_name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                studentId = rs.getString("student_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentId; // 見つからない場合はnullを返す
    }

    public Student get(String student_id) throws Exception {
    	Student student = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM t_student WHERE student_id = ?");
            statement.setString(1, student_id);
            ResultSet rSet = statement.executeQuery();
            if (rSet.next()) {
                student = new Student();
                student.setStudentId(rSet.getString("student_id"));
                student.setStudentName(rSet.getString("student_name"));
                student.setClassId(rSet.getString("class_id"));
                student.setFlag(rSet.getInt("flag"));
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
            String sql = "SELECT * FROM t_student WHERE student_name LIKE ? OR student_id LIKE ?";
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
                    "INSERT INTO t_student (student_id, student_name, class_id, flag) VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, student.getStudentId());
            statement.setString(2, student.getStudentName());
            statement.setString(3, student.getClassId());
            statement.setInt(4, student.getFlag());

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
            statement = connection.prepareStatement("DELETE FROM t_student WHERE student_id = ?");
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

    public List<Student> filterByClassId(String classId) throws Exception {
        List<Student> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "SELECT * FROM t_student WHERE class_id = ?")) {
            ps.setString(1, classId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getString("student_id"));
                    student.setStudentName(rs.getString("name"));
                    student.setClassId(rs.getString("class_id"));
                    // 他のプロパティをセット
                    list.add(student);
                }
            }
        }
        return list;
    }






public List<Student> getStudentsByTeacherId(String teacherId) throws SQLException {
    List<Student> studentList = new ArrayList<>();

    // 教師IDに基づいて生徒を取得するSQLクエリ
    String sql = "SELECT s.student_id, s.student_name, s.class_id " +
                 "FROM students s " +
                 "JOIN teacher_student ts ON s.student_id = ts.student_id " +
                 "WHERE ts.teacher_id = ?";

    try (Connection connection = getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        // 教師IDをパラメータとして設定
    	statement.setString(1, teacherId);

        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setClassId(rs.getString("class_id"));
                studentList.add(student);
            }
        }
    }

    return studentList;
  }
}