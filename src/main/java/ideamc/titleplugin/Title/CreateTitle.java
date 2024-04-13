package ideamc.titleplugin.Title;

import static ideamc.titleplugin.TitlePlugin.Sql;
import org.bukkit.command.CommandSender;


// /ps create [type] [title名称] [coin/playerpoints/permission]
public class CreateTitle {
    //活动title的创建
    public void create(CommandSender sender, String type, String titleName, String description){
        String sql = null;
        if(type.equalsIgnoreCase("activity")){
            sql = "INSERT INTO Title (type,title_name,description,canbuy,vault,playerpoints,youxiao) ";
            sql += "VALUES ";
            sql += "('activity',";
            sql += "'" + titleName + "',";
            sql += "'" + description + "',";
            sql += "false,";
            sql += "0,";
            sql += "0,";
            sql += "0)";
        }
        if(Sql().query(sql,sender)){
            sender.sendMessage("§2[TitlePlugin]创建成功!");
        }else{
            sender.sendMessage("§4l[TitlePlugin]创建失败!");
        }
    }
    //点券or金币title的创建
    public void create(CommandSender sender, String type, String titleName, String description, int vault){
        String sql =null;
        if(type.equalsIgnoreCase("coin")){
            sql = "INSERT INTO Title (type,title_name,description,vault,canbuy,playerpoints) ";
            sql += "VALUES ";
            sql += "('coin',";
            sql += "'" + titleName + "',";
            sql += "'" + description + "',";
            sql += vault + ",";
            sql += "true,";
            sql += "0)";
        } else if(type.equalsIgnoreCase("points")){
            sql = "INSERT INTO Title (type,title_name,description,playerpoints,canbuy,vault) ";
            sql += "VALUES ";
            sql += "('points',";
            sql += "'" + titleName + "',";
            sql += "'" + description + "',";
            sql += vault + ",";
            sql += "true,";
            sql += "0)";
        }
        if(Sql().query(sql,sender)){
            sender.sendMessage("§2[TitlePlugin]创建成功!");
        }else{
            sender.sendMessage("§4[TitlePlugin]创建失败!");
        }
    }
    //权限title的创建
    public void create(CommandSender sender, String type, String titleName, String descripition, String permission){
        String sql =null;
        if(type.equalsIgnoreCase("permission")){
            sql = "INSERT INTO Title (type,title_name,description,permission,canbuy,vault,playerpoints) ";
            sql += "VALUES ";
            sql += "('permission',";
            sql += "'" + titleName + "',";
            sql += "'" + descripition + "',";
            sql += "'" + permission + "',";
            sql += "false,";
            sql += "0,";
            sql += "0)";
        }
        if(Sql().query(sql,sender)){
            sender.sendMessage("§2[TitlePlugin]创建成功!");
        }else{
            sender.sendMessage("§4[TitlePlugin]创建失败!");
        }
    }
}
