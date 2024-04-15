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
    public static void buycoin(CommandSender sender, int title_id){
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
                            String sql1;
                            if(youxiao != 0){
                                String date = addDaysToDate(getCurrentDate(),youxiao);
                                sql1 = "INSERT INTO PlayerTitle (title_id,player_uuid,expiration_date,prefix_enable,suffix_enable) VALUES";
                                sql1 += " (" + title_id + ",";
                                sql1 += " '" + stplayer_uuid + "',";
                                sql1 += " '" + date + "',";
                                sql1 += " false,";
                                sql1 += " false)";
                            }else{
                                sql1 = "INSERT INTO PlayerTitle (title_id,player_uuid,expiration_date,prefix_enable,suffix_enable) VALUES";
                                sql1 += " (" + title_id + ",";
                                sql1 += " '" + stplayer_uuid + ",";
                                sql1 += " NULL,";
                                sql1 += " false, ";
                                sql1 += " false)";
                            }
                            if(Sql().query(sql1,sender)){
                                sender.sendMessage("§2[TitlePlugin]称号ID" + title_id + "成功!");
                            }else{
                                sender.sendMessage("§4[TitlePlugin]称号ID" + title_id + "失败!");
                            }
                        }
                    }
                }
            }catch (Exception ignored){}
        }else{
            sender.sendMessage("§4[TitlePlugin]输入的称号ID有误!");
        }
    }

    public static void buypoint(CommandSender sender, int title_id){
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
                            String sql1;
                            if(youxiao != 0){
                                String date = addDaysToDate(getCurrentDate(),youxiao);
                                sql1 = "INSERT INTO PlayerTitle (title_id,player_uuid,expiration_date,prefix_enable,suffix_enable) VALUES";
                                sql1 += " (" + title_id + ",";
                                sql1 += " '" + stplayer_uuid + "',";
                                sql1 += " '" + date + "',";
                                sql1 += " false,";
                                sql1 += " false)";
                            }else{
                                sql1 = "INSERT INTO PlayerTitle (title_id,player_uuid,expiration_date,prefix_enable,suffix_enable) VALUES";
                                sql1 += " (" + title_id + ",";
                                sql1 += " ' " + stplayer_uuid + "',";
                                sql1 += " NULL,";
                                sql1 += " false,";
                                sql1 += " false)";
                            }
                            if(Sql().query(sql1,sender)){
                                sender.sendMessage("§2[TitlePlugin]购买成功!");
                            }else{
                                sender.sendMessage("§4[TitlePlugin]购买失败!");
                            }
                        }
                    }else{
                        sender.sendMessage("§4[TitlePlugin]该称号无法购买!");
                    }
                }
            }catch (Exception ignored){}
        }else{
            sender.sendMessage("§4[TitlePlugin]输入的称号ID有误!");
        }
    }
}
