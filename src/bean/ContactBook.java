package bean;

import java.sql.Date;

public class ContactBook implements java.io.Serializable{

	private String contact_book_id;
	private String teacher_id;
	private String guardian_id	;
	private Date day;
	private String contact_details;
	private boolean check;

	public String getContactBookId(){
		return contact_book_id;
	}
	public String getTeacherId(){
		return teacher_id;
	}
	public String getGuardianId(){
		return guardian_id;
	}
	public Date getDay(){
		return day;
	}
	public String getContactDetails(){
		return contact_details;
	}
	public boolean getCheck(){
		return check;
	}
	public void setContactBookId(String contact_book_id){
		this.contact_book_id=contact_book_id;
	}
	public void setTeacherId(String teacher_id){
		this.teacher_id=teacher_id;
	}
	public void setGuardinaId(String guardian_id){
		this.guardian_id=guardian_id;
	}
	public void setDay(Date day){
		this.day=day;
	}
	public void setContactDetails(String contact_details){
		this.contact_details=contact_details;
	}
	public void setCheck(boolean check){
		this.check=check;
	}
}
