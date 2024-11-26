package bean;

import java.sql.Date;
import java.sql.Time;

public class SleepRecord implements java.io.Serializable{

	private String sleep_id;
	private String student_id;
	private Date day;
	private Time time;
	private int sleep;

	public String getSleepId(){
		return sleep_id;
	}
	public String getStudentId(){
		return student_id;
	}
	public Date getDay(){
		return day;
	}
	public Time getTime(){
		return time;
	}

	public int getSleep(){
		return sleep;
	}
	public void setSleepId(String sleep_id){
		this.sleep_id=sleep_id;
	}
	public void setStudentId(String student_id){
		this.student_id=student_id;
	}
	public void setDay(Date day){
		this.day=day;
	}
//	public void setTime(Date time){
//		this.time=time;
//	}
	public void setSleep(int sleep){
		this.sleep=sleep;
	}
	public void setTime(Time time) {
	    this.time = time;
	}

}
