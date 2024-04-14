package ideamc.titleplugin.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PlayerCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("tip") && args.length == 0){
            sender.sendMessage(playerhelp);
            return true;
        }
        if(command.getName().equalsIgnoreCase("tip") && args.length == 1 && args[0].equalsIgnoreCase("shop")){

        }
        return false;
    }

    private final String[] playerhelp = {
            "===TitlePlugin===",
            "/tip shop ---打开称号商店",
            "/tip buy [称号ID] ---购买称号",
            "/tip list ---列出已有称号",
            "/tip listall ---列出商店所有称号",
            "/tip ui ---打开个人称号仓库"
    };

}
