package ideamc.titleplugin.SQL;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.sql.*;

public class Sql implements sqlchoose{
    private static Connection connection = null;
    private static Statement statement = null;
    public static void LoadSQLite(){
        try {
            // 注册 SQLite 数据库驱动
            Class.forName("org.sqlite.JDBC");

            // 连接到数据库（如果不存在则会自动创建）
            connection = DriverManager.getConnection("jdbc:sqlite:./plugins/TitlePlugin/TitlePlugin.db");

            // 创建 Statement 对象
            statement = connection.createStatement();

            try {
                // 如果数据表不存在，则创建数据表
                String createTableSQL_Title = "CREATE TABLE IF NOT EXISTS Title ";
                createTableSQL_Title += "(title_id INT AUTO_INCREMENT PRIMARY KEY,";
                createTableSQL_Title += "title_name TEXT NOT NULL,";
                createTableSQL_Title += "type TEXT NOT NULL,";
                createTableSQL_Title += "description TEXT,";
                createTableSQL_Title += "vault INT,";//所需金币
                createTableSQL_Title += "playerpoints INT,";//所需点券
                createTableSQL_Title += "canbuy BOOLEAN,";//能否购买
                createTableSQL_Title += "permission TEXT NULL,";//所需权限
                createTableSQL_Title += "youxiao INT,";//购买有效期
                createTableSQL_Title += "sale_end_date TEXT NULL)";//限时销售截止日期
                statement.executeUpdate(createTableSQL_Title);

                String createTableSQL_PlayerTitle = "CREATE TABLE IF NOT EXISTS PlayerTitle ";
                createTableSQL_PlayerTitle += "(title_id INT NOT NULL,";
                createTableSQL_PlayerTitle += "player_uuid TEXT NOT NULL,";
                createTableSQL_PlayerTitle += "expiration_date TEXT NULL,";
                createTableSQL_PlayerTitle += "prefix_enable boolean NOT NULL,";
                createTableSQL_PlayerTitle += "suffix_enable boolean NOT NULL)";
                statement.executeUpdate(createTableSQL_PlayerTitle);
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2SQL数据库加载成功!");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);
            }

        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);
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
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);;
            }
        }
    }
    @Override
    public boolean query(String sql, CommandSender sender){
        try {
            // 连接到数据库
            connection = DriverManager.getConnection("jdbc:sqlite:./plugins/TitlePlugin/TitlePlugin.db");
            // 创建 Statement 对象
            statement = connection.createStatement();
        }catch (SQLException e){
            sender.sendMessage("[TitlePlugin]§4" + e);;
        }
        if(statement != null && connection != null){
            try {
                statement.executeUpdate(sql);
                return true;
            }catch (SQLException e){
                sender.sendMessage("[TitlePlugin]§4" + e);
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
                    Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);
                }
            }
        }else{
            return false;
        }
    }
    @Override
    public ResultSet readquery(String sql, CommandSender sender){
        try {
            // 连接到数据库
            connection = DriverManager.getConnection("jdbc:sqlite:./plugins/TitlePlugin/TitlePlugin.db");
            // 创建 Statement 对象
            statement = connection.createStatement();
        }catch (SQLException e){
            sender.sendMessage("[TitlePlugin]§4" + e);
        }
        if(statement != null && connection != null){
            try {
                return statement.executeQuery(sql);
            }catch (SQLException e){
                sender.sendMessage("[TitlePlugin]§4" + e);
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
                    Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);
                }
            }
        }
        return null;
    }
    @Override
    public boolean eventquery(String sql) {
        try {
            // 连接到数据库
            connection = DriverManager.getConnection("jdbc:sqlite:./plugins/TitlePlugin/TitlePlugin.db");
            // 创建 Statement 对象
            statement = connection.createStatement();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2" + e);;
        }
        if (statement != null && connection != null) {
            try {
                statement.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);;
                return false;
            } finally {
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
                    Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);
                }
            }
        } else {
            return false;
        }
    }
    @Override
    public ResultSet readeventquery(String sql){
        try {
            // 连接到数据库
            connection = DriverManager.getConnection("jdbc:sqlite:./plugins/TitlePlugin/TitlePlugin.db");
            // 创建 Statement 对象
            statement = connection.createStatement();
        }catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);
        }
        if(statement != null && connection != null){
            try {
                return statement.executeQuery(sql);
            }catch (SQLException e){
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);
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
                    Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);
                }
            }
        }
        return null;
    }
}
