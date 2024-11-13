package bean;

import java.sql.Date;
import java.sql.Time;

public class Appointment implements java.io.Serializable{

	private String appointment_id;
	private String teacher_id;
	private String guardian_id;
	private Date appointment_date;
	private Time appointment_time;
	private boolean flag;

	public String getAppointmentId(){
		return appointment_id;
	}
	public String getTeacherId(){
		return teacher_id;
	}
	public String getGuardianId(){
		return guardian_id;
	}
	public Date getAppointmentDate(){
		return appointment_date;
	}
	public Time getAppointmentTime(){
		return appointment_time;
	}
	public boolean getFlag(){
		return flag;
	}
	public void setAppointmentId(String appointment_id){
		this.appointment_id=appointment_id;
	}
	public void setTeacherId(String teacher_id){
		this.teacher_id=teacher_id;
	}
	public void setGuardianId(String guardian_id){
		this.guardian_id=guardian_id;
	}
	public void setAppointmentDate(Date appointment_date){
		this.appointment_date=appointment_date;
	}
	public void setAppointmentTime(Time appointment_time){
		this.appointment_time=appointment_time;
	}
	public void setFlag(boolean flag){
		this.flag=flag;
	}
}
