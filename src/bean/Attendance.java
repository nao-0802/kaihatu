package bean;

import java.sql.Date;
import java.sql.Time;

public class Attendance implements java.io.Serializable {

    private String attendance_id;
    private String student_id;
    private Date day;
    private int type;
    private int reason;
    private String notes;
    private String student_name;
    private Time tardiness_time;
    private Time early_time;
    private String symptom;

    public String getAttendanceId() {
        return attendance_id;
    }

    public String getStudentId() {
        return student_id;
    }

    public Date getDay() {
        return day;
    }

    public int getType() {
        return type;
    }

    public String getNotes() {
        return notes;
    }

    public String getStudentName() {
        return student_name;
    }

    public Time getTardinessTime() {
        return tardiness_time;
    }

    public Time getEarlyTime() {
        return early_time;
    }

    public String getSymptom() {
        return symptom;
    }
    public int getReason() {
        return reason;
    }


    public void setAttendanceId(String attendance_id) {
        this.attendance_id = attendance_id;
    }

    public void setStudentId(String student_id) {
        this.student_id = student_id;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setStudentName(String student_name) {
        this.student_name = student_name;
    }

    public void setTardinessTime(Time tardiness_time) {
        this.tardiness_time = tardiness_time;
    }

    public void setEarlyTime(Time early_time) {
        this.early_time = early_time;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
    public void setReason(int reason) {
        this.reason = reason;
    }

}
