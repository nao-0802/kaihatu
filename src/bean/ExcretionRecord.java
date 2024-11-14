package bean;

import java.sql.Date;

public class ExcretionRecord implements java.io.Serializable{

	private String excretion_id;
	private String student_id;
	private Date day;
	private Date time;
	private int type;
	private String excretion_detail;

	public String getExcretionId(){
		return excretion_id;
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
	public String getExcretionDetail(){
		return excretion_detail;
	}
	public void setExcretionId(String excretion_id){
		this.excretion_id=excretion_id;
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
	public void setExcretionDetail(String excretion_detail){
		this.excretion_detail=excretion_detail;
	}
}
