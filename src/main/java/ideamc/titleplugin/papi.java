package ideamc.titleplugin;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.util.UUID;

import static ideamc.titleplugin.TitlePlugin.Sql;

public class Papi extends PlaceholderExpansion {

    private final TitlePlugin plugin;

    public Papi(TitlePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    @NotNull
    public String getAuthor() {
        return "suxiaolin"; //
    }

    @Override
    @NotNull
    public String getIdentifier() {
        return "papi";
    }

    @Override
    @NotNull
    public String getVersion() {
        return plugin.getDescription().getVersion(); //
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        UUID player_uuid = player.getUniqueId();
        String stplayer_uuid = player_uuid.toString();
        if (params.equalsIgnoreCase("player_prefix")) {
            String sql = "SELECT title_id FROM PlayerTitle WHERE prefix_enable = true AND player_uuid = '" + stplayer_uuid + "'";
            ResultSet rs = Sql().readeventquery(sql);
            if (rs != null){
                try {
                    while (rs.next()) {
                        int title_id = rs.getInt("title_id");
                        String sql1 = "SELECT title_name FROM Title WHERE title_id = " + title_id;
                        ResultSet rs1 = Sql().readeventquery(sql1);
                        if (rs1 != null){
                            try {
                                while (rs1.next()) {
                                    String title_name = rs1.getString("title_name");
                                    return title_name;
                                }
                            }catch (Exception e) {
                                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4papi处理相关问题:" + e.getMessage());
                            }
                        }

                    }
                }catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§44papi处理相关问题" + e.getMessage());
                }
            }
        } else if (params.equalsIgnoreCase("player_suffix")) {
            String sql = "SELECT title_id FROM PlayerTitle WHERE suffix_enable = true AND player_uuid = '" + stplayer_uuid + "'";
            ResultSet rs = Sql().readeventquery(sql);
            if (rs != null){
                try {
                    while (rs.next()) {
                        int title_id = rs.getInt("title_id");
                        String sql1 = "SELECT title_name FROM Title WHERE title_id = " + title_id;
                        ResultSet rs1 = Sql().readeventquery(sql1);
                        if (rs1 != null){
                            try {
                                while (rs1.next()) {
                                    String title_name = rs1.getString("title_name");
                                    return title_name;
                                }
                            }catch (Exception e) {
                                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4papi处理相关问题:" + e.getMessage());
                            }
                        }

                    }
                }catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§44papi处理相关问题" + e.getMessage());
                }
            }
        }

        return "";
    }
}
