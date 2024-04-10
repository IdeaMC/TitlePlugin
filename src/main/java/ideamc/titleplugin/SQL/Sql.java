package ideamc.titleplugin.SQL;

import org.bukkit.Bukkit;

import java.sql.*;

public class Sql {
    public void LoadSQLite(){
        Connection connection = null;
        Statement statement = null;

        try {
            // 注册 SQLite 数据库驱动
            Class.forName("org.sqlite.JDBC");

            // 连接到数据库（如果不存在则会自动创建）
            connection = DriverManager.getConnection("jdbc:sqlite:./plugins/PlayerSuffix/PlayerSuffix.db");

            // 创建 Statement 对象
            statement = connection.createStatement();

            try {
                // 如果数据表不存在，则创建数据表
                String createTableSQL_Suffix = "CREATE TABLE IF NOT EXISTS Suffix ";
                createTableSQL_Suffix += "(suffix_id INT NOT NULL,";
                createTableSQL_Suffix += "suffix TEXT NOT NULL,";
                createTableSQL_Suffix += "type TEXT NOT NULL,";
                createTableSQL_Suffix += "description TEXT,";
                createTableSQL_Suffix += "vault INT,";
                createTableSQL_Suffix += "playerpoint INT,";
                createTableSQL_Suffix += "permission TEXT,";
                createTableSQL_Suffix += "sale_data TEXT)";
                statement.executeUpdate(createTableSQL_Suffix);

                String createTableSQL_PlayerSuffix = "CREATE TABLE IF NOT EXISTS PlayerSuffix ";
                createTableSQL_PlayerSuffix += "(suffix_id INT NOT NULL,";
                createTableSQL_PlayerSuffix += "plyeruuid TEXT NOT NULL,";
                createTableSQL_PlayerSuffix += "data TEXT NOT NULL";
                createTableSQL_PlayerSuffix += "enable TEXT NOT NULL)";
                statement.executeUpdate(createTableSQL_PlayerSuffix);
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("[PlayerSuffix]§2" + e);
            }

        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getConsoleSender().sendMessage("[PlayerSuffix]§2" + e);
        } finally {
            // 关闭连接和 Statement
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("[PlayerSuffix]§2" + e);;
            }
        }
    }
}
