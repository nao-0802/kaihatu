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
    private String baseSql = "SELECT guardian_id, guardian_name, password, email FROM guardians WHERE guardian_id = ?";

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
                        "INSERT INTO guardians (guardian_id, guardian_name, password, email) VALUES (?, ?, ?, ?)"
                );
                statement.setString(1, guardian.getGuardianId());
                statement.setString(2, guardian.getGuardianName());
                statement.setString(3, guardian.getPassword());
                statement.setString(4, guardian.getEmail());
            } else {
                // 既存のGuardianの更新
                statement = connection.prepareStatement(
                        "UPDATE guardians SET guardian_name = ?, password = ?, email = ? WHERE guardian_id = ?"
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
            statement = connection.prepareStatement("DELETE FROM guardians WHERE guardian_id = ?");
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
}
