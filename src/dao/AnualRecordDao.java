package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AnualRecord;

public class AnualRecordDao extends Dao {
    // SQLクエリ: anual_record_idに基づいてレコードを取得
    private String baseSql = "SELECT anual_record_id, class_id, text FROM anual_records WHERE class_id = ?";

    // ResultSetからAnualRecordリストを生成するメソッド
    private List<AnualRecord> postfilter(ResultSet rSet) throws Exception {
        List<AnualRecord> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                AnualRecord record = new AnualRecord();
                record.setAnualRecordId(rSet.getString("anual_record_id"));
                record.setClassId(rSet.getString("class_id"));
                record.setText(rSet.getString("text"));
                list.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    // 指定されたclass_idのAnualRecordを取得するメソッド
    public List<AnualRecord> filter(String classId) throws Exception {
        List<AnualRecord> list = new ArrayList<>();
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
