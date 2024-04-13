package ideamc.titleplugin.Title;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.sql.ResultSet;
import java.util.UUID;

import static ideamc.titleplugin.Date.addDaysToDate;
import static ideamc.titleplugin.Date.getCurrentDate;
import static ideamc.titleplugin.TitlePlugin.Sql;
import static ideamc.titleplugin.Vault.Point.removeplayerpoint;
import static ideamc.titleplugin.Vault.coin.removecoin;

public class BuyTitle {
    public void buycoin(CommandSender sender, int title_id){
        String sql = "SELECT * FROM Title WHERE title_id = " + title_id;
        ResultSet resultSet = Sql().readquery(sql, sender);
        if (resultSet != null) {
            String player_name = sender.getName();
            String stplayer_uuid = Bukkit.getPlayer(player_name).getUniqueId().toString();
            try {
                if (resultSet.next()) {
                    boolean canbuy = resultSet.getBoolean("canbuy");
                    if(canbuy){
                        int vault = resultSet.getInt("vault");
                        if(removecoin(player_name,vault)){
                            int youxiao = resultSet.getInt("youxiao");
                            if(youxiao != 0){
                                String date = addDaysToDate(getCurrentDate(),youxiao);
                                String sql1 = "INSERT INTO PlayerTitle (title_id,player_uuid,expiration_date,prefix_enable,suffix_enable) VALUES";
                                sql += " ('" + title_id + "', ";
                                sql += stplayer_uuid + ", '";
                                sql += date + "', ";
                                sql += "false, ";
                                sql += "false)";
                            }else{
                                String sql1 = "INSERT INTO PlayerTitle (title_id,player_uuid,expiration_date,prefix_enable,suffix_enable) VALUES";
                                sql += " ('" + title_id + "', ";
                                sql += stplayer_uuid + ", '";
                                sql += "0', ";
                                sql += "false, ";
                                sql += "false)";
                            }
                        }
                    }
                }
            }catch (Exception ignored){}
        }
    }

    public void buypoint(CommandSender sender, int title_id){
        String sql = "SELECT * FROM Title WHERE title_id = " + title_id;
        ResultSet resultSet = Sql().readquery(sql, sender);
        if (resultSet != null) {
            String player_name = sender.getName();
            String stplayer_uuid = Bukkit.getPlayer(player_name).getUniqueId().toString();
            try {
                if (resultSet.next()) {
                    boolean canbuy = resultSet.getBoolean("canbuy");
                    if(canbuy){
                        int playerpoints = resultSet.getInt("playerpoints");
                        if(removeplayerpoint(player_name,playerpoints)){
                            int youxiao = resultSet.getInt("youxiao");
                            if(youxiao != 0){
                                String date = addDaysToDate(getCurrentDate(),youxiao);
                                String sql1 = "INSERT INTO PlayerTitle (title_id,player_uuid,expiration_date,prefix_enable,suffix_enable) VALUES";
                                sql += " ('" + title_id + "', ";
                                sql += stplayer_uuid + ", '";
                                sql += date + "', ";
                                sql += "false, ";
                                sql += "false)";
                            }else{
                                String sql1 = "INSERT INTO PlayerTitle (title_id,player_uuid,expiration_date,prefix_enable,suffix_enable) VALUES";
                                sql += " ('" + title_id + "', ";
                                sql += stplayer_uuid + ", '";
                                sql += "0', ";
                                sql += "false, ";
                                sql += "false)";
                            }
                        }
                    }
                }
            }catch (Exception ignored){}
        }
    }
}
