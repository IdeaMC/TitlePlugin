package ideamc.titleplugin.Vault;

import ideamc.titleplugin.TitlePlugin;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class coin {
    public boolean removecoin(String playername, int coin)
    {
        Economy economy = TitlePlugin.getEconomy();
        EconomyResponse r = economy.withdrawPlayer(playername, coin);
        Player player = Bukkit.getPlayer(playername);
        if(r.transactionSuccess()) {
            player.sendMessage(String.format("成功扣除金币 %s 剩余金币 %s", economy.format(r.amount), economy.format(r.balance)));
            return true;
        } else {
            player.sendMessage(String.format("发生错误: %s", r.errorMessage));
            return false;
        }
    }
}
