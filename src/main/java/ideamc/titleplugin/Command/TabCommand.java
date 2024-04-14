package ideamc.titleplugin.Command;

import ideamc.titleplugin.TitlePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TabCommand implements TabCompleter {
    public TabCommand(TitlePlugin plugin) {
        plugin.getCommand("tip").setTabCompleter(this);
        plugin.getCommand("atip").setTabCompleter(this);
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String a, String[] args) {

        if (command.getName().equalsIgnoreCase("tip") && args.length == 1) {
            return Collections.singletonList(playertabCommands.toString());
        }else if(command.getName().equalsIgnoreCase("atip") && args.length == 1){
            return Collections.singletonList(admintabCommands.toString());
        }
        return null;
    }

    private final List<String> playertabCommands = Arrays.asList(
            "shop",
            "ui"
    );
    private final List<String> admintabCommands = Arrays.asList(
            "create",
            "del",
            "setcoin",
            "setpoints",
            "setdescription",
            "setpermission",
            "setcanbuy",
            "setyouxiao",
            "setjiezhi",
            "add",
            "del"
    );
}
