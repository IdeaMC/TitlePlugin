package ideamc.titleplugin.SQL;

import ideamc.titleplugin.GUI.biyao;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ideamc.titleplugin.TitlePlugin.getconfig;

public class MySQL implements sqlchoose{
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
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
                createTableSQL_Title += "youxiao INT,";//购买有效期
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
                    Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);;
                }
            }
        }else{
            return false;
        }
    }
    @Override
    public boolean readquery (String sql, CommandSender sender, int a){
        try {
            String url = "jdbc:mysql://" + Host + ":" + Port + "/" + Database;
            // 连接到数据库
            connection = DriverManager.getConnection(url, Username, Password);
            // 创建 Statement 对象
            statement = connection.createStatement();
        }catch (SQLException e){
            sender.sendMessage("[TitlePlugin]§4" + e);;
        }
        if(statement != null && connection != null){
            try {
                resultSet = statement.executeQuery(sql);
                if (Objects.requireNonNull(resultSet).next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        return true;
                    }else {
                        return false;
                    }
                }
            }catch (SQLException e){
                sender.sendMessage("[TitlePlugin]§4" + e);
            }finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                        resultSet = null;
                    }
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
        return false;
    }
    @Override
    public List<biyao.TitleData> readquery(String sql, CommandSender sender, String table_name){
        try {
            String url = "jdbc:mysql://" + Host + ":" + Port + "/" + Database;
            // 连接到数据库
            connection = DriverManager.getConnection(url, Username, Password);
            // 创建 Statement 对象
            statement = connection.createStatement();
        }catch (SQLException e){
            sender.sendMessage("[TitlePlugin]§4" + e);;
        }
        if(statement != null && connection != null){
            try {
                resultSet = statement.executeQuery(sql);
                List<biyao.TitleData> titleData = new ArrayList<>();
                while (resultSet.next()) {
                    if(table_name.equalsIgnoreCase("title")){
                        int titleId = resultSet.getInt("title_id");
                        String titleName = resultSet.getString("title_name");
                        String type = resultSet.getString("type");
                        String description = resultSet.getString("description");
                        int coin = resultSet.getInt("vault");
                        int point = resultSet.getInt("playerpoints");
                        boolean canBuy = resultSet.getBoolean("canbuy");
                        String permission = resultSet.getString("permission");
                        int youxiao = resultSet.getInt("youxiao");
                        String saleEndDate = resultSet.getString("sale_end_date");
                        titleData.add(new biyao.TitleData(titleId, titleName, type, description, coin, point, canBuy, permission, youxiao, saleEndDate, null, null,true, true));
                    }else if(table_name.equalsIgnoreCase("playertitle")){
                        int titleId = resultSet.getInt("title_id");
                        String playerUuid = resultSet.getString("player_uuid");
                        String expirationDate = resultSet.getString("expiration_date");
                        boolean prefixEnable = resultSet.getBoolean("prefix_enable");
                        boolean suffixEnable = resultSet.getBoolean("suffix_enable");
                        titleData.add(new biyao.TitleData(titleId, null, null, null, 0, 0, false, null, 0, null, playerUuid,expirationDate, prefixEnable, suffixEnable));
                    }
                }
                return titleData;
            }catch (SQLException e){
                sender.sendMessage("[TitlePlugin]§4" + e);
            }finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                        resultSet = null;
                    }
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
    public boolean eventquery(String sql){
        try {
            String url = "jdbc:mysql://" + Host + ":" + Port + "/" + Database;
            // 连接到数据库
            connection = DriverManager.getConnection(url, Username, Password);
            // 创建 Statement 对象
            statement = connection.createStatement();
        }catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);
        }
        if(statement != null && connection != null){
            try {
                statement.executeUpdate(sql);
                return true;
            }catch (SQLException e){
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);
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
    public List<biyao.TitleData> readeventquery(String sql, String table_name){
        try {
            String url = "jdbc:mysql://" + Host + ":" + Port + "/" + Database;
            // 连接到数据库
            connection = DriverManager.getConnection(url, Username, Password);
            // 创建 Statement 对象
            statement = connection.createStatement();
        }catch (SQLException e){
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);
        }
        if(statement != null && connection != null){
            try {
                resultSet = statement.executeQuery(sql);
                List<biyao.TitleData> titleData = new ArrayList<>();
                while (resultSet.next()) {
                    if(table_name.equalsIgnoreCase("title")){
                        int titleId = resultSet.getInt("title_id");
                        String titleName = resultSet.getString("title_name");
                        String type = resultSet.getString("type");
                        String description = resultSet.getString("description");
                        int coin = resultSet.getInt("vault");
                        int point = resultSet.getInt("playerpoints");
                        boolean canBuy = resultSet.getBoolean("canbuy");
                        String permission = resultSet.getString("permission");
                        int youxiao = resultSet.getInt("youxiao");
                        String saleEndDate = resultSet.getString("sale_end_date");
                        titleData.add(new biyao.TitleData(titleId, titleName, type, description, coin, point, canBuy, permission, youxiao, saleEndDate, null, null,true, true));
                    }else if(table_name.equalsIgnoreCase("playertitle")){
                        int titleId = resultSet.getInt("title_id");
                        String playerUuid = resultSet.getString("player_uuid");
                        String expirationDate = resultSet.getString("expiration_date");
                        boolean prefixEnable = resultSet.getBoolean("prefix_enable");
                        boolean suffixEnable = resultSet.getBoolean("suffix_enable");
                        titleData.add(new biyao.TitleData(titleId, null, null, null, 0, 0, false, null, 0, null, playerUuid,expirationDate, prefixEnable, suffixEnable));
                    }
                }
                return titleData;
            }catch (SQLException e){
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4" + e);
            }finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                        resultSet = null;
                    }
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
