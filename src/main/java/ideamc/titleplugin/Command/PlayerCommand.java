package ideamc.titleplugin.Command;

import ideamc.titleplugin.TitlePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static ideamc.titleplugin.GUI.GeRenGui.showGeRenGui;
import static ideamc.titleplugin.GUI.ShopGui.showShopGui;

public class PlayerCommand implements CommandExecutor{

    public PlayerCommand(TitlePlugin plugin) {
        plugin.getCommand("tip").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("tip") && args.length == 1 && args[0].equalsIgnoreCase("shop")){
            if(sender instanceof Player){
                showShopGui(sender);
                return true;
            }else{
                sender.sendMessage("[TitlePlugin]§4只能由玩家执行该指令!");
                return true;
            }
        }else if(command.getName().equalsIgnoreCase("tip") && args.length == 1 && args[0].equalsIgnoreCase("ui")){
            if(sender instanceof Player){
                showGeRenGui(sender);
            }else{
                sender.sendMessage("[TitlePlugin]§4只能由玩家执行该指令!");
                return true;
            }
        }else if(command.getName().equalsIgnoreCase("tip") && args.length == 0){
            sender.sendMessage(playerhelp);
            return true;
        }
        return true;
    }

    private final String[] playerhelp = {
            "===TitlePlugin===",
            "/tip shop ---打开称号商店",
            "/tip ui ---打开个人称号仓库"
    };

}
