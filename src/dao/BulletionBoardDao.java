package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bean.BulletionBoard;

public class BulletionBoardDao extends Dao {
    // SQLクエリ: teacher_idに基づいてレコードを取得
    private String baseSql = "SELECT post_id, title, content, teacher_id FROM t_bulletin_board WHERE teacher_id = ?";

    // ResultSetからBulletinBoardリストを生成するメソッド
    private List<BulletionBoard> postfilter(ResultSet rSet) throws Exception {
        List<BulletionBoard> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                BulletionBoard bulletin = new BulletionBoard();
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
    public BulletionBoard findById(String postId) {
        String sql = "SELECT * FROM t_bulletion_board WHERE post_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, postId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BulletionBoard board = new BulletionBoard();
                    board.setPostId(rs.getString("post_id"));
                    board.setTitle(rs.getString("title"));
                    board.setContent(rs.getString("content"));
                    board.setDay(rs.getDate("day"));
                    return board;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // 指定されたteacher_idのBulletinBoardを取得するメソッド
    public List<BulletionBoard> filter(String teacherId) throws Exception {
        List<BulletionBoard> list = new ArrayList<>();
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
    public boolean create(BulletionBoard bulletinBoard) throws Exception {
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

    public boolean save(String title, String content, String teacherId) {
        String sql = "INSERT INTO t_bulletion_board (post_id, title, content, teacher_id, day) VALUES (?, ?, ?, ?, ?)";
        String postId = generateRandomId();
        String day = LocalDate.now().toString();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, postId);
            stmt.setString(2, title);
            stmt.setString(3, content);
            stmt.setString(4, teacherId);
            stmt.setString(5, day);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String generateRandomId() {
        Random random = new Random();
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            idBuilder.append(random.nextInt(10));
        }
        return idBuilder.toString();
    }
    public List<BulletionBoard> findByTeacherId(String teacherId) {
        List<BulletionBoard> boardList = new ArrayList<>();
        String sql = "SELECT * FROM t_bulletion_board WHERE teacher_id = ? ORDER BY day DESC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, teacherId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    BulletionBoard board = new BulletionBoard();
                    board.setPostId(rs.getString("post_id"));
                    board.setTitle(rs.getString("title"));
                    board.setContent(rs.getString("content"));
                    board.setDay(rs.getDate("day"));
                    boardList.add(board);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boardList;
    }


    // post_idでBulletinBoardを取得するメソッド
    public BulletionBoard getBulletinBoard(String postId) throws Exception {
        BulletionBoard bulletinBoard = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM t_bulletin_board WHERE post_id = ?");
            statement.setString(1, postId);
            rSet = statement.executeQuery();
            if (rSet.next()) {
                bulletinBoard = new BulletionBoard();
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
