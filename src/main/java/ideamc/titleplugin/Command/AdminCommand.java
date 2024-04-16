package ideamc.titleplugin.Command;

import ideamc.titleplugin.TitlePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static ideamc.titleplugin.GUI.ListGui.showListGui;
import static ideamc.titleplugin.Title.AddTitle.addtitle;
import static ideamc.titleplugin.Title.CreateTitle.createtitle;
import static ideamc.titleplugin.Title.DelTitle.delatitle;
import static ideamc.titleplugin.Title.DelTitle.delplayertitle;
import static ideamc.titleplugin.Title.EditTitle.edittitle;

public class AdminCommand implements CommandExecutor {
    public AdminCommand(TitlePlugin plugin) {
        plugin.getCommand("atip").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender.hasPermission("titleplugin.op") | sender.isOp()){
            if(command.getName().equalsIgnoreCase("atip") && args.length == 1 && args[0].equalsIgnoreCase("list")){
                showListGui(sender);
                return true;
            } else if(command.getName().equalsIgnoreCase("atip") && args.length == 4 && args[0].equalsIgnoreCase("create") && args[1].equalsIgnoreCase("activity")){
                createtitle(sender, "activity", args[2], args[3]);
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 5 && args[0].equalsIgnoreCase("create") && args[1].equalsIgnoreCase("coin")){
                createtitle(sender, "coin", args[2], args[3], Integer.parseInt(args[4]));
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 5 && args[0].equalsIgnoreCase("create") && args[1].equalsIgnoreCase("points")){
                createtitle(sender, "points", args[2], args[3], Integer.parseInt(args[4]));
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 5 && args[0].equalsIgnoreCase("create") && args[1].equalsIgnoreCase("permission")){
                createtitle(sender, "points", args[2], args[3], args[4]);
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && (args.length == 1 | args.length == 2 | args.length == 3 | args.length == 4) && args[0].equalsIgnoreCase("create")){
                sender.sendMessage(createhelp);
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 2 && args[0].equalsIgnoreCase("del")){
                delatitle(sender, Integer.parseInt(args[1]));
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && (args.length == 1 | args.length == 2 | args.length == 3) && args[0].equalsIgnoreCase("del")){
                sender.sendMessage(delhelp);
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 3 && args[0].equalsIgnoreCase("setcoin")){
                edittitle(sender, Integer.parseInt(args[1]), "setcoin", Integer.parseInt(args[2]));
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 3 && args[0].equalsIgnoreCase("setpoints")){
                edittitle(sender, Integer.parseInt(args[1]), "setpoints", Integer.parseInt(args[2]));
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 3 && args[0].equalsIgnoreCase("setdescription")){
                edittitle(sender, Integer.parseInt(args[1]), "setdescription", args[2]);
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 3 && args[0].equalsIgnoreCase("setpermission")){
                edittitle(sender, Integer.parseInt(args[1]), "setpermission", args[2]);
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 3 && args[0].equalsIgnoreCase("setcanbuy")){
                edittitle(sender, Integer.parseInt(args[1]), "setcanbuy", args[2]);
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 3 && args[0].equalsIgnoreCase("setyouxiao")){
                edittitle(sender, Integer.parseInt(args[1]), "setyouxiao", Integer.parseInt(args[2]));
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 3 && args[0].equalsIgnoreCase("setjiezhi")){
                edittitle(sender, Integer.parseInt(args[1]), "setjiezhi", Integer.parseInt(args[2]));
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 3 && args[0].equalsIgnoreCase("setjiezhi")){
                edittitle(sender, Integer.parseInt(args[1]), "setjiezhi", args[2]);
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 3 && args[0].equalsIgnoreCase("add")){
                addtitle(sender, args[1], Integer.parseInt(args[2]));
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 4 && args[0].equalsIgnoreCase("add")){
                addtitle(sender, args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                return true;
            }else if(command.getName().equalsIgnoreCase("atip") && args.length == 3 && args[0].equalsIgnoreCase("del")){
                delplayertitle(sender, args[1], Integer.parseInt(args[2]));
                return true;
            }
            else if(command.getName().equalsIgnoreCase("atip") && (args.length == 1 | args.length == 2 | args.length == 3 | args.length == 4) && args[0].equalsIgnoreCase("add")){
                sender.sendMessage(addhelp);
                return true;
            }else if(command.getName().equalsIgnoreCase("atip")){
                sender.sendMessage(adminhelp);
                return true;
            }
        }
        return true;
    }

    private final String[] adminhelp = {
            "§6======================TitlePlugin=======================",
            "§3/atip list ---展示所有称号",
            "§3/atip create activity [称号名称] [称号描述] ---创建活动称号",
            "§3/atip create coin [称号名称] [称号描述] [金币] ---创建金币称号",
            "§3/atip create points [称号名称] [称号描述] [点券] ---创建点券称号",
            "§3/atip create permission [称号名称] [称号描述] [权限] ---创建权限称号",
            "§3/atip del [称号ID] ---删除称号",
            "§3/atip setcoin [称号ID] [金币] ---设置称号购买所需金币",
            "§3/atip setpoints [称号ID] [点券] ---设置称号购买所需点券",
            "§3/atip setdescription [称号ID] [描述] ---编辑称号描述",
            "§3/atip setpermission [称号ID] [权限] ---设置称号权限",
            "§3/atip setcanbuy [称号ID] [true/false] ---设置称号能否购买",
            "§3/atip setyouxiao [称号ID] [天数] ---设置称号购买有效期",
            "§3/atip setjiezhi [称号ID] [天数] ---设置称号购买截止日期",
            "§3/atip setjiezhi [称号ID] [null] ---删除称号购买截止日期",
            "§3/atip add [玩家] [称号ID] ---向玩家添加称号",
            "§3/atip add [玩家] [称号ID] [天数] ---向玩家添加具有有效期的称号",
            "§3/atip del [玩家] [称号ID] ---从玩家那里删除称号"

    };

    private final String[] createhelp = {
            "§6======================TitlePlugin=======================",
            "§3/atip create activity [称号名称] [称号描述] ---创建活动称号",
            "§3/atip create coin [称号名称] [称号描述] [金币] ---创建金币称号",
            "§3/atip create points [称号名称] [称号描述] [点券] ---创建点券称号",
            "§3/atip create permission [称号名称] [称号描述] [权限] ---创建权限称号",
    };

    private final String[] delhelp = {
            "§6=================TitlePlugin=================",
            "§3/atip del [称号ID] ---删除称号",
            "§3/atip del [玩家] [称号ID] ---从玩家那里删除称号"

    };

    private final String[] addhelp = {
            "§6=======================TitlePlugin=======================",
            "§3/atip add [玩家] [称号ID] ---向玩家添加称号",
            "§3/atip add [玩家] [称号ID] [天数] ---向玩家添加具有有效期的称号",

    };
}
