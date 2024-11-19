package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MealRecord;

public class MealRecordDao extends Dao {
    // SQLクエリ: student_idに基づいてレコードを取得
    private String baseSql = "SELECT meal_id, student_id, day, time, type, meal_amount FROM t_meal_record WHERE student_id = ?";

    // ResultSetからMealRecordリストを生成するメソッド
    private List<MealRecord> postfilter(ResultSet rSet) throws Exception {
        List<MealRecord> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                MealRecord mealRecord = new MealRecord();
                mealRecord.setMealId(rSet.getString("meal_id"));
                mealRecord.setStudentId(rSet.getString("student_id"));
                mealRecord.setDay(rSet.getDate("day"));
                mealRecord.setTime(rSet.getDate("time"));
                mealRecord.setType(rSet.getInt("type"));
                mealRecord.setMealAmount(rSet.getInt("meal_amount"));
                list.add(mealRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    // 指定されたstudent_idのMealRecordを取得するメソッド
    public List<MealRecord> filter(String studentId) throws Exception {
        List<MealRecord> list = new ArrayList<>();
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
    public boolean save(MealRecord mealRecord) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存のMealRecordを検索
            MealRecord existingRecord = getMealRecord(mealRecord.getMealId());
            if (existingRecord == null) {
                // 新しいMealRecordの場合、挿入
                statement = connection.prepareStatement(
                        "INSERT INTO t_meal_record (meal_id, student_id, day, time, type, meal_amount) VALUES (?, ?, ?, ?, ?, ?)"
                );
                statement.setString(1, mealRecord.getMealId());
                statement.setString(2, mealRecord.getStudentId());
                statement.setDate(3, mealRecord.getDay());
                statement.setDate(4, mealRecord.getTime());
                statement.setInt(5, mealRecord.getType());
                statement.setInt(6, mealRecord.getMealAmount());
            } else {
                // 既存のMealRecordの場合、更新
                statement = connection.prepareStatement(
                        "UPDATE t_meal_record SET student_id = ?, day = ?, time = ?, type = ?, meal_amount = ? WHERE meal_id = ?"
                );
                statement.setString(1, mealRecord.getStudentId());
                statement.setDate(2, mealRecord.getDay());
                statement.setDate(3, mealRecord.getTime());
                statement.setInt(4, mealRecord.getType());
                statement.setInt(5, mealRecord.getMealAmount());
                statement.setString(6, mealRecord.getMealId());
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

    // MealRecordを取得するメソッド（更新や削除の前に確認）
    private MealRecord getMealRecord(String mealId) throws Exception {
        MealRecord mealRecord = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, mealId);
            rSet = statement.executeQuery();
            if (rSet.next()) {
                mealRecord = new MealRecord();
                mealRecord.setMealId(rSet.getString("meal_id"));
                mealRecord.setStudentId(rSet.getString("student_id"));
                mealRecord.setDay(rSet.getDate("day"));
                mealRecord.setTime(rSet.getDate("time"));
                mealRecord.setType(rSet.getInt("type"));
                mealRecord.setMealAmount(rSet.getInt("meal_amount"));
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

        return mealRecord;
    }

    // MealRecordを削除するメソッド
    public boolean delete(String mealId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM t_meal_record WHERE meal_id = ?");
            statement.setString(1, mealId);
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
