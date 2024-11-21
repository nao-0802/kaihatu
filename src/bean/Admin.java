package bean;

public class Admin implements java.io.Serializable{

	private String admin_id;
	private String password;

	public String getAdminId(){
		return admin_id;
	}
	public String getPassword(){
		return password;
	}
	public void setAdminId(String admin_id){
		this.admin_id=admin_id;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public boolean isAuthenticated() {
        // 認証状態を判定するロジックを実装
        return true; // 例
    }

}
