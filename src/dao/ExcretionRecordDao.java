package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ExcretionRecord;

public class ExcretionRecordDao extends Dao {
    // SQLクエリ: student_idに基づいてレコードを取得
    private String baseSql = "SELECT excretion_id, student_id, day, time, type, excretion_detail FROM t_excretion_record WHERE student_id = ?";

    // ResultSetからExcretionRecordリストを生成するメソッド
    private List<ExcretionRecord> postfilter(ResultSet rSet) throws Exception {
        List<ExcretionRecord> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                ExcretionRecord excretionRecord = new ExcretionRecord();
                excretionRecord.setExcretionId(rSet.getString("excretion_id"));
                excretionRecord.setStudentId(rSet.getString("student_id"));
                excretionRecord.setDay(rSet.getDate("day"));
                excretionRecord.setTime(rSet.getTime("time"));
                excretionRecord.setType(rSet.getInt("type"));
                excretionRecord.setExcretionDetail(rSet.getString("excretion_detail"));
                list.add(excretionRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    // 指定されたstudent_idのExcretionRecordを取得するメソッド
    public List<ExcretionRecord> filter(String studentId) throws Exception {
        List<ExcretionRecord> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, studentId);
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

    // 新規または更新を行うメソッド
    public boolean save(ExcretionRecord excretionRecord) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存のExcretionRecordを検索
            ExcretionRecord existingRecord = getExcretionRecord(excretionRecord.getExcretionId());
            if (existingRecord == null) {
                // 新しいExcretionRecordの場合、挿入
                statement = connection.prepareStatement(
                        "INSERT INTO t_excretion_record (excretion_id, student_id, day, time, type, excretion_detail) VALUES (?, ?, ?, ?, ?, ?)"
                );
                statement.setString(1, excretionRecord.getExcretionId());
                statement.setString(2, excretionRecord.getStudentId());
                statement.setDate(3, excretionRecord.getDay());
                statement.setTime(4, excretionRecord.getTime());
                statement.setInt(5, excretionRecord.getType());
                statement.setString(6, excretionRecord.getExcretionDetail());
            } else {
                // 既存のExcretionRecordの場合、更新
                statement = connection.prepareStatement(
                        "UPDATE t_excretion_record SET student_id = ?, day = ?, time = ?, type = ?, excretion_detail = ? WHERE excretion_id = ?"
                );
                statement.setString(1, excretionRecord.getStudentId());
                statement.setDate(2, excretionRecord.getDay());
                statement.setTime(3, excretionRecord.getTime());
                statement.setInt(4, excretionRecord.getType());
                statement.setString(5, excretionRecord.getExcretionDetail());
                statement.setString(6, excretionRecord.getExcretionId());
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

    // ExcretionRecordを取得するメソッド（更新や削除の前に確認）
    private ExcretionRecord getExcretionRecord(String excretionId) throws Exception {
        ExcretionRecord excretionRecord = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM t_excretion_record WHERE excretion_id = ?");
            statement.setString(1, excretionId);
            rSet = statement.executeQuery();
            if (rSet.next()) {
                excretionRecord = new ExcretionRecord();
                excretionRecord.setExcretionId(rSet.getString("excretion_id"));
                excretionRecord.setStudentId(rSet.getString("student_id"));
                excretionRecord.setDay(rSet.getDate("day"));
                excretionRecord.setTime(rSet.getTime("time"));
                excretionRecord.setType(rSet.getInt("type"));
                excretionRecord.setExcretionDetail(rSet.getString("excretion_detail"));
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

        return excretionRecord;
    }

    // ExcretionRecordを削除するメソッド
    public boolean delete(String excretionId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM t_excretion_record WHERE excretion_id = ?");
            statement.setString(1, excretionId);
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
