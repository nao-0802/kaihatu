package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bean.Attendance;
import bean.Student;

public class AttendanceDao extends Dao {

    private static final String BASE_SQL = "SELECT attendance_id, student_id, day, type, time, reason, symptom, notes FROM t_attendance WHERE student_id = ?";

    // Nullまたは空の値にデフォルト値を設定するヘルパーメソッド
    private String getDefaultIfNull(String value, String defaultValue) {
        return (value == null || value.isEmpty()) ? defaultValue : value;
    }

    // ResultSetからAttendanceリストを生成
    private List<Attendance> postfilter(ResultSet rSet) throws SQLException {
        List<Attendance> list = new ArrayList<>();
        while (rSet.next()) {
            Attendance attendance = new Attendance();
            attendance.setAttendanceId(rSet.getString("attendance_id"));
            attendance.setStudentId(rSet.getString("student_id"));
            attendance.setDay(rSet.getDate("day"));
            attendance.setType(getDefaultIfNull(rSet.getString("type"), "不明"));
            attendance.setTime(rSet.getTime("time"));
            attendance.setReason(getDefaultIfNull(rSet.getString("reason"), "不明"));
            attendance.setSymptom(getDefaultIfNull(rSet.getString("symptom"), "なし"));
            attendance.setNotes(rSet.getString("notes"));

            // student_nameの設定
            attendance.setStudentName(rSet.getString("student_name"));

            list.add(attendance);
        }
        return list;
    }

