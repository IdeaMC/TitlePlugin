package ideamc.titleplugin.SQL;

import org.bukkit.command.CommandSender;

import java.sql.ResultSet;

public interface sqlchoose {
    boolean query(String sql, CommandSender sender);
    ResultSet readquery(String sql, CommandSender sender);
}
