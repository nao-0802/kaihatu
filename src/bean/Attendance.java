package bean;

import java.sql.Date;
import java.sql.Time;

public class Attendance implements java.io.Serializable {

    private String attendance_id;
    private String student_id;
    private Date day;
    private String type;  // int型からString型に変更
    private String reason;
    private String notes;
    private String student_name;
    private Time time;
    private String symptom;

    // 新しく追加したフィールド
    private String lateTime;  // 遅刻時間
    private String earlyLeaveTime;  // 早退時間

    // 既存のgetterとsetterメソッド

    public String getAttendanceId() {
        return attendance_id;
    }

    public String getStudentId() {
        return student_id;
    }

    public Date getDay() {
        return day;
    }

    public String getType() {  // 返り値をintからStringに変更
        return type;
    }

    public String getNotes() {
        return notes;
    }

    public String getStudentName() {
        return student_name;
    }

    public Time getTime() {
        return time;
    }

    public String getSymptom() {
        return symptom;
    }

    public String getReason() {
        return reason;
    }

    // 新たに追加したgetterとsetter

    public String getLateTime() {
        return lateTime;
    }

    public void setLateTime(String lateTime) {
        this.lateTime = lateTime;
    }

    public String getEarlyLeaveTime() {
        return earlyLeaveTime;
    }

    public void setEarlyLeaveTime(String earlyLeaveTime) {
        this.earlyLeaveTime = earlyLeaveTime;
    }

    // 既存のsetterメソッド

    public void setAttendanceId(String attendance_id) {
        this.attendance_id = attendance_id;
    }

    public void setStudentId(String student_id) {
        this.student_id = student_id;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public void setType(String type) {  // 引数の型をStringに変更
        this.type = type;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setStudentName(String student_name) {
        this.student_name = student_name;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
