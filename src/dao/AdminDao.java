package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Admin;
import tool.DbUtil;

public class AdminDao extends Dao {

    // Adminをadmin_idで取得
    public Admin get(String admin_id) throws Exception {
        Admin admin = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM t_admin WHERE admin_id = ?");
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
    public boolean registerAdmin(String adminId, String password) {
        try (Connection con = DbUtil.getConnection()) {
            String sql = "INSERT INTO t_admin (admin_id, password) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, adminId);
            stmt.setString(2, password); // 必要ならハッシュ化
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Admin login(String admin_id, String password) throws Exception {
		// 管理者クラスのインスタンスを取得
		Admin admin = get(admin_id);
		// 管理者がnullまたはパスワードが一致しない場合
		if (admin == null || !admin.getPassword().equals(password)) {
			return null;
		}
		return admin;
	}

}
