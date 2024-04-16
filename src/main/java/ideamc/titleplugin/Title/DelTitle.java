package ideamc.titleplugin.Title;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.UUID;

import static ideamc.titleplugin.TitlePlugin.Sql;

public class DelTitle {
    public static void delatitle(CommandSender sender, int title_id){
        String sql = "DELETE FROM Title WHERE title_id = " + title_id;
        if(Sql().query(sql, sender)){
            sender.sendMessage("§2[TitlePlugin]删除成功!");
            String sql1 = "DELETE FROM PlayerTitle WHERE title_id = " + title_id;
            if(Sql().query(sql1, sender)){
                sender.sendMessage("[TitlePlugin]§2已从玩家移除称号ID" + title_id);
            }else{
                sender.sendMessage("[TitlePlugin]§4从玩家移除称号ID" + title_id + "失败!");
            }
        }else{
            sender.sendMessage("[TitlePlugin]§4删除失败!");
        }
    }

    public static void delplayertitle(CommandSender sender, String playername, int title_id){
        UUID player_uuid = Bukkit.getPlayer(playername).getUniqueId();
        String stplayer_uuid = player_uuid.toString();
        String sql = "DELETE FROM PlayerTitle WHERE player_uuid = '" + stplayer_uuid + "' AND title_id = " + title_id;
        if(Sql().query(sql, sender)){
            sender.sendMessage("[TitlePlugin]§2已从玩家" + playername + "移除称号ID" + title_id);
        }else{
            sender.sendMessage("[TitlePlugin]§4从玩家" + playername + "移除称号ID" + title_id + "失败!");
        }
    }

    public static boolean eventdelplayertitle(String player_uuid, int title_id){
        String sql = "DELETE FROM PlayerTitle WHERE player_uuid = '" + player_uuid + "' AND title_id = " + title_id;
        return Sql().eventquery(sql);
    }
}
