package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BulletinBoard;

public class BulletinBoardDao extends Dao {
    // SQLクエリ: teacher_idに基づいてレコードを取得
    private String baseSql = "SELECT post_id, title, content, teacher_id FROM t_bulletin_board WHERE teacher_id = ?";

    // ResultSetからBulletinBoardリストを生成するメソッド
    private List<BulletinBoard> postfilter(ResultSet rSet) throws Exception {
        List<BulletinBoard> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                BulletinBoard bulletin = new BulletinBoard();
                bulletin.setPostId(rSet.getString("post_id"));
                bulletin.setTitle(rSet.getString("title"));
                bulletin.setContent(rSet.getString("content"));
                bulletin.setTeacherId(rSet.getString("teacher_id"));
                list.add(bulletin);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    // 指定されたteacher_idのBulletinBoardを取得するメソッド
    public List<BulletinBoard> filter(String teacherId) throws Exception {
        List<BulletinBoard> list = new ArrayList<>();
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

    // BulletinBoardを新規作成するメソッド
    public boolean create(BulletinBoard bulletinBoard) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 新規BulletinBoardを作成
            statement = connection.prepareStatement(
                    "INSERT INTO t_bulletin_board (post_id, title, content, teacher_id) VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, bulletinBoard.getPostId());
            statement.setString(2, bulletinBoard.getTitle());
            statement.setString(3, bulletinBoard.getContent());
            statement.setString(4, bulletinBoard.getTeacherId());

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

    // BulletinBoardを保存または更新するメソッド
    public boolean save(BulletinBoard bulletinBoard) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存のBulletinBoardを検索
            BulletinBoard existingBulletinBoard = getBulletinBoard(bulletinBoard.getPostId());
            if (existingBulletinBoard == null) {
                // 新しいBulletinBoardの場合
                statement = connection.prepareStatement(
                        "INSERT INTO t_bulletin_board (post_id, title, content, teacher_id) VALUES (?, ?, ?, ?)"
                );
                statement.setString(1, bulletinBoard.getPostId());
                statement.setString(2, bulletinBoard.getTitle());
                statement.setString(3, bulletinBoard.getContent());
                statement.setString(4, bulletinBoard.getTeacherId());
            } else {
                // 既存のBulletinBoardの更新
                statement = connection.prepareStatement(
                        "UPDATE t_bulletin_board SET title = ?, content = ?, teacher_id = ? WHERE post_id = ?"
                );
                statement.setString(1, bulletinBoard.getTitle());
                statement.setString(2, bulletinBoard.getContent());
                statement.setString(3, bulletinBoard.getTeacherId());
                statement.setString(4, bulletinBoard.getPostId());
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

    // post_idでBulletinBoardを取得するメソッド
    private BulletinBoard getBulletinBoard(String postId) throws Exception {
        BulletinBoard bulletinBoard = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM t_bulletin_board WHERE post_id = ?");
            statement.setString(1, postId);
            rSet = statement.executeQuery();
            if (rSet.next()) {
                bulletinBoard = new BulletinBoard();
                bulletinBoard.setPostId(rSet.getString("post_id"));
                bulletinBoard.setTitle(rSet.getString("title"));
                bulletinBoard.setContent(rSet.getString("content"));
                bulletinBoard.setTeacherId(rSet.getString("teacher_id"));
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

        return bulletinBoard;
    }

    // BulletinBoardを削除するメソッド
    public boolean delete(String postId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM t_bulletin_board WHERE post_id = ?");
            statement.setString(1, postId);
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
