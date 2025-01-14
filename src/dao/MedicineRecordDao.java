package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MedicineRecord;

public class MedicineRecordDao extends Dao {
    // SQLクエリ: student_idに基づいてレコードを取得
    private String baseSql = "SELECT  student_id,day, time, medicine FROM t_medicine_record WHERE student_id = ?";

    // ResultSetからMedicineRecordリストを生成するメソッド
    private List<MedicineRecord> postfilter(ResultSet rSet) throws Exception {
        List<MedicineRecord> list = new ArrayList<>();
        try {
            while (rSet.next()) {
            	MedicineRecord medicineRecord = new MedicineRecord();
            	medicineRecord.setStudentId(rSet.getString("student_id"));
            	medicineRecord.setDay(rSet.getDate("day"));
            	medicineRecord.setTime(rSet.getTime("time"));
            	medicineRecord.setMedicine(rSet.getInt("medicine"));
                list.add(medicineRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    // 指定されたstudent_idのMedicineRecordを取得するメソッド
    public List<MedicineRecord> filter(String studentId) throws Exception {
        List<MedicineRecord> list = new ArrayList<>();
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
    public boolean save(MedicineRecord medicineRecord) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存MedicineRecordを検索
        	MedicineRecord existingRecord = getMedicineRecord(medicineRecord.getMedicineId());
            if (existingRecord == null) {
                // 新しいMedicineRecordの場合、挿入
                statement = connection.prepareStatement(
                        "INSERT INTO t_medicine_record ( student_id,day, time, medicine) VALUES (?,?, ?, ?)"
                );
;
                statement.setString(1, medicineRecord.getStudentId());
                statement.setDate(2, medicineRecord.getDay());
                statement.setTime(3, medicineRecord.getTime());
                statement.setInt(4, medicineRecord.getMedicine());
            } else {
                // 既存のMedicineRecordの場合、更新
                statement = connection.prepareStatement(
                        "UPDATE t_medicine_record SET  = ?, day = ?, time = ?, medicine = ? WHERE medicine_id = ?"
                );
                statement.setString(1, medicineRecord.getStudentId());
                statement.setDate(2, medicineRecord.getDay());
                statement.setTime(3, medicineRecord.getTime());
                statement.setInt(4, medicineRecord.getMedicine());

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

    // MedicineRecordを取得するメソッド（更新や削除の前に確認）
    private MedicineRecord getMedicineRecord(String medicineId) throws Exception {
    	MedicineRecord medicineRecord = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM t_medicine_record WHERE medicine_id = ?");
            statement.setString(1, medicineId);
            rSet = statement.executeQuery();
            if (rSet.next()) {
            	medicineRecord = new MedicineRecord();

            	medicineRecord.setStudentId(rSet.getString("student_id"));
            	medicineRecord.setDay(rSet.getDate("day"));
            	medicineRecord.setTime(rSet.getTime("time"));
            	medicineRecord.setMedicine(rSet.getInt("medicine"));

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

        return medicineRecord;
    }

    // MedicineRecordを削除するメソッド
    public boolean delete(String medicineId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM t_medicine_record WHERE medicine_id = ?");
            statement.setString(1, medicineId);
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
