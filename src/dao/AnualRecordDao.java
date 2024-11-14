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

    // AnualRecordを保存または更新するメソッド
    public boolean save(AnualRecord record) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存のAnualRecordを検索
            AnualRecord existingRecord = getAnualRecord(record.getAnualRecordId());
            if (existingRecord == null) {
                // 新しいAnualRecordの場合
                statement = connection.prepareStatement(
                        "INSERT INTO anual_records (anual_record_id, class_id, text) VALUES (?, ?, ?)"
                );
                statement.setString(1, record.getAnualRecordId());
                statement.setString(2, record.getClassId());
                statement.setString(3, record.getText());
            } else {
                // 既存のAnualRecordの更新
                statement = connection.prepareStatement(
                        "UPDATE anual_records SET class_id = ?, text = ? WHERE anual_record_id = ?"
                );
                statement.setString(1, record.getClassId());
                statement.setString(2, record.getText());
                statement.setString(3, record.getAnualRecordId());
            }
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

    // anual_record_idでAnualRecordを取得するメソッド
    private AnualRecord getAnualRecord(String anualRecordId) throws Exception {
        AnualRecord record = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, anualRecordId);
            rSet = statement.executeQuery();
            if (rSet.next()) {
                record = new AnualRecord();
                record.setAnualRecordId(rSet.getString("anual_record_id"));
                record.setClassId(rSet.getString("class_id"));
                record.setText(rSet.getString("text"));
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

        return record;
    }

    // AnualRecordを削除するメソッド
    public boolean delete(String anualRecordId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM anual_records WHERE anual_record_id = ?");
            statement.setString(1, anualRecordId);
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
