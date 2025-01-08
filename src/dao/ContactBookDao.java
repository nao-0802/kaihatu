package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.ContactBook;

public class ContactBookDao extends Dao {
    // 既存のSQLクエリ
    private String baseSql = "SELECT contact_book_id, teacher_id, guardian_id, day, contact_details, contact_check FROM t_contact_book WHERE teacher_id = ?";

    // 新しいSQLクエリ: guardian_id と date に基づいてレコードを取得
    public String findByGuardianIdAndDateSql = "SELECT contact_book_id, teacher_id, guardian_id, day, contact_details, contact_check FROM t_contact_book WHERE guardian_id = ? AND day = ?";

    // ResultSetからContactBookリストを生成するメソッド
    private List<ContactBook> postfilter(ResultSet rSet) throws Exception {
        List<ContactBook> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                ContactBook contactBook = new ContactBook();
                contactBook.setContactBookId(rSet.getString("contact_book_id"));
                contactBook.setTeacherId(rSet.getString("teacher_id"));
                contactBook.setGuardianId(rSet.getString("guardian_id"));
                contactBook.setDay(rSet.getDate("day"));
                contactBook.setContactDetails(rSet.getString("contact_details"));
                contactBook.setContactCheck(rSet.getBoolean("contact_check"));
                list.add(contactBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    // 指定されたteacher_idのContactBookを取得するメソッド
    public List<ContactBook> filter(String teacherId) throws Exception {
        List<ContactBook> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, teacherId);
            rSet = statement.executeQuery();
            list = postfilter(rSet);
        } catch (Exception e) {
            throw e;
        } finally {
            // 例外処理とリソースの解放
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

    // guardian_id と date に基づいて ContactBook を検索するメソッド
    public List<ContactBook> findByGuardianIdAndDate(String guardianId, String selectedDate) throws Exception {
        List<ContactBook> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(findByGuardianIdAndDateSql);
            statement.setString(1, guardianId);
            statement.setString(2, selectedDate);
            rSet = statement.executeQuery();
            list = postfilter(rSet); // ResultSetからContactBookリストを作成
        } catch (Exception e) {
            throw e;
        } finally {
            // 例外処理とリソースの解放
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

    // ContactBookを保存または更新するメソッド
    public boolean save(ContactBook contactBook) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存のContactBookを検索
            ContactBook existingContactBook = getContactBook(contactBook.getContactBookId());
            if (existingContactBook == null) {
                // 新しいContactBookの場合
                statement = connection.prepareStatement(
                        "INSERT INTO t_contact_book (contact_book_id, teacher_id, guardian_id, day, contact_details, contact_check) VALUES (?, ?, ?, ?, ?, ?)"
                );
                statement.setString(1, contactBook.getContactBookId());
                statement.setString(2, contactBook.getTeacherId());
                statement.setString(3, contactBook.getGuardianId());
                statement.setDate(4, contactBook.getDay());
                statement.setString(5, contactBook.getContactDetails());
                statement.setBoolean(6, contactBook.getContactCheck());
            } else {
                // 既存のContactBookの更新
                statement = connection.prepareStatement(
                        "UPDATE t_contact_book SET teacher_id = ?, guardian_id = ?, day = ?, contact_details = ?, contact_check = ? WHERE contact_book_id = ?"
                );
                statement.setString(1, contactBook.getTeacherId());
                statement.setString(2, contactBook.getGuardianId());
                statement.setDate(3, contactBook.getDay());
                statement.setString(4, contactBook.getContactDetails());
                statement.setBoolean(5, contactBook.getContactCheck());
                statement.setString(6, contactBook.getContactBookId());
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

 // 連絡帳を保存または更新するメソッド
    public boolean saveNotebook(ContactBook notebook) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int count = 0;

        try {
            // データベース接続
            connection = getConnection();

            // 既存の連絡帳を検索
            boolean exists = notebookExists(notebook.getContactBookId(), connection);

            if (!exists) {
                // 新規作成の場合
                statement = connection.prepareStatement(
                        "INSERT INTO t_contact_book "
                                + "(contact_book_id, teacher_id, guardian_id, day, contact_details, contact_check) "
                                + "VALUES (?, ?, ?, ?, ?, ?)"
                );
                statement.setString(1, notebook.getContactBookId());
                statement.setString(2, notebook.getTeacherId());
                statement.setString(3, notebook.getGuardianId());
                statement.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // 現在時刻
                statement.setString(5, notebook.getContactDetails());
                statement.setBoolean(6, false); // 既読フラグは初期値として未読
            } else {
                // 更新の場合
                statement = connection.prepareStatement(
                        "UPDATE t_contact_book SET "
                                + "teacher_id = ?, guardian_id = ?, day = ?, contact_details = ?, contact_check = ? "
                                + "WHERE contact_book_id = ?"
                );
                statement.setString(1, notebook.getTeacherId());
                statement.setString(2, notebook.getGuardianId());
                statement.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // 現在時刻
                statement.setString(4, notebook.getContactDetails());
                statement.setBoolean(5, notebook.getContactCheck());
                statement.setString(6, notebook.getContactBookId());
            }
            count = statement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            // リソースを解放
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

    // 指定された連絡帳IDが存在するか確認するメソッド
    private boolean notebookExists(String contactBookId, Connection connection) throws SQLException {
        String sql = "SELECT 1 FROM t_contact_book WHERE contact_book_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, contactBookId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }


    // contact_book_idでContactBookを取得するメソッド
    private ContactBook getContactBook(String contactBookId) throws Exception {
        ContactBook contactBook = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, contactBookId);
            rSet = statement.executeQuery();
            if (rSet.next()) {
                contactBook = new ContactBook();
                contactBook.setContactBookId(rSet.getString("contact_book_id"));
                contactBook.setTeacherId(rSet.getString("teacher_id"));
                contactBook.setGuardianId(rSet.getString("guardian_id"));
                contactBook.setDay(rSet.getDate("day"));
                contactBook.setContactDetails(rSet.getString("contact_details"));
                contactBook.setContactCheck(rSet.getBoolean("contact_check"));
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

        return contactBook;
    }

    // ContactBookを削除するメソッド
    public boolean delete(String contactBookId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM t_contact_book WHERE contact_book_id = ?");
            statement.setString(1, contactBookId);
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


public List<ContactBook> findByGuardianId(String guardianId) throws Exception {
    List<ContactBook> list = new ArrayList<>();
    Connection connection = getConnection();
    PreparedStatement statement = null;
    ResultSet rSet = null;

    try {
        String sql = "SELECT contact_book_id, teacher_id, guardian_id, day, contact_details, contact_check FROM t_contact_book WHERE guardian_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, guardianId);
        rSet = statement.executeQuery();

        // ResultSetを基にリストを作成
        while (rSet.next()) {
            ContactBook contactBook = new ContactBook();
            contactBook.setContactBookId(rSet.getString("contact_book_id"));
            contactBook.setTeacherId(rSet.getString("teacher_id"));
            contactBook.setGuardianId(rSet.getString("guardian_id"));
            contactBook.setDay(rSet.getDate("day"));
            contactBook.setContactDetails(rSet.getString("contact_details"));
            contactBook.setContactCheck(rSet.getBoolean("contact_check"));
            list.add(contactBook);
        }
    } catch (Exception e) {
        throw e;
    } finally {
        // リソースの解放
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    return list;
	}

//保護者IDに基づく連絡帳リストを取得
public List<ContactBook> getByGuardianId(String guardianId) throws Exception {
    List<ContactBook> list = new ArrayList<>();
    Connection con = getConnection();

    String sql = "SELECT * FROM t_contact_book WHERE guardian_id = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, guardianId);
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        ContactBook cb = new ContactBook();
        cb.setContactBookId(rs.getString("contact_book_id"));
        cb.setTeacherId(rs.getString("teacher_id"));
        cb.setGuardianId(rs.getString("guardian_id"));
        cb.setDay(rs.getDate("day"));
        cb.setContactDetails(rs.getString("contact_details"));
        cb.setContactCheck(rs.getBoolean("contact_check"));
        list.add(cb);
    }

    rs.close();
    ps.close();
    con.close();
    return list;
}

// 連絡帳IDに基づく連絡帳の詳細を取得
public ContactBook getById(String contactBookId) throws Exception {
    ContactBook cb = null;
    Connection con = getConnection();

    String sql = "SELECT * FROM t_contact_book WHERE contact_book_id = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, contactBookId);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        cb = new ContactBook();
        cb.setContactBookId(rs.getString("contact_book_id"));
        cb.setTeacherId(rs.getString("teacher_id"));
        cb.setGuardianId(rs.getString("guardian_id"));
        cb.setDay(rs.getDate("day"));
        cb.setContactDetails(rs.getString("contact_details"));
        cb.setContactCheck(rs.getBoolean("contact_check"));
    }

    rs.close();
    ps.close();
    con.close();
    return cb;
}
}




