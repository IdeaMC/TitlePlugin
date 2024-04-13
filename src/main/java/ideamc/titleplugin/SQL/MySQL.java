package ideamc.titleplugin.SQL;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.sql.*;

import static ideamc.titleplugin.TitlePlugin.getconfig;

public class MySQL implements sqlchoose{
    private static Connection connection;
    private static Statement statement;
    private static int Port;
    private static String Host;
    private static String Username;
    private static String Password;
    private static String Database;
    public static void MysqlConfig() {
        Host = getconfig().getString("SQL.host");
        Port = getconfig().getInt("SQL.port");
        Database = getconfig().getString("SQL.database");
        Username = getconfig().getString("SQL.username");
        Password = getconfig().getString("SQL.password");
        connection = null;
        statement = null;
    }
    public static void LoadMysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + Host + ":" + Port + "/" + Database;
            connection = DriverManager.getConnection(url, Username, Password);
            createTableIfNotExists();
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2Mysql数据库连接成功");
        } catch (SQLException | ClassNotFoundException e) {
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4Mysql数据库连接错误!" + e.getMessage());
        }
    }

    public static void createTableIfNotExists() {
        if (connection != null) {
            try {
                statement = connection.createStatement();

                String createTableSQL_Title = "CREATE TABLE IF NOT EXISTS Title ";
                createTableSQL_Title += "(title_id INT AUTO_INCREMENT PRIMARY KEY,";
                createTableSQL_Title += "title_name TEXT NOT NULL,";
                createTableSQL_Title += "type TEXT NOT NULL,";
                createTableSQL_Title += "description TEXT,";
                createTableSQL_Title += "vault INT,";//所需金币
                createTableSQL_Title += "playerpoints INT,";//所需点券
                createTableSQL_Title += "canbuy BOOLEAN,";//能否购买
                createTableSQL_Title += "permission TEXT,";//所需权限
                createTableSQL_Title += "expiration_date INT,";//购买有效期
                createTableSQL_Title += "sale_end_date TEXT)";//限时销售截止日期
                statement.executeUpdate(createTableSQL_Title);

                String createTableSQL_PlayerTitle = "CREATE TABLE IF NOT EXISTS PlayerTitle ";
                createTableSQL_PlayerTitle += "(title_id INT NOT NULL,";
                createTableSQL_PlayerTitle += "plyeruuid TEXT NOT NULL,";
                createTableSQL_PlayerTitle += "expiration_date TEXT,";
                createTableSQL_PlayerTitle += "prefix_enable boolean NOT NULL,";
                createTableSQL_PlayerTitle += "suffix_enable boolean NOT NULL)";
                statement.executeUpdate(createTableSQL_PlayerTitle);

                statement.close();
                connection.close();
                statement = null;
                connection = null;
            } catch (SQLException ignored) {
            }
        }
    }
    @Override
    public boolean query(String sql, CommandSender sender){
        try {
            String url = "jdbc:mysql://" + Host + ":" + Port + "/" + Database;
            // 连接到数据库
            connection = DriverManager.getConnection(url, Username, Password);
            // 创建 Statement 对象
            statement = connection.createStatement();
        }catch (SQLException e){
            sender.sendMessage("[TitlePlugin]§2" + e);;
        }
        if(statement != null && connection != null){
            try {
                statement.executeUpdate(sql);
                return true;
            }catch (SQLException e){
                sender.sendMessage("[TitlePlugin]§2" + e);
                return false;
            }finally {
                try {
                    if (statement != null) {
                        statement.close();
                        statement = null;
                    }
                    if (connection != null) {
                        connection.close();
                        connection = null;
                    }
                } catch (SQLException e) {
                    Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2" + e);;
                }
            }
        }else{
            return false;
        }
    }
    @Override
    public ResultSet readquery(String sql, CommandSender sender){
        try {
            String url = "jdbc:mysql://" + Host + ":" + Port + "/" + Database;
            // 连接到数据库
            connection = DriverManager.getConnection(url, Username, Password);
            // 创建 Statement 对象
            statement = connection.createStatement();
        }catch (SQLException e){
            sender.sendMessage("[TitlePlugin]§2" + e);;
        }
        if(statement != null && connection != null){
            try {
                return statement.executeQuery(sql);
            }catch (SQLException e){
                sender.sendMessage("[TitlePlugin]§2" + e);
            }finally {
                try {
                    if (statement != null) {
                        statement.close();
                        statement = null;
                    }
                    if (connection != null) {
                        connection.close();
                        connection = null;
                    }
                } catch (SQLException e) {
                    Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2" + e);;
                }
            }
        }
        return null;
    }
}
