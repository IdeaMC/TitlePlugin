package ideamc.titleplugin.Title;

import static ideamc.titleplugin.TitlePlugin.Sql;
import org.bukkit.command.CommandSender;


// /ps create [type] [title名称] [coin/playerpoints/permission]
public class CreateTitle {
    //活动title的创建
    public void create(CommandSender sender, String type, String titleName, String description){
        String sql = null;
        if("activity".equalsIgnoreCase(type)){
            sql = "INSERT INTO Title (type,title_name,description,canbuy) ";
            sql += "VALUES ";
            sql += "('activity',";
            sql += "'"+titleName+"',";
            sql += "'"+description+"',";
            sql += "false)";
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
        if("coin".equalsIgnoreCase(type)){
            sql = "INSERT INTO Title (type,title_name,description,vault) ";
            sql += "VALUES ";
            sql += "('coin',";
            sql += "'"+titleName+"',";
            sql += "'"+description+"',";
            sql += vault+",";
            sql += "true)";
        } else if("points".equalsIgnoreCase(type)){
            sql = "INSERT INTO Title (type,title_name,description,vault) ";
            sql += "VALUES ";
            sql += "('points',";
            sql += "'"+titleName+"',";
            sql += "'"+description+"',";
            sql += vault+",";
            sql += "true)";
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
        if("permission".equalsIgnoreCase(type)){
            sql = "INSERT INTO Title (type,title_name,description,permission) ";
            sql += "VALUES ";
            sql += "('permission',";
            sql += "'"+titleName+"',";
            sql += "'"+descripition+"',";
            sql += "'"+permission+"',";
            sql += "false)";
        }
        if(Sql().query(sql,sender)){
            sender.sendMessage("§2[TitlePlugin]创建成功!");
        }else{
            sender.sendMessage("§4[TitlePlugin]创建失败!");
        }
    }
}
