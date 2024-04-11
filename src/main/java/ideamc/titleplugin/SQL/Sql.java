package ideamc.titleplugin.SQL;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.sql.*;

public class Sql {
    private Connection connection = null;
    private Statement statement = null;
    public void LoadSQLite(){

        try {
            // 注册 SQLite 数据库驱动
            Class.forName("org.sqlite.JDBC");

            // 连接到数据库（如果不存在则会自动创建）
            connection = DriverManager.getConnection("jdbc:sqlite:./plugins/PlayerSuffix/PlayerSuffix.db");

            // 创建 Statement 对象
            statement = connection.createStatement();

            try {
                // 如果数据表不存在，则创建数据表
                String createTableSQL_Title = "CREATE TABLE IF NOT EXISTS Title ";
                createTableSQL_Title += "(suffix_id INT AUTO_INCREMENT PRIMARY KEY,";
                createTableSQL_Title += "suffix TEXT NOT NULL,";
                createTableSQL_Title += "type TEXT NOT NULL,";
                createTableSQL_Title += "description TEXT,";
                createTableSQL_Title += "vault INT,";
                createTableSQL_Title += "playerpoints INT,";
                createTableSQL_Title += "canbuy boolean,";//能否购买
                createTableSQL_Title += "permission TEXT,";
                createTableSQL_Title += "expiration_date TEXT,";//购买有效期
                createTableSQL_Title += "sale_end_date TEXT)";//限时销售截止日期
                statement.executeUpdate(createTableSQL_Title);

                String createTableSQL_PlayerTitle = "CREATE TABLE IF NOT EXISTS PlayerTitle ";
                createTableSQL_PlayerTitle += "(suffix_id INT NOT NULL,";
                createTableSQL_PlayerTitle += "plyeruuid TEXT NOT NULL,";
                createTableSQL_PlayerTitle += "expiration_date TEXT NOT NULL,";
                createTableSQL_PlayerTitle += "prefix_enable boolean NOT NULL,";
                createTableSQL_PlayerTitle += "suffix_enable boolean NOT NULL)";
                statement.executeUpdate(createTableSQL_PlayerTitle);
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2" + e);
            }

        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2" + e);
        }
    }

    //卸载数据库连接
    public void unLoadSQLite(){

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
    //创建title
    public void query(String sql, CommandSender sender) {
        try {
            statement.executeUpdate(sql);
        }catch (SQLException e){
            sender.sendMessage("[TitlePlugin]§2" + e);;
        }
    }
}