    // 指定されたstudent_idのAttendanceを取得
    public List<Attendance> filter(String studentId) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(BASE_SQL)) {
            statement.setString(1, studentId);
            try (ResultSet rSet = statement.executeQuery()) {
                return postfilter(rSet);
            }
        }
    }

    // Attendanceを保存または更新
    public boolean save(Attendance attendance) throws Exception {
        String sql;
        if (getAttendance(attendance.getAttendanceId()) == null) {
            sql = "INSERT INTO t_attendance (attendance_id, student_id, day, type, time, reason, symptom, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE t_attendance SET student_id = ?, day = ?, type = ?, time = ?, reason = ?, symptom = ?, notes = ? WHERE attendance_id = ?";
        }

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, attendance.getAttendanceId());
            statement.setString(2, attendance.getStudentId());
            statement.setDate(3, attendance.getDay());
            statement.setString(4, attendance.getType());
            statement.setTime(5, attendance.getTime());
            statement.setString(6, attendance.getReason());
            statement.setString(7, attendance.getSymptom());
            statement.setString(8, attendance.getNotes());
            return statement.executeUpdate() > 0;
        }
    }

    // Attendanceを取得
    private Attendance getAttendance(String attendanceId) throws Exception {
        String sql = "SELECT * FROM t_attendance WHERE attendance_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, attendanceId);
            try (ResultSet rSet = statement.executeQuery()) {
                if (rSet.next()) {
                    Attendance attendance = new Attendance();
                    attendance.setAttendanceId(rSet.getString("attendance_id"));
                    attendance.setStudentId(rSet.getString("student_id"));
                    attendance.setDay(rSet.getDate("day"));
                    attendance.setType(rSet.getString("type"));
                    attendance.setTime(rSet.getTime("time"));
                    attendance.setReason(rSet.getString("reason"));
                    attendance.setSymptom(rSet.getString("symptom"));
                    attendance.setNotes(rSet.getString("notes"));
                    return attendance;
                }
            }
        }
        return null;
    }

    // Attendanceを削除
    public boolean delete(String attendanceId) throws Exception {
        String sql = "DELETE FROM t_attendance WHERE attendance_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, attendanceId);
            return statement.executeUpdate() > 0;
        }
    }

    // クラスIDに基づく出席情報の取得
    public List<Attendance> getAttendancesByTeacherId(String userId) throws Exception {
        List<Attendance> attendanceList = new ArrayList<>();
        String classIdSql = "SELECT CLASS_ID FROM t_teacher WHERE TEACHER_ID = ?";
        String attendanceSql = "SELECT a.attendance_id, a.student_id, a.day, a.type, a.time, a.notes, a.reason, a.symptom, s.student_name " +
                               "FROM t_attendance a JOIN t_student s ON a.student_id = s.student_id " +
                               "WHERE s.class_id = ? ORDER BY a.day DESC";

        try (Connection connection = getConnection();
             PreparedStatement psClassId = connection.prepareStatement(classIdSql)) {
            psClassId.setString(1, userId);

            try (ResultSet rsClassId = psClassId.executeQuery()) {
                if (rsClassId.next()) {
                    String classId = rsClassId.getString("CLASS_ID");
                    try (PreparedStatement psAttendance = connection.prepareStatement(attendanceSql)) {
                        psAttendance.setString(1, classId);
                        try (ResultSet rsAttendance = psAttendance.executeQuery()) {
                            attendanceList = postfilter(rsAttendance);
                        }
                    }
                }
            }
        }
        return attendanceList;
    }

    // 複数の学生IDに基づく出席情報の取得
    public List<Attendance> getAttendancesByStudents(List<Student> students) throws Exception {
        List<String> studentIds = students.stream()
                                          .map(Student::getStudentId)
                                          .collect(Collectors.toList());
        if (studentIds.isEmpty()) {
            return new ArrayList<>();
        }

        String inClause = studentIds.stream()
                                    .map(id -> "?")
                                    .collect(Collectors.joining(","));
        String sql = "SELECT a.attendance_id, a.student_id, a.day, a.type, a.time, a.reason, a.symptom, a.notes, s.student_name " +
                     "FROM t_attendance a JOIN t_student s ON a.student_id = s.student_id " +
                     "WHERE a.student_id IN (" + inClause + ") ORDER BY a.day DESC";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < studentIds.size(); i++) {
                ps.setString(i + 1, studentIds.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                return postfilter(rs);
            }
        }
    }

    // 生徒IDと日付に基づく出席情報の取得
    public List<Attendance> getAttendanceByStudentAndDate(String studentId, Date date) throws Exception {
        String sql = "SELECT a.attendance_id, a.student_id, a.day, a.type, a.time, a.reason, a.symptom, a.notes, s.student_name " +
                     "FROM t_attendance a JOIN t_student s ON a.student_id = s.student_id " +
                     "WHERE a.student_id = ? AND a.day = ? ORDER BY a.day DESC";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ps.setDate(2, date);
            try (ResultSet rs = ps.executeQuery()) {
                return postfilter(rs);
            }
        }
    }


    public List<Attendance> getAttendanceByStudentsAndDate(List<String> studentIds, Date date, String studentName) throws Exception {
        if (studentIds == null || studentIds.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.attendance_id, a.student_id, a.day, a.type, a.time, a.reason, a.symptom, a.notes, s.student_name ")
           .append("FROM t_attendance a ")
           .append("JOIN t_student s ON a.student_id = s.student_id ")
           .append("WHERE a.student_id IN (");

        // 生徒IDの設定
        String inClause = studentIds.stream()
                                    .map(id -> "?")
                                    .collect(Collectors.joining(","));
        sql.append(inClause).append(") ");

        // 日付の設定
        sql.append("AND a.day = ? ");

        // 生徒名の設定（もし指定されていれば）
        if (studentName != null && !studentName.isEmpty()) {
            sql.append("AND s.student_name LIKE ? ");
        }

        sql.append("ORDER BY a.day DESC");

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            // 生徒IDの設定
            for (int i = 0; i < studentIds.size(); i++) {
                ps.setString(i + 1, studentIds.get(i));
            }

            // 日付の設定
            ps.setDate(studentIds.size() + 1, date);

            // 生徒名の設定（もし指定されていれば）
            if (studentName != null && !studentName.isEmpty()) {
                ps.setString(studentIds.size() + 2, "%" + studentName + "%");
            }

            try (ResultSet rs = ps.executeQuery()) {
                return postfilter(rs);
            }
        }
    }
}