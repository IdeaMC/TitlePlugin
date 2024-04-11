package ideamc.titleplugin.Vault;

import ideamc.titleplugin.TitlePlugin;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Point {
    public boolean removeplayerpoint(String playername,int playerpoint){
        Player player = Bukkit.getPlayer(playername);
        UUID playeruuid = player.getUniqueId();

        PlayerPointsAPI ppapi = TitlePlugin.getPlayerPointsAPI();
        boolean response = ppapi.take(playeruuid,playerpoint);

        if(response){
            player.sendMessage("[TitlePlugin]§2购买成功,剩余点券" + ppapi.look(playeruuid));
            return true;
        }else{
            player.sendMessage("[TitlePlugin]§4购买失败,剩余点券" + ppapi.look(playeruuid));
            return false;
        }
    }
}
