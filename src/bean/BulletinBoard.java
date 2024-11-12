package bean;

public class BulletinBoard implements java.io.Serializable{

	private String post_id;
	private String title;
	private String content;
	private String teacher_id;

	public String getPostId(){
		return post_id;
	}
	public String getTitle(){
		return title;
	}
	public String getContent(){
		return content;
	}
	public String TeacherId(){
		return teacher_id;
	}
	public void setPostId(String post_id){
		this.post_id=post_id;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public void setContent(String content){
		this.content=content;
	}
	public void setTeacherId(String teacher_id){
		this.teacher_id=teacher_id;
	}
}
