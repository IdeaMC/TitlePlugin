package ideamc.titleplugin.SQL;

import ideamc.titleplugin.GUI.biyao;
import org.bukkit.command.CommandSender;

import java.sql.ResultSet;
import java.util.List;

public interface sqlchoose {
    boolean query(String sql, CommandSender sender);
    boolean eventquery(String sql);
    List<biyao.TitleData> readquery(String sql, CommandSender sender, String table_name);
    List<biyao.TitleData> readeventquery(String sql, String table_name);
}
