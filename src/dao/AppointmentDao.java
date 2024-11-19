package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Appointment;

public class AppointmentDao extends Dao {
    // SQLクエリ: teacher_idに基づいてレコードを取得
    private String baseSql = "SELECT appointment_id, teacher_id, guardian_id, appointment_date, appointment_time, flag FROM t_appointment WHERE teacher_id = ?";

    // ResultSetからAppointmentリストを生成するメソッド
    private List<Appointment> postfilter(ResultSet rSet) throws Exception {
        List<Appointment> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rSet.getString("appointment_id"));
                appointment.setTeacherId(rSet.getString("teacher_id"));
                appointment.setGuardianId(rSet.getString("guardian_id"));
                appointment.setAppointmentDate(rSet.getDate("appointment_date"));
                appointment.setAppointmentTime(rSet.getTime("appointment_time"));
                appointment.setFlag(rSet.getBoolean("flag"));
                list.add(appointment);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    // 指定されたteacher_idのAppointmentを取得するメソッド
    public List<Appointment> filter(String teacherId) throws Exception {
        List<Appointment> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, teacherId);
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

    // Appointmentを保存または更新するメソッド
    public boolean save(Appointment appointment) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存のAppointmentを検索
            Appointment existingAppointment = getAppointment(appointment.getAppointmentId());
            if (existingAppointment == null) {
                // 新しいAppointmentの場合
                statement = connection.prepareStatement(
                        "INSERT INTO t_appointment (appointment_id, teacher_id, guardian_id, appointment_date, appointment_time, flag) VALUES (?, ?, ?, ?, ?, ?)"
                );
                statement.setString(1, appointment.getAppointmentId());
                statement.setString(2, appointment.getTeacherId());
                statement.setString(3, appointment.getGuardianId());
                statement.setDate(4, appointment.getAppointmentDate());
                statement.setTime(5, appointment.getAppointmentTime());
                statement.setBoolean(6, appointment.getFlag());
            } else {
                // 既存のAppointmentの更新
                statement = connection.prepareStatement(
                        "UPDATE t_appointment SET teacher_id = ?, guardian_id = ?, appointment_date = ?, appointment_time = ?, flag = ? WHERE appointment_id = ?"
                );
                statement.setString(1, appointment.getTeacherId());
                statement.setString(2, appointment.getGuardianId());
                statement.setDate(3, appointment.getAppointmentDate());
                statement.setTime(4, appointment.getAppointmentTime());
                statement.setBoolean(5, appointment.getFlag());
                statement.setString(6, appointment.getAppointmentId());
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

    // appointment_idでAppointmentを取得するメソッド
    private Appointment getAppointment(String appointmentId) throws Exception {
        Appointment appointment = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM t_appointment WHERE appointment_id = ?");
            statement.setString(1, appointmentId);
            rSet = statement.executeQuery();
            if (rSet.next()) {
                appointment = new Appointment();
                appointment.setAppointmentId(rSet.getString("appointment_id"));
                appointment.setTeacherId(rSet.getString("teacher_id"));
                appointment.setGuardianId(rSet.getString("guardian_id"));
                appointment.setAppointmentDate(rSet.getDate("appointment_date"));
                appointment.setAppointmentTime(rSet.getTime("appointment_time"));
                appointment.setFlag(rSet.getBoolean("flag"));
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

        return appointment;
    }

    // Appointmentを削除するメソッド
    public boolean delete(String appointmentId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM t_appointment WHERE appointment_id = ?");
            statement.setString(1, appointmentId);
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
