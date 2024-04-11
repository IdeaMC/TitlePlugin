package ideamc.titleplugin.Command;

import ideamc.titleplugin.Title.CreateTitle;
import ideamc.titleplugin.TitlePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class AdminCommand implements CommandExecutor {
    public AdminCommand(TitlePlugin plugin) {
        plugin.getCommand("titleplugin").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(command.getName().equals("titleplugin") && args[0].equalsIgnoreCase("test")){
            CreateTitle ct = new CreateTitle();
            ct.create(commandSender,"title","test");
            commandSender.sendMessage("test");
            return true;
        }
        return false;
    }
}
