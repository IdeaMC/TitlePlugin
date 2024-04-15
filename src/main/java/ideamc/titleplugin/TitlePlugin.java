package ideamc.titleplugin;

import ideamc.titleplugin.Command.AdminCommand;
import ideamc.titleplugin.Command.PlayerCommand;
import ideamc.titleplugin.Command.TabCommand;
import ideamc.titleplugin.Event.TitleSaleEndDateEvent;
import ideamc.titleplugin.Event.playerJoinEvent;
import ideamc.titleplugin.GUI.GeRenGui;
import ideamc.titleplugin.GUI.ShopGui;
import ideamc.titleplugin.SQL.Sql;
import ideamc.titleplugin.SQL.MySQL;
import ideamc.titleplugin.SQL.sqlchoose;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.black_ixx.playerpoints.PlayerPoints;

import static ideamc.titleplugin.Event.TitleSaleEndDateEvent.titlesaleendate;

public class TitlePlugin extends JavaPlugin {
    private static FileConfiguration config;
    private static Economy econ = null;
    private PlayerPointsAPI playerpointsAPI;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();

        if(config.getString("SQL.type").equalsIgnoreCase("mysql")){
            MySQL.MysqlConfig();
            MySQL.LoadMysql();
        }else{
            Sql.LoadSQLite();
        }

        new AdminCommand(this);
        new PlayerCommand(this);
        new TabCommand(this);

        //挂钩vault
        if (!setupEconomy() ) {
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4前置Vault未找到,金币购买功能无法使用!");
        }else{
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2前置Vault已找到!");
        }
        //挂钩playerpoints
        if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
            this.playerpointsAPI = PlayerPoints.getInstance().getAPI();
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2前置PlayerPoints已找到!");
        }else{
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4前置PlayerPoints未找到,点券购买功能无法使用!");
        }
        //挂钩papi
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new Papi(this).register();
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2前置PlaceholderAPI已找到!");
        }else{
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4前置PlaceholderAPI未找到,变量功能无法使用!");
        }

        new ShopGui(this);
        new GeRenGui(this);
        new playerJoinEvent(this);
        new TitleSaleEndDateEvent();
        titlesaleendate();

    }

    @Override
    public void onDisable() {
    }

    //检测vault是否存在并注册
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static PlayerPointsAPI getPlayerPointsAPI() {
        return getPlayerPointsAPI();
    }

    public static FileConfiguration getconfig() {
        return config;
    }

    public static sqlchoose Sql(){
        if(config.getString("SQL.type").equalsIgnoreCase("mysql")){
            return new MySQL();
        }else{
            return new Sql();
        }
    }
}
