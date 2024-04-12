package ideamc.titleplugin.Title;

import ideamc.titleplugin.SQL.Sql;
import org.bukkit.command.CommandSender;


// /ps create [type] [title名称] [coin/playerpoints/permission]
public class CreateTitle {
    //活动title的创建
    public void create(CommandSender sender, String type, String title_name, String description){
        String sql = null;
        if(type.equalsIgnoreCase("activity")){
            sql = "INSERT INTO Title (type,title_name,description,canbuy) ";
            sql += "VALUES ";
            sql += "('activity',";
            sql += "'"+title_name+"',";
            sql += "'"+description+"',";
            sql += "false)";
        }
        if(Sql.query(sql,sender)){
            sender.sendMessage("§2[TitlePlugin]创建成功!");
        }else{
            sender.sendMessage("§4l[TitlePlugin]创建失败!");
        }
    }
    //点券or金币title的创建
    public void create(CommandSender sender,String type,String title_name,String descripition,int vault){
        String sql =null;
        if(type.equalsIgnoreCase("coin")){
            sql = "INSERT INTO Title (type,title_name,description,vault) ";
            sql += "VALUES ";
            sql += "('coin',";
            sql += "'"+title_name+"',";
            sql += "'"+descripition+"',";
            sql += vault+",";
            sql += "true)";
        } else if(type.equalsIgnoreCase("points")){
            sql = "INSERT INTO Title (type,title_name,description,vault) ";
            sql += "VALUES ";
            sql += "('points',";
            sql += "'"+title_name+"',";
            sql += "'"+descripition+"',";
            sql += vault+",";
            sql += "true)";
        }
        if(Sql.query(sql,sender)){
            sender.sendMessage("§2[TitlePlugin]创建成功!");
        }else{
            sender.sendMessage("§4[TitlePlugin]创建失败!");
        }
    }
    //权限title的创建
    public void create(CommandSender sender,String type,String title_name,String descripition,String permission){
        String sql =null;
        if(type.equalsIgnoreCase("permission")){
            sql = "INSERT INTO Title (type,title_name,description,permission) ";
            sql += "VALUES ";
            sql += "('permission',";
            sql += "'"+title_name+"',";
            sql += "'"+descripition+"',";
            sql += "'"+permission+"',";
            sql += "false)";
        }
        if(Sql.query(sql,sender)){
            sender.sendMessage("§2[TitlePlugin]创建成功!");
        }else{
            sender.sendMessage("§4[TitlePlugin]创建失败!");
        }
    }
}
