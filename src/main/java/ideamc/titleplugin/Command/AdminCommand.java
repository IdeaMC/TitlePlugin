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
        if("titleplugin".equals(command.getName()) && "test".equalsIgnoreCase(args[0])){
            CreateTitle ct = new CreateTitle();
            commandSender.sendMessage("test");
            return true;
        }
        return false;
    }

    private final String[] adminhelp = {
            "===TitlePlugin===",
            "/atip create activity [称号名称] [称号描述] ---创建活动称号",
            "/atip create coin [称号名称] [称号描述] [金币] ---创建金币称号",
            "/atip create points [称号名称] [称号描述] [点券] ---创建点券称号",
            "/atip create permission [称号名称] [称号描述] [权限] ---创建权限称号",
            "/atip del [称号ID] ---删除称号",
            "/atip setcoin [称号ID] [金币] ---设置称号购买所需金币",
            "/atip setpoints [称号ID] [点券] ---设置称号购买所需点券",
            "/atip setdescription [称号ID] [描述] ---编辑称号描述",
            "/atip setpermission [称号ID] [权限] ---设置称号权限",
            "/atip setcanbuy [称号ID] [true/false] ---设置称号能否购买",
            "/atip setyouxiao [称号ID] [天数] ---设置称号购买有效期",
            "/atip setjiezhi [称号ID] [天数] ---设置称号购买截止日期",
            "/atip add [玩家] [称号ID] ---向玩家添加称号",
            "/atip add [玩家] [称号ID] [天数] ---向玩家添加具有有效期的称号",
            "/atip del [玩家] [称号ID] ---从玩家那里删除称号"

    };
}
