package bean;

public class AnualRecord implements java.io.Serializable{

	private String anual_record_id;
	private String class_id;
	private String text;

	public String getAnualRecordId(){
		return anual_record_id;
	}
	public String getClassId(){
		return class_id;
	}
	public String getText(){
		return text;
	}
	public void setAnualRecordId(String anual_record_id){
		this.anual_record_id=anual_record_id;
	}
	public void setClassId(String class_id){
		this.class_id=class_id;
	}
	public void setText(String text){
		this.text=text;
	}
}
