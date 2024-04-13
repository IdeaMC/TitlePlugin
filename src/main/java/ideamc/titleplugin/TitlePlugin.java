package ideamc.titleplugin;

import ideamc.titleplugin.Command.AdminCommand;
import org.bukkit.Bukkit;
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
}
