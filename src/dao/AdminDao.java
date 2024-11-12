package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Admin;

public class AdminDao extends Dao {

    // Adminをadmin_idで取得
    public Admin get(String admin_id) throws Exception {
        Admin admin = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM admin WHERE admin_id = ?");
            statement.setString(1, admin_id);
            ResultSet rSet = statement.executeQuery();
            if (rSet.next()) {
                admin = new Admin();
                admin.setAdminId(rSet.getString("admin_id"));
                admin.setPassword(rSet.getString("password"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return admin;
    }
    public Admin login(String admin_id, String password) throws Exception {
		// 教員クラスのインスタンスを取得
		Admin admin = get(admin_id);
		// 教員がnullまたはパスワードが一致しない場合
		if (admin == null || !admin.getPassword().equals(password)) {
			return null;
		}
		return admin;
	}

}
