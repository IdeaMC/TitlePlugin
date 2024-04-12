package ideamc.titleplugin.Title;

import ideamc.titleplugin.SQL.Sql;
import org.bukkit.command.CommandSender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class EditTitle {
    //permission和description的修改
    public void edit(CommandSender sender, int titleId, String changetype, String a){
        if("setpermission".equalsIgnoreCase(changetype)){
            String sql = "UPDATE Title ";
            sql += "SET permission = " + a;
            sql += " WHERE title_id = " + titleId;
            if(Sql.query(sql,sender)){
                String sql1 = "SELECT COUNT(*) FROM PlayerTitle";
                sql1 += " WHERE title_id = " + titleId;
                ResultSet resultSet = Sql.readquery(sql1,sender);
                try {
                    if (Objects.requireNonNull(resultSet).next()) {
                        int count = resultSet.getInt(1);
                        if (count > 0) {
                            String sql2 = "DELETE FROM PlayerTitle";
                            sql2 += " WHERE title_id = " + titleId;
                            Sql.query(sql2,sender);
                        }
                    }
                } catch (SQLException ignored) {
                }
                sender.sendMessage("§2[TitlePlugin]修改成功!");
            }else{
                sender.sendMessage("§4[TitlePlugin]修改失败!");
            }
        }else if("setdescription".equalsIgnoreCase(changetype)){
            String sql = "UPDATE Title ";
            sql += "SET description = " + a;
            sql += " WHERE title_id = " + titleId;
            if(Sql.query(sql,sender)){
                sender.sendMessage("§2[TitlePlugin]修改成功!");
            }else{
                sender.sendMessage("§4[TitlePlugin]修改失败!");
            }
        }
    }

    public void edit(CommandSender sender, int titleId, String changetype, int a){
        if("setcoin".equalsIgnoreCase(changetype)){
            String sql = "UPDATE Title ";
            sql += "SET coin = " + a;
            sql += " WHERE title_id = " + titleId;
            if(Sql.query(sql,sender)){
                sender.sendMessage("§2[TitlePlugin]修改成功!");
            }else{
                sender.sendMessage("§4[TitlePlugin]修改失败!");
            }
        }else if("setpoints".equalsIgnoreCase(changetype)){
            String sql = "UPDATE Title ";
            sql += "SET playerpoints = " + a;
            sql += " WHERE title_id = " + titleId;
            // TODO
        }
    }
}
