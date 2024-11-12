package bean;

public class Student implements java.io.Serializable{

	private String student_id;
	private String student_name;
	private String password;
	private String class_id;
	private String guardian_id;
	private int flag;
	private String student_record_id;

	public String getStudentId(){
		return student_id;
	}
	public String getStudentName(){
		return student_name;
	}
	public String getPassword(){
		return password;
	}
	public String getClassId(){
		return class_id;
	}
	public String getGuardianId(){
		return guardian_id;
	}
	public int getFlag(){
		return flag;
	}
	public String getStudentRecordId(){
		return student_record_id;
	}
	public void setStudentId(String student_id){
		this.student_id=student_id;
	}
	public void setStudentName(String student_name){
		this.student_name=student_name;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public void setClassId(String class_id){
		this.class_id=class_id;
	}
	public void setGuardianId(String guardian_id){
		this.guardian_id=guardian_id;
	}
	public void setFlag(int flag){
		this.flag=flag;
	}
	public void setStudentRecordId(String student_record_id){
		this.student_record_id=student_record_id;
	}
}
