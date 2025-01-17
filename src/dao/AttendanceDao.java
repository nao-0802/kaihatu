package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bean.Attendance;
import bean.Student;

public class AttendanceDao extends Dao {
    private Connection connection;

    public AttendanceDao(Connection connection) {
        this.connection = connection;
    }

    // SQLクエリ: student_idに基づいてレコードを取得
    private String baseSql = "SELECT attendance_id, student_id, day, type,time,reason,symptom, notes FROM t_attendance WHERE student_id = ?";

    // ResultSetからAttendanceリストを生成するメソッド
    private List<Attendance> postfilter(ResultSet rSet) throws Exception {
        List<Attendance> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Attendance attendance = new Attendance();
                attendance.setAttendanceId(rSet.getString("attendance_id"));
                attendance.setStudentId(rSet.getString("student_id"));
                attendance.setDay(rSet.getDate("day"));
                attendance.setType(rSet.getString("type"));
                attendance.setNotes(rSet.getString("notes"));
                attendance.setTime(rSet.getTime("time"));
                attendance.setSymptom(rSet.getString("symptom"));
                attendance.setReason(rSet.getString("reason"));
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
                		"INSERT INTO t_attendance (attendance_id, student_id, day, type,time,symptom,reason, notes) VALUES (?, ?,?, ?, ?, ?, ?, ?)"



                );
                statement.setString(1, attendance.getAttendanceId());
                statement.setString(2, attendance.getStudentId());
                statement.setDate(3, attendance.getDay());
                statement.setString(4, attendance.getType());
                statement.setTime(5, attendance.getTime());
                statement.setString(6, attendance.getSymptom());
                statement.setString(7, attendance.getReason());
                statement.setString(8, attendance.getNotes());

            } else {
                // 既存のAttendanceの場合、更新
                statement = connection.prepareStatement(
                		"UPDATE t_attendance SET student_id = ?, day = ?, type = ?, time = ?,reason = ?, symptom = ?, notes = ? WHERE attendance_id = ?"

                );
                statement.setString(1, attendance.getStudentId());
                statement.setDate(2, attendance.getDay());
                statement.setString(3, attendance.getType());
                statement.setTime(4, attendance.getTime());
                statement.setString(5, attendance.getReason());
                statement.setString(6, attendance.getSymptom());
                statement.setString(7, attendance.getNotes());
                statement.setString(8, attendance.getAttendanceId());
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
                attendance.setType(rSet.getString("type"));
                attendance.setTime(rSet.getTime("time"));
                attendance.setReason(rSet.getString("reason"));
                attendance.setSymptom(rSet.getString("symptom"));
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




    public List<Attendance> getAttendancesByTeacherId(String userId) throws Exception {
        List<Attendance> attendanceList = new ArrayList<>();

        // まず、教師のclass_idを取得するSQLクエリ
        String classIdSql = "SELECT CLASS_ID FROM t_teacher WHERE TEACHER_ID = ?";

        // 教師のクラスIDを取得
        String classId = null;
        try (Connection connection = getConnection();
             PreparedStatement psClassId = connection.prepareStatement(classIdSql)) {

            psClassId.setString(1, userId);

            try (ResultSet rsClassId = psClassId.executeQuery()) {
                if (rsClassId.next()) {
                    classId = rsClassId.getString("CLASS_ID");
                }
            }
        }

        // classIdがnullでない場合に、出席情報を取得するSQLクエリを実行
        if (classId != null) {
            String attendanceSql = "SELECT a.attendance_id, a.student_id, a.day, a.type, a.time, a.notes, a.reason, a.symptom, s.student_name " +
                    "FROM t_attendance a " +
                    "JOIN t_student s ON a.student_id = s.student_id " +
                    "WHERE s.class_id = ? " +
                    "ORDER BY a.day DESC";

            try (Connection connection = getConnection();
                 PreparedStatement psAttendance = connection.prepareStatement(attendanceSql)) {

                // クラスIDをパラメータとして設定
                psAttendance.setString(1, classId);

                // 出席情報を実行して結果を取得
                try (ResultSet rs = psAttendance.executeQuery()) {
                    while (rs.next()) {
                        // 出席情報をAttendanceオブジェクトに設定
                        Attendance attendance = new Attendance();
                        attendance.setAttendanceId(rs.getString("attendance_id"));
                        attendance.setStudentId(rs.getString("student_id"));
                        attendance.setDay(rs.getDate("day"));

                        // 出席状況（type）の文字列をそのまま取得
                        String attendanceStatus = rs.getString("type");
                        if (attendanceStatus == null || attendanceStatus.isEmpty()) {
                            attendanceStatus = "不明";  // 空やnullの場合にデフォルトで「不明」に設定
                        }
                        attendance.setType(attendanceStatus);  // 文字列として設定

                        // 理由（reason）の文字列をそのまま取得
                        String reason = rs.getString("reason");
                        if (reason == null || reason.isEmpty()) {
                            reason = "不明";  // 空やnullの場合にデフォルトで「不明」に設定
                        }
                        attendance.setReason(reason);

                        // 症状（symptom）の文字列をそのまま取得
                        String symptom = rs.getString("symptom");
                        if (symptom == null || symptom.isEmpty()) {
                            symptom = "なし";  // 空やnullの場合にデフォルトで「不明」に設定
                        }
                        attendance.setSymptom(symptom);

                        attendance.setTime(rs.getTime("time"));
                        attendance.setNotes(rs.getString("notes"));
                        attendance.setStudentName(rs.getString("student_name")); // 生徒名を設定

                        // リストに追加
                        attendanceList.add(attendance);
                    }
                }
            }
        }

        return attendanceList;
    }




    public List<Attendance> getAttendancesByStudents(List<Student> students) throws Exception {
        List<Attendance> attendanceList = new ArrayList<>();

        // 学生IDを取得
        List<String> studentIds = students.stream()
                .map(Student::getStudentId)
                .collect(Collectors.toList());


        // 学生IDが空の場合、処理を終了
        if (studentIds.isEmpty()) {
            return attendanceList;
        }

        // 出席情報を取得するSQLクエリ
        String attendanceSql = "SELECT a.attendance_id, a.student_id, a.day, a.type,a.time,a.reason,a.symptom, a.notes, s.student_name " +
                               "FROM t_attendance a " +
                               "JOIN t_student s ON a.student_id = s.student_id " +
                               "WHERE a.student_id IN (" + studentIds.stream().map(id -> "?").collect(Collectors.joining(",")) + ") " +
                               "ORDER BY a.day DESC";

        try (Connection connection = getConnection();
             PreparedStatement psAttendance = connection.prepareStatement(attendanceSql)) {

            // プレースホルダーに学生IDを設定
            for (int i = 0; i < studentIds.size(); i++) {
                psAttendance.setString(i + 1, studentIds.get(i));
            }
            // クエリ実行と結果処理
            try (ResultSet rs = psAttendance.executeQuery()) {
                while (rs.next()) {
                    Attendance attendance = new Attendance();
                    attendance.setAttendanceId(rs.getString("attendance_id"));
                    attendance.setStudentId(rs.getString("student_id"));
                    attendance.setDay(rs.getDate("day"));
                    attendance.setType(rs.getString("type"));
                    attendance.setTime(rs.getTime("time"));
                    attendance.setReason(rs.getString("reason"));
                    attendance.setSymptom(rs.getString("symptom"));
                    attendance.setNotes(rs.getString("notes"));
                    attendance.setStudentName(rs.getString("student_name"));
                    attendanceList.add(attendance);
                    System.out.println(rs.getString("student_id"));
                }
            }
        }

        return attendanceList;
    }

//// 生徒の遅刻時間を取得するメソッド
//public Time getLateTime(String studentId) throws SQLException {
//    String query = "SELECT late_time FROM attendance WHERE student_id = ?";
//    try (PreparedStatement stmt = connection.prepareStatement(query)) {
//        stmt.setString(1, studentId);  // 生徒IDを設定
//        try (ResultSet rs = stmt.executeQuery()) {
//            if (rs.next()) {
//                return rs.getTime("late_time");  // 遅刻時間を取得
//            } else {
//                return null;  // 遅刻時間が見つからなかった場合
//            }
//        }
//    }
//    }
//}