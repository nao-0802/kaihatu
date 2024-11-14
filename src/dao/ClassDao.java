package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Class;

public class ClassDao extends Dao {
    // SQLクエリ: class_idに基づいてレコードを取得
    private String baseSql = "SELECT class_id, class_name FROM classes WHERE class_id = ?";

    // ResultSetからClassリストを生成するメソッド
    private List<Class> postfilter(ResultSet rSet) throws Exception {
        List<Class> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Class classObj = new Class();
                classObj.setClassId(rSet.getString("class_id"));
                classObj.setClassName(rSet.getString("class_name"));
                list.add(classObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    // 指定されたclass_idのClassを取得するメソッド
    public List<Class> filter(String classId) throws Exception {
        List<Class> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, classId);
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
}
