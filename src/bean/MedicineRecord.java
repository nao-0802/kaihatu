package bean;

import java.sql.Date;
import java.sql.Time;

public class MedicineRecord implements java.io.Serializable{

	private String medicine_id;
	private String student_id;
	private Date day;
	private Time time;
	private int medicine;

	public String getMedicineId(){
		return medicine_id;
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

	public int getMedicine(){
		return medicine;
	}
	public void setMedicineId(String medicine_id){
		this.medicine_id=medicine_id;
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
	public void setMedicine(int medicine){
		this.medicine=medicine;
	}
	public void setTime(Time time) {
	    this.time = time;
	}

}
