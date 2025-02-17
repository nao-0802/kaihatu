package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao extends Dao {

	private ClassDao classDao = new ClassDao();


    // SQLクエリ: 学生の情報を取得するための基本SQL
    private String baseSql = "SELECT * FROM t_student WHERE class_id = ?";

    private static final String SELECT_ALL_STUDENTS_SQL =
            "SELECT student_id, student_name, class_id, flag " +
            "FROM t_student";

    // 修正されたSQLクエリ（MERGE文）
    private static final String SAVE_Student_SQL =
        "MERGE INTO t_student KEY(student_id) " +
        "VALUES (?, ?, ?, ?)";

    private static final String SELECT_ALL_ACTIVE_STUDENTS_SQL =
    	    "SELECT student_id, student_name, flag, class_id " +
    	    "FROM t_student " +
    	    "WHERE flag = 0"; // 在籍フラグが0の生徒のみを取得


 // guardian_idから生徒名を取得するメソッド
    public String getStudentNameByGuardianId(String guardianId) {
        String studentName = null;
        String sql = "SELECT s.student_name FROM t_student s " +
                     "JOIN t_student_record sr ON s.student_id = sr.student_id " +
                     "WHERE sr.guardian_id = ?";

        try (Connection conn = getConnection();
        	     PreparedStatement stmt = conn.prepareStatement(sql)) {

        	    stmt.setString(1, guardianId);
        	    try (ResultSet rs = stmt.executeQuery()) {
        	        if (rs.next()) {
        	            studentName = rs.getString("student_name");
        	        }
        	    }
        	} catch (SQLException e) {
        	    // SQLExceptionの処理
        	    e.printStackTrace();
        	} catch (Exception e) {
        	    // その他のExceptionの処理
        	    e.printStackTrace();
        	}
        return studentName;
    }


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

    public Student findStudentByName(String studentName) {
        String sql = "SELECT student_id, class_id FROM t_student WHERE student_name = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setClassId(rs.getString("class_id"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
        return null;
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

             // classIdに対応するクラス名を取得
                String className = classDao.getClassNameById(student.getClassId());
                student.setClassName(className);

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

    public Student findStudentById(String studentId) throws SQLException {
        Student student = null;
        String sql = "SELECT student_name FROM t_student WHERE student_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setStudentName(rs.getString("student_name"));
                }
            }
        } catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        return student;
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


 // 生徒を保存または更新
    public boolean save(Student student) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_Student_SQL)) {

            statement.setString(1, student.getStudentId());
            statement.setString(2, student.getStudentName());
            statement.setString(4, student.getClassId());
            statement.setInt(3, student.getFlag());

            System.out.println("実行する SQL: " + SAVE_Student_SQL);
            System.out.println("パラメータ: " + student.getStudentId() + ", " + student.getStudentName() + ", " + student.getClassId() + ", " + student.getFlag());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }

    public List<Student> getStudentsByFlag(String flag) throws Exception {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM t_student WHERE flag = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, flag);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setFlag(rs.getInt("flag"));
                student.setClassId(rs.getString("class_id"));

             // classIdに対応するクラス名を取得
                String className = classDao.getClassNameById(student.getClassId());
                student.setClassName(className);
                students.add(student);
            }
        }
        return students;
    }

 // 有効な生徒のみ取得するメソッド
    public List<Student> getAllActiveStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACTIVE_STUDENTS_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getString("student_id"));
                student.setStudentName(resultSet.getString("student_name"));
                student.setFlag(resultSet.getInt("flag"));
                student.setClassId(resultSet.getString("class_id"));


             // classIdに対応するクラス名を取得
                String className = classDao.getClassNameById(student.getClassId());
                student.setClassName(className);


                list.add(student);
            }
        }
        return list;
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
                 "SELECT * FROM t_student WHERE class_id = ? and flag = ?")) {
            ps.setString(1, classId);
            ps.setInt(2, 0);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getString("student_id"));
                    student.setStudentName(rs.getString("student_name"));
                    student.setClassId(rs.getString("class_id"));
                    // 他のプロパティをセット
                    list.add(student);
                }
            }
        }
        return list;
    }

 // student_id に紐づく学生情報を取得
    public Student findById(String studentId) throws Exception {
        Student student = null;
        String sql = "SELECT student_id, student_name, class_id FROM t_student WHERE student_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    student = new Student();
                    student.setStudentId(resultSet.getString("student_id"));
                    student.setStudentName(resultSet.getString("student_name"));
                    student.setClassId(resultSet.getString("class_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("学生情報の取得中にエラーが発生しました。");
        }

        return student;
    }

    public Student findByGuardianId(String guardianId) throws Exception {
        String sql = "SELECT s.student_id, s.student_name, s.class_id " +
                     "FROM t_student_record sr " +
                     "INNER JOIN t_student s ON sr.student_id = s.student_id " +
                     "WHERE sr.guardian_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, guardianId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student();
                    student.setStudentId(resultSet.getString("student_id"));
                    student.setStudentName(resultSet.getString("student_name"));
                    student.setClassId(resultSet.getString("class_id"));
                    return student;
                }
            }
        }

        return null; // 該当する学生が存在しない場合
    }






public List<Student> getStudentsByTeacherId(String teacherId) throws Exception {
    List<Student> studentList = new ArrayList<>();

    // 教師IDに基づいて生徒を取得するSQLクエリ
    String sql = "SELECT s.student_id, s.student_name, s.class_id " +
                 "FROM t_student s " +
                 "JOIN t_class tc ON s.class_id = tc.class_id " +
                 "JOIN t_teacher tt ON tc.class_id = tt.class_id " +
                 "WHERE tt.teacher_id = ?";
    // データベース接続
    try (Connection connection = getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

        // 教師IDをパラメータとして設定
    	ps.setString(1, teacherId);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setClassId(rs.getString("class_id"));
                studentList.add(student);
                System.out.println(rs.getString("student_id"));
            }
        }
    }catch (Exception e){

    } return studentList;
}
}

