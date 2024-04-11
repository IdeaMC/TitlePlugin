package ideamc.titleplugin;

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
        if (!setupEconomy() ) {
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4前置Vault未找到已禁用本插件!");
        }

        if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
            this.playerpointsAPI = PlayerPoints.getInstance().getAPI();
            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§a前置PlayerPoints已找到!");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

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
