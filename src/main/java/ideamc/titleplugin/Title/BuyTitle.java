package ideamc.titleplugin.Title;

import ideamc.titleplugin.GUI.biyao;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;

import static ideamc.titleplugin.Date.addDaysToDate;
import static ideamc.titleplugin.Date.getCurrentDate;
import static ideamc.titleplugin.TitlePlugin.Sql;
import static ideamc.titleplugin.Vault.Point.removeplayerpoint;
import static ideamc.titleplugin.Vault.coin.removecoin;

public class BuyTitle {
    public static void buycoin(CommandSender sender, int title_id){
        String sql = "SELECT * FROM Title WHERE title_id = " + title_id;
        List<biyao.TitleData> resultSet = Sql().readquery(sql, sender, "title");
        if (resultSet != null) {
            String player_name = sender.getName();
            String stplayer_uuid = Bukkit.getPlayer(player_name).getUniqueId().toString();
            for(biyao.TitleData t : resultSet){
                boolean canbuy = t.isCanBuy();
                if(canbuy){
                    int vault = t.getCoin();
                    if(removecoin(player_name,vault)){
                        int youxiao = t.getYouxiao();
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
                            sender.sendMessage("[TitlePlugin]§2称号ID" + title_id + "成功!");
                        }else{
                            sender.sendMessage("[TitlePlugin]§4称号ID" + title_id + "失败!");
                        }
                    }
                }
            }
        }else{
            sender.sendMessage("[TitlePlugin]§4输入的称号ID有误!");
        }
    }

    public static void buypoint(CommandSender sender, int title_id){
        String sql = "SELECT * FROM Title WHERE title_id = " + title_id;
        List<biyao.TitleData> resultSet = Sql().readquery(sql, sender, "Title");
        if (resultSet != null) {
            String player_name = sender.getName();
            String stplayer_uuid = Bukkit.getPlayer(player_name).getUniqueId().toString();
            for (biyao.TitleData t : resultSet){
                boolean canbuy = t.isCanBuy();
                if(canbuy){
                    int playerpoints = t.getPoints();
                    if(removeplayerpoint(player_name,playerpoints)){
                        int youxiao = t.getYouxiao();
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
                            sender.sendMessage("[TitlePlugin]§2购买成功!");
                        }else{
                            sender.sendMessage("[TitlePlugin]§4购买失败!");
                        }
                    }
                }else{
                    sender.sendMessage("[TitlePlugin]§4该称号无法购买!");
                }
            }
        }else{
            sender.sendMessage("[TitlePlugin]§4输入的称号ID有误!");
        }
    }
}
