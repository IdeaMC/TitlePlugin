package ideamc.titleplugin.Vault;

import ideamc.titleplugin.TitlePlugin;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class coin {
    public static boolean removecoin(String playername, int coin) {
        Player player = Bukkit.getPlayer(playername);

        Economy economy = TitlePlugin.getEconomy();
        EconomyResponse r = economy.withdrawPlayer(playername, coin);

        if(r.transactionSuccess()) {
            player.sendMessage(String.format("[TitlePlugin]§2购买成功,剩余金币 %s",economy.format(r.balance)));
            return true;
        } else {
            player.sendMessage(String.format("[TitlePlugin]§4发生错误: %s", r.errorMessage));
            return false;
        }
    }
}
