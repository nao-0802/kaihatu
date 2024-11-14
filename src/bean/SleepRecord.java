package bean;

import java.sql.Date;

public class SleepRecord implements java.io.Serializable{

	private String sleep_id;
	private String student_id;
	private Date day;
	private Date time;
	private int type;

	public String getSleepId(){
		return sleep_id;
	}
	public String getStudentId(){
		return student_id;
	}
	public Date getDay(){
		return day;
	}
	public Date getTime(){
		return time;
	}
	public int getType(){
		return type;
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
	public void setTime(Date time){
		this.time=time;
	}
	public void setType(int type){
		this.type=type;
	}
}
