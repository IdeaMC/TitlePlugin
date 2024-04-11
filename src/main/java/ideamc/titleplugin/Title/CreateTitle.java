package ideamc.titleplugin.Title;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

// /ps create [type] [title名称] [coin/playerpoints/permission] (有效期)
public class CreateTitle {
    //活动title的创建
    public void create(String type,String title_name){;
        MiniMessage mm = MiniMessage.miniMessage();
        Component title = mm.deserialize("<gold>[<red>活动<gold>]<white> " + title_name);
        Player player = Bukkit.getPlayer("suxiaolin");
        player.sendMessage(String.valueOf(title));
    }
    //点券or金币or限量title的创建
    public void create(String type,String suffix_name,int vault){

    }
    //权限title的创建
    public void create(String type,String suffix_name,String permission){

    }

}
