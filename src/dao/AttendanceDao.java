package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Attendance;

public class AttendanceDao extends Dao {
    // SQLクエリ: student_idに基づいてレコードを取得
    private String baseSql = "SELECT attendance_id, student_id, day, type, notes FROM t_attendance WHERE student_id = ?";

    // ResultSetからAttendanceリストを生成するメソッド
    private List<Attendance> postfilter(ResultSet rSet) throws Exception {
        List<Attendance> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Attendance attendance = new Attendance();
                attendance.setAttendanceId(rSet.getString("attendance_id"));
                attendance.setStudentId(rSet.getString("student_id"));
                attendance.setDay(rSet.getDate("day"));
                attendance.setType(rSet.getInt("type"));
                attendance.setNotes(rSet.getString("notes"));
                list.add(attendance);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    // 指定されたstudent_idのAttendanceを取得するメソッド
    public List<Attendance> filter(String studentId) throws Exception {
        List<Attendance> list = new ArrayList<>();
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
    public boolean save(Attendance attendance) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存のAttendanceを検索
            Attendance existingAttendance = getAttendance(attendance.getAttendanceId());
            if (existingAttendance == null) {
                // 新しいAttendanceの場合、挿入
                statement = connection.prepareStatement(
                        "INSERT INTO t_attendance (attendance_id, student_id, day, type, notes) VALUES (?, ?, ?, ?, ?)"
                );
                statement.setString(1, attendance.getAttendanceId());
                statement.setString(2, attendance.getStudentId());
                statement.setDate(3, attendance.getDay());
                statement.setInt(4, attendance.getType());
                statement.setString(5, attendance.getNotes());
            } else {
                // 既存のAttendanceの場合、更新
                statement = connection.prepareStatement(
                        "UPDATE t_attendance SET student_id = ?, day = ?, type = ?, notes = ? WHERE attendance_id = ?"
                );
                statement.setString(1, attendance.getStudentId());
                statement.setDate(2, attendance.getDay());
                statement.setInt(3, attendance.getType());
                statement.setString(4, attendance.getNotes());
                statement.setString(5, attendance.getAttendanceId());
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

    // Attendanceを取得するメソッド（更新や削除の前に確認）
    private Attendance getAttendance(String attendanceId) throws Exception {
        Attendance attendance = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM t_attendance WHERE attendance_id = ?");
            statement.setString(1, attendanceId);
            rSet = statement.executeQuery();
            if (rSet.next()) {
                attendance = new Attendance();
                attendance.setAttendanceId(rSet.getString("attendance_id"));
                attendance.setStudentId(rSet.getString("student_id"));
                attendance.setDay(rSet.getDate("day"));
                attendance.setType(rSet.getInt("type"));
                attendance.setNotes(rSet.getString("notes"));
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

        return attendance;
    }

    // Attendanceを削除するメソッド
    public boolean delete(String attendanceId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM t_attendance WHERE attendance_id = ?");
            statement.setString(1, attendanceId);
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
