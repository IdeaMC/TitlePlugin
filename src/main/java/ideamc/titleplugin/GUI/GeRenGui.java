package ideamc.titleplugin.GUI;

import ideamc.titleplugin.TitlePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

import static ideamc.titleplugin.GUI.biyao.createGeRenTitleItem;
import static ideamc.titleplugin.GUI.biyao.readgerendatabase;
import static ideamc.titleplugin.TitlePlugin.Sql;

public class GeRenGui implements Listener {
    private static final int itemsPerPage = 45; // 每页45个物品
    private static int totalPages; //总页数
    private static int currentPage; //当前页
    private static Inventory gui;
    private static List<biyao.TitleData> titles;

    public GeRenGui(TitlePlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void showGeRenGui(CommandSender sender) {
        titles = readgerendatabase((Player) sender);
        totalPages = (titles.size() + itemsPerPage - 1) / itemsPerPage;
        currentPage = 0;

        gui = Bukkit.createInventory(null, 54, "个人称号");

        refillInventory((Player) sender);

    }

    private static void refillInventory(Player player) {
        // 清空当前Inventory
        if (gui != null) {
            gui.clear();
        }

        // 重新填充当前页的物品
        for (int i = currentPage * itemsPerPage; i < Math.min((currentPage + 1) * itemsPerPage, titles.size()); i++) {
            biyao.TitleData titleData = titles.get(i);
            ItemStack titleItem = createGeRenTitleItem(titleData);
            gui.addItem(titleItem);
        }

        // 保持导航按钮状态不变
        if (totalPages > 1) {
            //前一页item
            ItemStack previousPageItem = new ItemStack(Material.BOOK);
            ItemMeta previousPagemeta = previousPageItem.getItemMeta();
            previousPagemeta.setDisplayName("前一页");
            previousPageItem.setItemMeta(previousPagemeta);
            //后一页item
            ItemStack nextPageItem = new ItemStack(Material.BOOK);
            ItemMeta nextPagemeta = nextPageItem.getItemMeta();
            nextPagemeta.setDisplayName("后一页");
            nextPageItem.setItemMeta(nextPagemeta);

            if (currentPage == 0) {
                gui.setItem(50, nextPageItem);
            } else if (currentPage > 0) {
                gui.setItem(48, previousPageItem);
                gui.setItem(50, nextPageItem);
            } else if (currentPage == totalPages) {
                gui.setItem(48, previousPageItem);
            }
        }

        player.openInventory(gui); // 重新打开Inventory以更新视图
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        UUID plaer_uuid = player.getUniqueId();
        String stplayer_uuid = plaer_uuid.toString();
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory != null && clickedInventory.equals(gui)) {
            event.setCancelled(true); // 防止玩家直接拿取物品

            if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.NAME_TAG) {

                String stitle_id = event.getCurrentItem().getItemMeta().getDisplayName();
                int title_id = Integer.parseInt(stitle_id);

                if (event.isLeftClick()) {
                    String sql = "SELECT * FROM PlayerTitle WHERE player_uuid = '" + stplayer_uuid + "' AND title_id = '" + title_id + "'";
                    List<biyao.TitleData> rs = Sql().readquery(sql, player, "playertitle");
                    if (rs != null) {
                        for(biyao.TitleData t : rs){
                            boolean type = t.isPrefixEnable();
                            if (type) {
                                String sql1 = "UPDATE PlayerTitle ";
                                sql1 += "SET prefix_enable = false";
                                sql1 += " WHERE player_uuid = '" + stplayer_uuid + "'";
                                sql1 += " AND title_id = '" + title_id + "'";
                                if (Sql().eventquery(sql1)) {
                                    player.sendMessage("[TitlePlugin]§2前缀已禁用!");
                                    showGeRenGui(player);
                                } else {
                                    player.sendMessage("[TitlePlugin]§4前缀禁用失败!");
                                    player.closeInventory();
                                }
                            } else {
                                String sql1 = "UPDATE PlayerTitle ";
                                sql1 += "SET prefix_enable = true";
                                sql1 += " WHERE player_uuid = '" + stplayer_uuid + "'";
                                sql1 += " AND title_id = '" + title_id + "'";

                                String sql2 = "SELECT * FROM PlayerTitle WHERE player_uuid = '" + stplayer_uuid + "' AND prefix_enable = true";
                                List<biyao.TitleData> rs2 = Sql().readquery(sql2, player, "playertitle");
                                if(rs2 != null){
                                    for(biyao.TitleData t2 : rs2){
                                        if(t2.isPrefixEnable()){
                                            String sql3 = "UPDATE PlayerTitle ";
                                            sql3 += "SET prefix_enable = false";
                                            sql3 += " WHERE player_uuid = '" + stplayer_uuid + "'";
                                            sql3 += " AND title_id = '" + t2.getTitleId() + "'";
                                            if (Sql().query(sql3, player)) {

                                            } else {
                                                player.sendMessage("[TitlePlugin]§4禁用已启用前缀失败!");
                                            }
                                        }
                                    }
                                }
                                if (Sql().eventquery(sql1)) {
                                    player.sendMessage("[TitlePlugin]§2前缀已启用!");
                                    showGeRenGui(player);
                                } else {
                                    player.sendMessage("[TitlePlugin]§4前缀启用失败!");
                                   player.closeInventory();
                                }
                            }
                        }

                    }
                }else if (event.isRightClick()) {
                    String sql2 = "SELECT * FROM PlayerTitle WHERE player_uuid = '" + stplayer_uuid + "'" + " AND title_id = '" + title_id + "'";
                    List<biyao.TitleData> rs2 = Sql().readquery(sql2, player, "playertitle");
                    if (rs2 != null) {
                        for(biyao.TitleData t : rs2){
                            boolean type = t.isSuffixEnable();
                            if (type) {
                                String sql1 = "UPDATE PlayerTitle ";
                                sql1 += "SET suffix_enable = false";
                                sql1 += " WHERE player_uuid = '" + stplayer_uuid + "'";
                                sql1 += " AND title_id = '" + title_id + "'";
                                if (Sql().eventquery(sql1)) {
                                    player.sendMessage("[TitlePlugin]§2后缀已禁用!");
                                    showGeRenGui(player);
                                } else {
                                    player.sendMessage("[TitlePlugin]§4后缀已禁失败!");
                                    player.closeInventory();
                                }
                            } else {
                                String sql1 = "UPDATE PlayerTitle ";
                                sql1 += "SET suffix_enable = true";
                                sql1 += " WHERE player_uuid = '" + stplayer_uuid + "'";
                                sql1 += " AND title_id = '" + title_id + "'";

                                String s = "SELECT * FROM PlayerTitle WHERE player_uuid = '" + stplayer_uuid + "' AND suffix_enable = true";
                                List<biyao.TitleData> rss = Sql().readquery(s, player, "playertitle");
                                if(rss != null){
                                    for(biyao.TitleData st : rss){
                                        if(st.isPrefixEnable()){
                                            String sql4 = "UPDATE PlayerTitle ";
                                            sql4 += "SET suffix_enable = false";
                                            sql4 += " WHERE player_uuid = '" + stplayer_uuid + "'";
                                            sql4 += " AND title_id = '" + st.getTitleId() + "'";
                                            if (Sql().query(sql4, player)) {

                                            } else {
                                                player.sendMessage("[TitlePlugin]§4禁用已启用后缀失败!");
                                            }
                                        }
                                    }
                                }
                                if (Sql().eventquery(sql1)) {
                                    player.sendMessage("[TitlePlugin]§2后缀已启用!");
                                    showGeRenGui(player);
                                } else {
                                    player.sendMessage("[TitlePlugin]§4后缀启用失败!");
                                    player.closeInventory();
                                }
                            }
                        }
                    }
                }

                int slot = event.getRawSlot();

                if (slot == 48) { // 前一页按钮被点击
                    if (currentPage > 0) {
                        currentPage--;
                        refillInventory(player);
                    }
                } else if (slot == 50) { // 后一页按钮被点击
                    if (currentPage < totalPages - 1) {
                        currentPage++;
                        refillInventory(player);
                    }
                }
            }
        }
    }
}
