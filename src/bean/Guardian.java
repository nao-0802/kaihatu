package bean;

public class Guardian implements java.io.Serializable{

	private String guardian_id;
	private String guardian_name;
	private String password;
	private String student_id;
	private String student_name;

	public String getGuardianId(){
		return guardian_id;
	}
	public String getGuardianName(){
		return guardian_name;
	}
	public String getPassword(){
		return password;
	}

	public String getStudentId(){
		return student_id;
	}

	public String getStudentName(){
		return student_name;
	}

	public void setGuardianId(String guardian_id){
		this.guardian_id=guardian_id;
	}
	public void setGuardianName(String guardian_name){
		this.guardian_name=guardian_name;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public void setStudentId(String student_id){
		this.student_id=student_id;
	}

	public void setStudentName(String student_name){
		this.student_name=student_name;
	}
	




}