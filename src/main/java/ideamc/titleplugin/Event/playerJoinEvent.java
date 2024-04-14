package ideamc.titleplugin.Event;

import ideamc.titleplugin.TitlePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permission;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static ideamc.titleplugin.Date.isDateLater;
import static ideamc.titleplugin.Title.AddTitle.eventaddtitle;
import static ideamc.titleplugin.Title.DelTitle.eventdelplayertitle;
import static ideamc.titleplugin.TitlePlugin.Sql;
import static org.bukkit.Bukkit.getServer;

public class playerJoinEvent implements Listener {
    public playerJoinEvent(TitlePlugin plugin){
        getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoinAddOrDelTitle(PlayerJoinEvent event){

        Player player = event.getPlayer();
        UUID player_uuid = player.getUniqueId();
        String stplayer_uuid = player_uuid.toString();

        String sql = "SELECT DISTINCT permission FROM Title";
        ResultSet rs = Sql().readeventquery(sql);
        Set<String> uniquePermissions = new HashSet<>();
        if(rs != null){
            try {
                while (rs.next()){
                    String permission = rs.getString("permission");
                    if(permission != null){
                        uniquePermissions.add(permission);
                    }
                }
                if(!uniquePermissions.isEmpty()){
                    String sql1 = "SELECT title_id FROM PlayerTitle WHERE player_uuid = " + "'" + stplayer_uuid + "'";
                    ResultSet rs1 = Sql().readeventquery(sql1);
                    ArrayList<Integer> title_id = new ArrayList<>();//玩家所拥有的称号ID集合
                    if(rs1 != null){
                        while (rs1.next()){
                            title_id.add(rs1.getInt("title_id"));
                        }
                    }
                    if(!title_id.isEmpty()){
                        for (String permission : uniquePermissions){
                            Permission per = new Permission(permission);
                            if(player.hasPermission(per)){
                                String sql2 = "SELECT title_id FROM Title WHERE permission = " + "'" + permission + "'";
                                ResultSet rs2 = Sql().readeventquery(sql2);
                                if(rs2 != null){
                                    while (rs2.next()){
                                        int t = rs2.getInt("title_id");
                                        if(!title_id.contains(t)){
                                            if(eventaddtitle(stplayer_uuid, t)){
                                                player.sendMessage("§2[Titleplugin]你因有权限" + permission + "获得了称号ID" + t);
                                                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2玩家" + player.getName() + "因有权限" + permission + "获得称号 " + t);
                                            }else{
                                                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4玩家加入检测权限添加称号出现问题!");
                                            }
                                        }
                                    }
                                }
                            }else{
                                String sql2 = "SELECT title_id FROM Title WHERE permission = " + "'" + permission + "'";
                                ResultSet rs2 = Sql().readeventquery(sql2);
                                if(rs2 != null){
                                    while (rs2.next()){
                                        int t = rs2.getInt("title_id");
                                        if(title_id.contains(t)){
                                            if(eventdelplayertitle(stplayer_uuid, t)){
                                                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2玩家" + player.getName() + "因失去权限" + permission + "失去称号ID " + t);
                                            }else{
                                                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4玩家加入检测权限删除称号出现问题!");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (SQLException ignored) {}
        }
    }
    @EventHandler
    public void onPlayerJoinData(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID player_uuid = player.getUniqueId();
        String stplayer_uuid = player_uuid.toString();

        String sql = "SELECT DISTINCT expiration_date, title_id FROM PlayerTitle WHERE player_uuid = " + "'" + stplayer_uuid + "'";
        ResultSet rs = Sql().readeventquery(sql);
        if(rs != null){
            try {
                while (rs.next()){
                    String expiration_date = rs.getString("expiration_date");
                    int title_id = rs.getInt("title_id");
                    if(expiration_date != null){
                        if(isDateLater(expiration_date)){
                            String sql1 = "DELETE FROM PlayerTitle WHERE player_uuid = " + "'" + stplayer_uuid + "'" + " AND expiration_date = " + "'" + expiration_date + "'";
                            if(Sql().eventquery(sql1)){
                                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2玩家" + player.getName() + "称号ID" + title_id + "已过期");
                                player.sendMessage("§2[Titleplugin]称号ID" + title_id + "已过期");
                            }else{
                                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4玩家称号过期删除出现问题!");
                            }
                       }
                   }
               }
           } catch (SQLException ignored) {}
        }
    }
}
