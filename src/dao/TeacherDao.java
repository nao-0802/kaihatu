package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;

public class TeacherDao extends Dao {

	private ClassDao classDao = new ClassDao();

    // SQLクエリ
    private static final String SEARCH_TEACHERS_SQL =
        "SELECT teacher_id, teacher_name, password, class_id, flag " +
        "FROM t_teacher " +
        "WHERE teacher_name LIKE ? OR teacher_id LIKE ?";

    // 修正されたSQLクエリ（MERGE文）
    private static final String SAVE_TEACHER_SQL =
        "MERGE INTO t_teacher KEY(teacher_id) " +
        "VALUES (?, ?, ?, ?, ?)";

    private static final String DELETE_TEACHER_SQL =
        "DELETE FROM t_teacher WHERE teacher_id = ?";

    private static final String SELECT_TEACHER_SQL =
        "SELECT teacher_id, teacher_name, password, class_id, flag " +
        "FROM t_teacher WHERE teacher_id = ?";

    private static final String SELECT_ALL_TEACHERS_SQL =
        "SELECT teacher_id, teacher_name, password, class_id, flag " +
        "FROM t_teacher";

    private static final String SELECT_ALL_ACTIVE_TEACHERS_SQL =
    	    "SELECT teacher_id, teacher_name, password, class_id, flag " +
    	    "FROM t_teacher " +
    	    "WHERE flag = 0"; // 在籍フラグが0の教職員のみを取得


    // すべての教師を取得
    public List<Teacher> getAllTeachers() throws Exception {
        List<Teacher> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TEACHERS_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(resultSet.getString("teacher_id"));
                teacher.setTeacherName(resultSet.getString("teacher_name"));
                teacher.setPassword(resultSet.getString("password"));
                teacher.setClassId(resultSet.getString("class_id"));
                teacher.setFlag(resultSet.getInt("flag"));
                list.add(teacher);
            }
        }
        return list;
    }

 // 有効な教師のみ取得するメソッド
    public List<Teacher> getAllActiveTeachers() throws Exception {
        List<Teacher> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACTIVE_TEACHERS_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(resultSet.getString("teacher_id"));
                teacher.setTeacherName(resultSet.getString("teacher_name"));
                teacher.setPassword(resultSet.getString("password"));
                teacher.setClassId(resultSet.getString("class_id"));
                teacher.setFlag(resultSet.getInt("flag"));

             // classIdに対応するクラス名を取得
                String className = classDao.getClassNameById(teacher.getClassId());
                teacher.setClassName(className);


                list.add(teacher);
            }
        }
        return list;
    }

    // 特定の教師を取得
    public Teacher get(String teacherId) throws Exception {
        Teacher teacher = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TEACHER_SQL)) {

            statement.setString(1, teacherId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    teacher = new Teacher();
                    teacher.setTeacherId(resultSet.getString("teacher_id"));
                    teacher.setTeacherName(resultSet.getString("teacher_name"));
                    teacher.setPassword(resultSet.getString("password"));
                    teacher.setClassId(resultSet.getString("class_id"));
                    teacher.setFlag(resultSet.getInt("flag"));
                }
            }
        }
        return teacher;
    }

    // 教師を検索
    public List<Teacher> search(String keyword) throws Exception {
        List<Teacher> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SEARCH_TEACHERS_SQL)) {

            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setTeacherId(resultSet.getString("teacher_id"));
                    teacher.setTeacherName(resultSet.getString("teacher_name"));
                    teacher.setPassword(resultSet.getString("password"));
                    teacher.setClassId(resultSet.getString("class_id"));
                    teacher.setFlag(resultSet.getInt("flag"));
                    list.add(teacher);
                }
            }
        }
        return list;
    }

    // 教師を保存または更新
    public boolean save(Teacher teacher) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_TEACHER_SQL)) {

            statement.setString(1, teacher.getTeacherId());
            statement.setString(2, teacher.getTeacherName());
            statement.setString(3, teacher.getPassword());
            statement.setString(4, teacher.getClassId());
            statement.setInt(5, teacher.getFlag());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }

    // 教師を削除
    public boolean delete(String teacherId) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TEACHER_SQL)) {

            statement.setString(1, teacherId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }

    public Teacher login(String teacher_id, String password) throws Exception {
        // 管理者クラスのインスタンスを取得
        Teacher teacher = get(teacher_id);
        // 管理者がnullまたはパスワードが一致しない場合
        if (teacher == null || !teacher.getPassword().equals(password)) {
            return null;
        }
        return teacher;
    }
 // class_id に紐づく教師情報を取得
    public Teacher findByClassId(String classId) throws Exception {
        Teacher teacher = null;
        String sql = "SELECT teacher_id, teacher_name FROM t_teacher WHERE class_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, classId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    teacher = new Teacher();
                    teacher.setTeacherId(resultSet.getString("teacher_id"));
                    teacher.setTeacherName(resultSet.getString("teacher_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("教師情報の取得中にエラーが発生しました。");
        }

        return teacher;
    }

}
