package ideamc.titleplugin.GUI;

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

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

import static ideamc.titleplugin.GUI.biyao.createGeRenTitleItem;
import static ideamc.titleplugin.GUI.biyao.readgerendatabase;
import static ideamc.titleplugin.TitlePlugin.Sql;

public class GeRenGui implements Listener {
    private static final int itemsPerPage = 45; // 每页45个物品
    private static int totalPages;
    private static int currentPage;
    private static Inventory gui;
    private static List<biyao.TitleData> titles;

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

            if (clickedInventory.contains(Material.NAME_TAG)) {

                String stitle_id = clickedInventory.getName();
                int title_id = Integer.parseInt(stitle_id);

                if (event.getAction().toString().contains("LEFT")) {
                    String sql = "SELECT prefix_enable FROM PlayerTitle WHERE player_uuid = '" + stplayer_uuid + "' AND title_id = " + title_id;
                    ResultSet rs = Sql().readquery(sql, player);
                    if (rs != null) {
                        try {
                            while (rs.next()) {
                                boolean type = rs.getBoolean("prefix_enable");
                                if (type) {
                                    String sql1 = "UPDATE PlayerTitle ";
                                    sql1 += "SET prefix_enable = false";
                                    sql1 += " WHERE player_uuid = '" + stplayer_uuid + "'";
                                    sql1 += " AND title_id = " + title_id;
                                    if (Sql().eventquery(sql1)) {
                                        player.sendMessage("§2[TitlePlugin]前缀已禁用!");
                                    } else {
                                        player.sendMessage("§4[TitlePlugin]前缀已禁失败!");
                                    }
                                } else {
                                    String sql1 = "UPDATE PlayerTitle ";
                                    sql1 += "SET prefix_enable = true";
                                    sql1 += " WHERE player_uuid = '" + stplayer_uuid + "'";
                                    sql1 += " AND title_id = " + title_id;
                                    if (Sql().eventquery(sql1)) {
                                        player.sendMessage("§2[TitlePlugin]前缀已启用!");
                                    } else {
                                        player.sendMessage("§4[TitlePlugin]前缀启用失败!");
                                    }
                                }
                            }
                        } catch (Exception e) {
                            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4个人仓库前缀禁用错误!");
                        }
                    } else if (event.getAction().toString().contains("RIGHT")) {
                        String sql2 = "SELECT prefix_enable FROM PlayerTitle WHERE player_uuid = " + stplayer_uuid + " AND title_id = " + title_id;
                        ResultSet rs2 = Sql().readquery(sql2, player);
                        if (rs2 != null) {
                            try {
                                while (rs.next()) {
                                    boolean type = rs.getBoolean("suffix_enable");
                                    if (type) {
                                        String sql1 = "UPDATE PlayerTitle ";
                                        sql1 += "SET suffix_enable = false";
                                        sql1 += " WHERE player_uuid = '" + stplayer_uuid + "'";
                                        sql1 += " AND title_id = " + title_id;
                                        if (Sql().eventquery(sql1)) {
                                            player.sendMessage("§2[TitlePlugin]后缀已禁用!");
                                        } else {
                                            player.sendMessage("§4[TitlePlugin]后缀已禁失败!");
                                        }
                                    } else {
                                        String sql1 = "UPDATE PlayerTitle ";
                                        sql1 += "SET suffix_enable = true";
                                        sql1 += " WHERE player_uuid = " + stplayer_uuid;
                                        sql1 += " AND title_id = " + title_id;
                                        if (Sql().eventquery(sql1)) {
                                            player.sendMessage("§2[TitlePlugin]后缀已启用!");
                                        } else {
                                            player.sendMessage("§4[TitlePlugin]后缀启用失败!");
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4个人仓库后缀禁用错误!");
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
