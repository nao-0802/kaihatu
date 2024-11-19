package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Guardian;

public class GuardianDao extends Dao {
    // SQLクエリ: guardian_idに基づいてレコードを取得
    private String baseSql = "SELECT guardian_id, guardian_name, password, email FROM t_guardian WHERE guardian_id = ?";

    // ResultSetからGuardianリストを生成するメソッド
    private List<Guardian> postfilter(ResultSet rSet) throws Exception {
        List<Guardian> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Guardian guardian = new Guardian();
                guardian.setGuardianId(rSet.getString("guardian_id"));
                guardian.setGuardianName(rSet.getString("guardian_name"));
                guardian.setPassword(rSet.getString("password"));
                guardian.setEmail(rSet.getString("email"));
                list.add(guardian);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    public List<Guardian> search(String keyword) throws Exception {
        List<Guardian> list = new ArrayList<>();
        String sql = "SELECT guardian_id, guardian_name FROM t_guardian WHERE guardian_name LIKE ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + keyword + "%");
            try (ResultSet rSet = statement.executeQuery()) {
                while (rSet.next()) {
                    Guardian guardian = new Guardian();
                    guardian.setGuardianId(rSet.getString("guardian_id"));
                    guardian.setGuardianName(rSet.getString("guardian_name"));
                    list.add(guardian);
                }
            }
        }
        return list;
    }


    // 指定されたguardian_idのGuardianを取得するメソッド
    public List<Guardian> filter(String guardianId) throws Exception {
        List<Guardian> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, guardianId);
            rSet = statement.executeQuery();
            list = postfilter(rSet);
        } catch (Exception e) {
            throw e;
        } finally {
            // PreparedStatementを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // Connectionを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return list;
    }

    public List<Guardian> getAllGuardians() throws Exception {
        List<Guardian> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT guardian_id, guardian_name FROM t_guardian");
            rSet = statement.executeQuery();
            while (rSet.next()) {
                Guardian guardian = new Guardian();
                guardian.setGuardianId(rSet.getString("guardian_id"));
                guardian.setGuardianName(rSet.getString("guardian_name"));
                list.add(guardian);
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

        return list;
    }

    // Guardianを保存または更新するメソッド
    public boolean save(Guardian guardian) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存のGuardianを検索
            Guardian existingGuardian = getGuardian(guardian.getGuardianId());
            if (existingGuardian == null) {
                // 新しいGuardianの場合
                statement = connection.prepareStatement(
                        "INSERT INTO t_guardian (guardian_id, guardian_name, password, email) VALUES (?, ?, ?, ?)"
                );
                statement.setString(1, guardian.getGuardianId());
                statement.setString(2, guardian.getGuardianName());
                statement.setString(3, guardian.getPassword());
                statement.setString(4, guardian.getEmail());
            } else {
                // 既存のGuardianの更新
                statement = connection.prepareStatement(
                        "UPDATE t_guardian SET guardian_name = ?, password = ?, email = ? WHERE guardian_id = ?"
                );
                statement.setString(1, guardian.getGuardianName());
                statement.setString(2, guardian.getPassword());
                statement.setString(3, guardian.getEmail());
                statement.setString(4, guardian.getGuardianId());
            }
            count = statement.executeUpdate();
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

        return count > 0;
    }

    // guardian_idでGuardianを取得するメソッド
    private Guardian getGuardian(String guardianId) throws Exception {
        Guardian guardian = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, guardianId);
            rSet = statement.executeQuery();
            if (rSet.next()) {
                guardian = new Guardian();
                guardian.setGuardianId(rSet.getString("guardian_id"));
                guardian.setGuardianName(rSet.getString("guardian_name"));
                guardian.setPassword(rSet.getString("password"));
                guardian.setEmail(rSet.getString("email"));
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

        return guardian;
    }

    // Guardianを削除するメソッド
    public boolean delete(String guardianId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM t_guardian WHERE guardian_id = ?");
            statement.setString(1, guardianId);
            statement.executeUpdate();
            result = true;
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

        return result;
    }

    public Guardian get(String guardian_id) throws Exception {
        Guardian guardian = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM t_guardian WHERE guardian_id = ?");
            statement.setString(1, guardian_id);
            ResultSet rSet = statement.executeQuery();
            if (rSet.next()) {
                guardian = new Guardian();
                guardian.setGuardianId(rSet.getString("guardian_id"));
                guardian.setPassword(rSet.getString("password"));
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
        return guardian;
    }

    public Guardian login(String guardian_id, String password) throws Exception {
		// 保護者クラスのインスタンスを取得
		Guardian guardian = get(guardian_id);
		// 管理者がnullまたはパスワードが一致しない場合
		if (guardian == null || !guardian.getPassword().equals(password)) {
			return null;
		}
		return guardian;
	}
}
