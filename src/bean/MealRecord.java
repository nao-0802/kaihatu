package bean;

import java.sql.Date;

public class MealRecord implements java.io.Serializable{

	private String meal_id;
	private String student_id;
	private Date day;
	private Date time;
	private int type;
	private int meal_amount;

	public String getMealId(){
		return meal_id;
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
	public int getMealAmount(){
		return meal_amount;
	}
	public void setMealId(String meal_id){
		this.meal_id=meal_id;
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
	public void setMealAmount(int meal_amount){
		this.meal_amount=meal_amount;
	}
}
