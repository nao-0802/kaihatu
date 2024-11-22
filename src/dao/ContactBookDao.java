package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ContactBook;

public class ContactBookDao extends Dao {
    // 既存のSQLクエリ
    private String baseSql = "SELECT contact_book_id, teacher_id, guardian_id, day, contact_details, check FROM t_contact_book WHERE teacher_id = ?";

    // 新しいSQLクエリ: guardian_id と date に基づいてレコードを取得
    private String findByGuardianIdAndDateSql = "SELECT contact_book_id, teacher_id, guardian_id, day, contact_details, check FROM t_contact_book WHERE guardian_id = ? AND day = ?";

    // ResultSetからContactBookリストを生成するメソッド
    private List<ContactBook> postfilter(ResultSet rSet) throws Exception {
        List<ContactBook> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                ContactBook contactBook = new ContactBook();
                contactBook.setContactBookId(rSet.getString("contact_book_id"));
                contactBook.setTeacherId(rSet.getString("teacher_id"));
                contactBook.setGuardinaId(rSet.getString("guardian_id"));
                contactBook.setDay(rSet.getDate("day"));
                contactBook.setContactDetails(rSet.getString("contact_details"));
                contactBook.setCheck(rSet.getBoolean("check"));
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
                        "INSERT INTO t_contact_book (contact_book_id, teacher_id, guardian_id, day, contact_details, check) VALUES (?, ?, ?, ?, ?, ?)"
                );
                statement.setString(1, contactBook.getContactBookId());
                statement.setString(2, contactBook.getTeacherId());
                statement.setString(3, contactBook.getGuardianId());
                statement.setDate(4, contactBook.getDay());
                statement.setString(5, contactBook.getContactDetails());
                statement.setBoolean(6, contactBook.getCheck());
            } else {
                // 既存のContactBookの更新
                statement = connection.prepareStatement(
                        "UPDATE t_contact_book SET teacher_id = ?, guardian_id = ?, day = ?, contact_details = ?, check = ? WHERE contact_book_id = ?"
                );
                statement.setString(1, contactBook.getTeacherId());
                statement.setString(2, contactBook.getGuardianId());
                statement.setDate(3, contactBook.getDay());
                statement.setString(4, contactBook.getContactDetails());
                statement.setBoolean(5, contactBook.getCheck());
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
                contactBook.setGuardinaId(rSet.getString("guardian_id"));
                contactBook.setDay(rSet.getDate("day"));
                contactBook.setContactDetails(rSet.getString("contact_details"));
                contactBook.setCheck(rSet.getBoolean("check"));
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
}



