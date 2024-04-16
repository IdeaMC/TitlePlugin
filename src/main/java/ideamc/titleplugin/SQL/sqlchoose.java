package ideamc.titleplugin.SQL;

import ideamc.titleplugin.GUI.biyao;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * @author suxiaolin
 * &#064;date  2024/4/16
 * 数据库表名有 Title 和 PlayerTitle
 * TitlePlugin
 */
public interface sqlchoose {

    /**
     * 执行sql语句时调用
     *
     * @param sql sql语句
     * @param sender 玩家
     *
     * 返回boolean类型(成功/失败)
     */
    boolean query(String sql, CommandSender sender);
    /**
     * 执行sql语句时调用
     *
     * @param sql sql语句
     *
     * 返回boolean类型(成功/失败)
     */
    boolean eventquery(String sql);
    /**
     * 执行sql语句时调用
     *
     * @param sql sql语句
     * @param sender 玩家
     * @param table_name sql语句中的表名
     * select时一定要 select *
     * 返回TitleData类型的数据 具体见GUI文件夹里的biyao.java
     */
    List<biyao.TitleData> readquery(String sql, CommandSender sender, String table_name);
    /**
     * 执行sql语句时调用
     *
     * @param sql sql语句
     * @param sender 玩家
     * @param a 填1就行
     * 返回boolean类型(成功/失败)
     */
    boolean readquery (String sql, CommandSender sender, int a);
    /**
     * 执行sql语句时调用
     *
     * @param sql sql语句
     * @param table_name sql语句中的表名
     * select时一定要 select *
     * 返回TitleData类型的数据 具体见GUI文件夹里的biyao.java
     */
    List<biyao.TitleData> readeventquery(String sql, String table_name);
}
