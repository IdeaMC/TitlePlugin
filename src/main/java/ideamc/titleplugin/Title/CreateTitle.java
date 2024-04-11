package ideamc.titleplugin.Title;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// /ps create [type] [title名称] [coin/playerpoints/permission] (有效期)
public class CreateTitle {
    //活动title的创建
    public void create(CommandSender sender,String type, String title_name){;
        Component title = Component.text()
                .append(Component.text("<gradient:#5e4fa2:#f79459>||||||||||||||||||||||||</gradient>!")).build();
        Player player = Bukkit.getPlayer("suxiaolin");
        player.sendMessage(String.valueOf(title));
    }
    //点券or金币or限量title的创建
    public void create(CommandSender sender,String type,String suffix_name,int vault){

    }
    //权限title的创建
    public void create(CommandSender sender,String type,String suffix_name,String permission){

    }

}
