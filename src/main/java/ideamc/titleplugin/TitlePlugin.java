package ideamc.titleplugin;

import ideamc.titleplugin.Command.AdminCommand;
import ideamc.titleplugin.utils.TitleIm;
import ideamc.titleplugin.utils.TitlePlayer;
import ideamc.titleplugin.utils.TitleType;
import ideamc.titleplugin.utils.interfaces.Title;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.black_ixx.playerpoints.PlayerPoints;

public class TitlePlugin extends JavaPlugin {

    private static Economy econ = null;
    private PlayerPointsAPI playerpointsAPI;

    @Override
    public void onEnable() {
        new AdminCommand(this);
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


    // TODO 这是个测试方法
    protected static void test() {
        // 创建一个前缀类型的Title
        Title title = new TitleIm(TitleType.PREFIX);
        // 设置Title的显示文字
        title.setDisplay("[IdeaMC]");
        //设置Vault购买价格 500 3600天
        title.price().setVault(500,311040000000L);
        //设置Point购买价格 100 3600天
        title.price().setPoint(500,311040000000L);
        //设置Permission购买 3600天
        title.price().setPermission(new Permission("ideamc.admin"),311040000000L);
        
        
        TitlePlayer titlePlayer = new TitlePlayer();
        titlePlayer.addTitle(title);

    }
}
