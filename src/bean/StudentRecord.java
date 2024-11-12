package bean;

import java.sql.Date;

public class StudentRecord implements java.io.Serializable{

	private String student_record_id;
	private String name;
	private String class_id;
	private String guardian_id;
	private Date birthdate;
	private String allergy;
	private String features;
	private String attendance_id;
	private String anual_record_id;

	public String getStudentRecordId(){
		return student_record_id;
	}
	public String getName(){
		return name;
	}
	public String getClassId(){
		return class_id;
	}
	public String getGuardianId(){
		return guardian_id;
	}
	public Date getBirthdate(){
		return birthdate;
	}
	public String getAllergy(){
		return allergy;
	}
	public String getFeatures(){
		return features;
	}
	public String getAttendanceId(){
		return attendance_id;
	}
	public String getAnualRecordId(){
		return anual_record_id;
	}
	public void setStudentRecordId(String student_record_id){
		this.student_record_id=student_record_id;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setClassId(String class_id){
		this.class_id=class_id;
	}
	public void setGuardianId(String guardian_id){
		this.guardian_id=guardian_id;
	}
	public void setBirthdate(Date birthdate){
		this.birthdate=birthdate;
	}
	public void setAllergy(String allergy){
		this.allergy=allergy;
	}
	public void setFeatures(String features){
		this.features=features;
	}
	public void setAttendanceId(String attendance_id){
		this.attendance_id=attendance_id;
	}
	public void setAnualRecordId(String anual_record_id){
		this.anual_record_id=anual_record_id;
	}
}
