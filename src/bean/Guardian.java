package bean;

public class Guardian implements java.io.Serializable{

	private String guardian_id;
	private String guardian_name;
	private String password;
	private String email;

	public String getGuardianId(){
		return guardian_id;
	}
	public String getGuardianName(){
		return guardian_name;
	}
	public String getPassword(){
		return password;
	}
	public String getEmail(){
		return email;
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
	public void setEmail(String email){
		this.email=email;
	}

}
