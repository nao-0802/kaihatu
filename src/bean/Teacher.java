package bean;

public class Teacher implements java.io.Serializable{

	private String teacher_id;
	private String teacher_name;
	private String password;
	private String class_id;
	private int flag;
	private String className; // クラス名


	public String getTeacherId(){
		return teacher_id;
	}
	public String getTeacherName(){
		return teacher_name;
	}
	public String getPassword(){
		return password;
	}
	public String getClassId(){
		return class_id;
	}
	public int getFlag(){
		return flag;
	}
	public void setTeacherId(String teacher_id){
		this.teacher_id=teacher_id;
	}
	public void setTeacherName(String teacher_name){
		this.teacher_name=teacher_name;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public void setClassId(String class_id){
		this.class_id=class_id;
	}
	public void setFlag(int flag){
		this.flag=flag;
	}
	 public String getClassName() {
	        return className;
	}
	public void setClassName(String className) {
        this.className = className;
    }
}
