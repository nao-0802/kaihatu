package bean;

public class Class implements java.io.Serializable{

	private String class_id;
	private String class_name;

	public String getClassId(){
		return class_id;
	}
	public String getClassName(){
		return class_name;
	}
	public void setClassId(String class_id){
		this.class_id=class_id;
	}
	public void setClassName(String class_name){
		this.class_name=class_name;
	}
}
