package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.SleepRecord;

public class SleepRecordDao extends Dao {
    // SQLクエリ: student_idに基づいてレコードを取得
    private String baseSql = "SELECT  student_id,day, time, sleep FROM t_sleep_record WHERE student_id = ?";

    // ResultSetからSleepRecordリストを生成するメソッド
    private List<SleepRecord> postfilter(ResultSet rSet) throws Exception {
        List<SleepRecord> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                SleepRecord sleepRecord = new SleepRecord();
//                sleepRecord.setSleepId(rSet.getString("sleep_id"));
              sleepRecord.setStudentId(rSet.getString("student_id"));
                sleepRecord.setDay(rSet.getDate("day"));
                sleepRecord.setTime(rSet.getDate("time"));
                sleepRecord.setSleep(rSet.getInt("sleep"));
                list.add(sleepRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    // 指定されたstudent_idのSleepRecordを取得するメソッド
    public List<SleepRecord> filter(String studentId) throws Exception {
        List<SleepRecord> list = new ArrayList<>();
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
    public boolean save(SleepRecord sleepRecord) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存のSleepRecordを検索
            SleepRecord existingRecord = getSleepRecord(sleepRecord.getSleepId());
            if (existingRecord == null) {
                // 新しいSleepRecordの場合、挿入
                statement = connection.prepareStatement(
                        "INSERT INTO t_sleep_record ( student_id,day, time, sleep) VALUES (?,?, ?, ?)"
                );
//                statement.setString(1, sleepRecord.getSleepId());
                statement.setString(1, sleepRecord.getStudentId());
                statement.setDate(2, sleepRecord.getDay());
                statement.setDate(3, sleepRecord.getTime());
                statement.setInt(4, sleepRecord.getSleep());
            } else {
                // 既存のSleepRecordの場合、更新
                statement = connection.prepareStatement(
                        "UPDATE t_sleep_record SET  = ?, day = ?, time = ?, sleep = ? WHERE sleep_id = ?"
                );
                statement.setString(1, sleepRecord.getStudentId());
                statement.setDate(2, sleepRecord.getDay());
                statement.setDate(3, sleepRecord.getTime());
                statement.setInt(4, sleepRecord.getSleep());
//                statement.setString(5, sleepRecord.getSleepId());
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

    // SleepRecordを取得するメソッド（更新や削除の前に確認）
    private SleepRecord getSleepRecord(String sleepId) throws Exception {
        SleepRecord sleepRecord = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM t_sleep_record WHERE sleep_id = ?");
            statement.setString(1, sleepId);
            rSet = statement.executeQuery();
            if (rSet.next()) {
                sleepRecord = new SleepRecord();
//                sleepRecord.setSleepId(rSet.getString("sleep_id"));
              sleepRecord.setStudentId(rSet.getString("student_id"));
                sleepRecord.setDay(rSet.getDate("day"));
                sleepRecord.setTime(rSet.getDate("time"));
                sleepRecord.setSleep(rSet.getInt("sleep"));

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

        return sleepRecord;
    }

    // SleepRecordを削除するメソッド
    public boolean delete(String sleepId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM t_sleep_record WHERE sleep_id = ?");
            statement.setString(1, sleepId);
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
