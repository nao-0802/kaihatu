package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Class;

public class ClassDao extends Dao {
    // SQLクエリ: 全クラスを取得
    private String getAllSql = "SELECT class_id, class_name FROM t_class";

 // クラスIDに対応するクラス名を取得するSQLクエリ
    private static final String SELECT_CLASS_NAME_SQL =
        "SELECT class_name FROM t_class WHERE class_id = ?";

    public void save(Class newClass) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO t_class (class_id, class_name) VALUES (?, ?)")) {

            statement.setString(1, newClass.getClassId());
            statement.setString(2, newClass.getClassName());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 全クラスを取得するメソッド
    public List<Class> getAllClasses() throws Exception {
        List<Class> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(getAllSql);
            rSet = statement.executeQuery();
            list = postfilter(rSet);
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

    public String findClassIdByClassName(String className) {
        String classId = null;

        try (Connection conn = getConnection()) {
            String sql = "SELECT class_id FROM t_class WHERE class_name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, className);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                classId = rs.getString("class_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classId; // 見つからない場合はnullを返す
    }


    public String getClassNameById(String classId) throws Exception {
        String className = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_CLASS_NAME_SQL)) {

            statement.setString(1, classId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    className = resultSet.getString("class_name");
                }
            }
        }
        return className;
    }

    // ResultSetからClassリストを生成する共通処理
    private List<Class> postfilter(ResultSet rSet) throws Exception {
        List<Class> list = new ArrayList<>();
        while (rSet.next()) {
            Class classObj = new Class();
            classObj.setClassId(rSet.getString("class_id"));
            classObj.setClassName(rSet.getString("class_name"));
            list.add(classObj);
        }
        return list;
    }
}
