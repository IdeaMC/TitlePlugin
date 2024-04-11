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
                String createTableSQL_Title = "CREATE TABLE IF NOT EXISTS Suffix ";
                createTableSQL_Title += "(suffix_id INT NOT NULL,";
                createTableSQL_Title += "suffix TEXT NOT NULL,";
                createTableSQL_Title += "type TEXT NOT NULL,";
                createTableSQL_Title += "description TEXT,";
                createTableSQL_Title += "vault INT,";
                createTableSQL_Title += "playerpoint INT,";
                createTableSQL_Title += "permission TEXT,";
                createTableSQL_Title += "sale_data TEXT)";
                statement.executeUpdate(createTableSQL_Title);

                String createTableSQL_PlayerTitle = "CREATE TABLE IF NOT EXISTS PlayerSuffix ";
                createTableSQL_PlayerTitle += "(suffix_id INT NOT NULL,";
                createTableSQL_PlayerTitle += "plyeruuid TEXT NOT NULL,";
                createTableSQL_PlayerTitle += "data TEXT NOT NULL,";
                createTableSQL_PlayerTitle += "prefix_enable TEXT NOT NULL,";
                createTableSQL_PlayerTitle += "suffix_enable TEXT NOT NULL)";
                statement.executeUpdate(createTableSQL_PlayerTitle);
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2" + e);
            }

        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2" + e);
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
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2" + e);;
            }
        }
    }

    public void createtitle(){

    }
}
