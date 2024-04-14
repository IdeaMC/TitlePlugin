package ideamc.titleplugin.GUI;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static ideamc.titleplugin.GUI.biyao.readdatabase;

public class ShopGui {
    private final int itemsPerPage = 45; // 每页45个物品
    private int totalPages;
    private int currentPage;
    private Inventory gui;
    private List<biyao.TitleData> titles;
    public void showGui(CommandSender sender) {
        titles = readdatabase((Player) sender);
        totalPages = (titles.size() + itemsPerPage - 1) / itemsPerPage;
        currentPage = 0;

        gui = Bukkit.createInventory(null, InventoryType.CHEST, "Title Selection");

        refillInventory((Player) sender);


    }
    private void refillInventory(Player player) {
        // 清空当前Inventory
        if(gui !=null){
            gui.clear();
        }

        // 重新填充当前页的物品
        for (int i = currentPage * itemsPerPage; i < Math.min((currentPage + 1) * itemsPerPage, titles.size()); i++) {
            biyao.TitleData titleData = titles.get(i);
            ItemStack titleItem = biyao.createTitleItem(titleData);
            gui.addItem(titleItem);
        }

        // 保持导航按钮状态不变
        gui.setItem(itemsPerPage - 1, previousPageItem);
        gui.setItem(itemsPerPage, nextPageItem);

        player.openInventory(gui); // 重新打开Inventory以更新视图
    }
}
