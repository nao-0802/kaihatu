package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tool.DbUtil;

public class MasterAdminDao {
    public boolean validateMasterAccount(String masterId, String masterPassword) {
        try (Connection con = DbUtil.getConnection()) {
            String sql = "SELECT password FROM t_master_admin WHERE master_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, masterId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                // パスワードを比較
                return storedPassword.equals(masterPassword); // 必要ならハッシュ化対応
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
