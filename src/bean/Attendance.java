package bean;

import java.sql.Date;

public class Attendance implements java.io.Serializable{

	private String attendance_id;
	private String student_record_id;
	private Date day;
	private int status;
	private String notes;

	public String getAttendanceId(){
		return attendance_id;
	}
	public String getStudentRecordId(){
		return student_record_id;
	}
	public Date getDay(){
		return day;
	}
	public int getStatus(){
		return status;
	}
	public String getNotes(){
		return notes;
	}
	public void setAttendanceId(String attendance_id){
		this.attendance_id=attendance_id;
	}
	public void setStudentRecordId(String student_record_id){
		this.student_record_id=student_record_id;
	}
	public void setDay(Date day){
		this.day=day;
	}
	public void setStatus(int status){
		this.status=status;
	}
	public void setNotes(String notes){
		this.notes=notes;
	}
}
