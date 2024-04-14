package ideamc.titleplugin.Title;

import static ideamc.titleplugin.TitlePlugin.Sql;
import ideamc.titleplugin.Date;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class AddTitle {
    public void addtitle(CommandSender sender, String playername, int title_id){
        String stplayer_uuid = Bukkit.getPlayer(playername).getUniqueId().toString();
        String sql = "INSERT INTO PlayerTitle (player_uuid, title_id, prefix_enable, suffix_enable) VALUES ";
        sql += "('" + stplayer_uuid + "', '";
        sql += title_id + "', ";
        sql += "false, ";
        sql += "false)";
        if(Sql().query(sql, sender)){
            sender.sendMessage("§2[TitlePlugin]已向玩家" + playername + "添加称号ID" + title_id);
        }else{
            sender.sendMessage("§4[TitlePlugin]向玩家" + playername + "添加称号ID" + title_id + "失败");
        }
    }

    public void addtitle(CommandSender sender, String playername, int title_id, int youxiao){
        String stplayer_uuid = Bukkit.getPlayer(playername).getUniqueId().toString();
        String expiration_date = Date.addDaysToDate(Date.getCurrentDate(), youxiao);
        String sql = "INSERT INTO PlayerTitle (player_uuid, title_id, expiration_date, prefix_enable, suffix_enable) VALUES ";
        sql += "('" + stplayer_uuid + "', '";
        sql += title_id + "', '";
        sql += expiration_date + "', ";
        sql += "false, ";
        sql += "false)";
        if(Sql().query(sql, sender)){
            sender.sendMessage("§2[TitlePlugin]已向玩家" + playername + "添加称号ID" + title_id);
        }else{
            sender.sendMessage("§4[TitlePlugin]向玩家" + playername + "添加称号ID" + title_id + "失败");
        }
    }

    public static boolean eventaddtitle(String player_uuid, int title_id){
        String sql = "INSERT INTO PlayerTitle (player_uuid, title_id, prefix_enable, suffix_enable) VALUES ";
        sql += "('" + player_uuid + "', '";
        sql += title_id + "', ";
        sql += "false, ";
        sql += "false)";
        return Sql().eventquery(sql);
    }

}
