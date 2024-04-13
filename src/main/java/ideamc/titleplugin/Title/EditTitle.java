package ideamc.titleplugin.Title;

import ideamc.titleplugin.Date;
import ideamc.titleplugin.TitlePlugin;
import org.bukkit.command.CommandSender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static ideamc.titleplugin.TitlePlugin.Sql;

public class EditTitle {

    //permission和description和能否购买的修改
    public void edit(CommandSender sender, int title_id, String changetype, String a){
        if(changetype.equalsIgnoreCase("setpermission")){
            String sql = "UPDATE Title ";
            sql += "SET permission = " + a;
            sql += " WHERE title_id = " + title_id;
            if(Sql().query(sql,sender)){
                String sql1 = "SELECT COUNT(*) FROM PlayerTitle";
                sql1 += " WHERE title_id = " + title_id;
                ResultSet resultSet = Sql().readquery(sql1,sender);
                try {
                    if (Objects.requireNonNull(resultSet).next()) {
                        int count = resultSet.getInt(1);
                        if (count > 0) {
                            String sql2 = "DELETE FROM PlayerTitle";
                            sql2 += " WHERE title_id = " + title_id;
                            Sql().query(sql2,sender);
                        }
                    }
                } catch (SQLException ignored) {
                }
                sender.sendMessage("§2[TitlePlugin]修改成功!");
                sender.sendMessage("§2[TitlePlugin]称号ID" + title_id + "所需权限修改为" + a);
            }else{
                sender.sendMessage("§4[TitlePlugin]修改失败!");
            }
        }else if(changetype.equalsIgnoreCase("setdescription")){
            String sql = "UPDATE Title ";
            sql += "SET description = " + a;
            sql += " WHERE title_id = " + title_id;
            if(Sql().query(sql,sender)){
                sender.sendMessage("§2[TitlePlugin]修改成功!");
                sender.sendMessage("§2[TitlePlugin]称号ID" + title_id + "称号描述修改为" + a);
            }else{
                sender.sendMessage("§4[TitlePlugin]修改失败!");
            }
        }else if(changetype.equalsIgnoreCase("setcanbuy")){
            boolean canbuy = a.equalsIgnoreCase("true");
            String sql = "UPDATE Title ";
            sql += "SET canbuy = " + canbuy;
            sql += " WHERE title_id = " + title_id;
            if(Sql().query(sql,sender)){
                sender.sendMessage("§2[TitlePlugin]修改成功!");
                sender.sendMessage("§2[TitlePlugin]称号ID" + title_id + "能否购买修改为" + a);
            }else{
                sender.sendMessage("§4[TitlePlugin]修改失败!");
            }
        }
    }
    //硬币和点券和购买有效期和购买截止日期的修改
    public void edit(CommandSender sender, int title_id, String changetype, int a){
        if(changetype.equalsIgnoreCase("setcoin")){
            String sql = "UPDATE Title ";
            sql += "SET coin = " + a;
            sql += " WHERE title_id = " + title_id;
            if(Sql().query(sql,sender)){
                sender.sendMessage("§2[TitlePlugin]修改成功!");
                sender.sendMessage("§2[TitlePlugin]称号ID" + title_id + "购买所需金币修改为" + a);
            }else{
                sender.sendMessage("§4[TitlePlugin]修改失败!");
            }
        }else if(changetype.equalsIgnoreCase("setpoints")){
            String sql = "UPDATE Title ";
            sql += "SET playerpoints = " + a;
            sql += " WHERE title_id = " + title_id;
            if(Sql().query(sql,sender)){
                sender.sendMessage("§2[TitlePlugin]修改成功!");
                sender.sendMessage("§2[TitlePlugin]称号ID" + title_id + "购买所需点数修改为" + a);
            }else{
                sender.sendMessage("§4[TitlePlugin]修改失败!");
            }
        }else if(changetype.equalsIgnoreCase("setyouxiao")){
            String sql = "UPDATE Title ";
            sql += "SET expiration_date = " + a;
            sql += " WHERE title_id = " + title_id;
            if(Sql().query(sql,sender)){
                sender.sendMessage("§2[TitlePlugin]修改成功!");
                sender.sendMessage("§2[TitlePlugin]称号ID" + title_id + "单次购买有效期设置为" + a);
            }else{
                sender.sendMessage("§4[TitlePlugin]修改失败!");
            }
        }else if(changetype.equalsIgnoreCase("setjiezhi")){
            String jzrq = Date.addDaysToDate(Date.getCurrentDate(),a);
            boolean canbuy = true;
            if(Date.isDateLater(jzrq)){
                canbuy = false;
            }
            String sql = "UPDATE Title ";
            sql += "SET sale_end_date = " + jzrq;
            sql += ", canbuy = " + canbuy;
            sql += " WHERE title_id = " + title_id;
            if(Sql().query(sql,sender)){
                sender.sendMessage("§2[TitlePlugin]修改成功!");
                sender.sendMessage("§2[TitlePlugin]称号ID" + title_id + "修改后的售卖截止日期为" + jzrq);
            }else{
                sender.sendMessage("§4[TitlePlugin]修改失败!");
            }
        }
    }
}
