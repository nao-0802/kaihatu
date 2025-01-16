package tool;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    // データベース接続情報
    private static final String URL = "jdbc:h2:tcp://localhost/~/kaihatu"; // H2 データベースの URL
    private static final String USER = "sa"; // ユーザー名
    private static final String PASSWORD = ""; // パスワード

    // コネクション取得メソッド
    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver"); // H2 ドライバのロード
        return DriverManager.getConnection(URL, USER, PASSWORD); // 接続を返す
    }
}
