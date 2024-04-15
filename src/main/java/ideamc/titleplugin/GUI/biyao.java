package ideamc.titleplugin.GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.ResultSet;
import java.util.*;

import static ideamc.titleplugin.TitlePlugin.Sql;

public class biyao {
    public static List<TitleData> readshopdatabase(Player player) {
        List<TitleData> titles = new ArrayList<>();

        String sql = "SELECT * FROM Title";
        ResultSet rs = Sql().readeventquery(sql);

        String sql1 = "SELECT title_id FROM PlayerTitle WHERE player_uuid = '" + player.getUniqueId().toString() + "'";
        ResultSet rs1 = Sql().readeventquery(sql1);
        Set<Integer> playerhvae = new HashSet<>();
        if(rs1 != null){
            try {
                while (rs1.next()) {
                    playerhvae.add(rs1.getInt("title_id"));
                }
            }catch (Exception e){
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4商店读取个人数据库错误");
            }
        }
        if(rs != null){
            try{
                while (rs.next()) {
                    if(!playerhvae.contains(rs.getInt("title_id"))){
                        int titleId = rs.getInt("title_id");
                        String titleName = rs.getString("title_name");
                        String type = rs.getString("type");
                        String description = rs.getString("description");
                        int coin = rs.getInt("vault");
                        int point = rs.getInt("playerpoints");
                        boolean canBuy = rs.getBoolean("canbuy");
                        String permission = rs.getString("permission");
                        int youxiao = rs.getInt("youxiao");
                        String saleEndDate = rs.getString("sale_end_date");

                        titles.add(new TitleData(titleId, titleName, type, description, coin, point, canBuy, permission, youxiao, saleEndDate, null, false, false));
                    }
                }
            }catch (Exception e){
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4商店读取数据库错误!" + e.getMessage());
            }
        }else{
            player.sendMessage("§4[TitlePlugin]商店中没有称号!");
        }

        return titles;
    }

    public static ItemStack createShopTitleItem(TitleData titleData) {
        ItemStack item = new ItemStack(Material.NAME_TAG);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(String.valueOf(titleData.getTitleId()));

        List<String> lore = new ArrayList<>();

        if(titleData.getType().equalsIgnoreCase("coin")){
            lore.add("描述:" + titleData.getDescription());
            lore.add("价格:" + titleData.getCoin() + "金币");
            if(titleData.getYouxiao() != 0){
                lore.add("有效期:" + titleData.getYouxiao() + "天");
            }
            if(titleData.getSaleEndDate() != null){
                lore.add("购买截止日期:" + titleData.getSaleEndDate());
            }
            lore.add("能否购买:" + (titleData.isCanBuy() ? "能" : "不能"));
            lore.add("点击左键以购买");
        }else if(titleData.getType().equalsIgnoreCase("points")){
            lore.add("描述:" + titleData.getDescription());
            lore.add("价格:" + titleData.getCoin() + "点券");
            if(titleData.getYouxiao() != 0){
                lore.add("有效期:" + titleData.getYouxiao() + "天");
            }
            if(titleData.getSaleEndDate() != null){
                lore.add("购买截止日期:" + titleData.getSaleEndDate());
            }
            lore.add("能否购买:" + (titleData.isCanBuy() ? "能" : "不能"));
            lore.add("点击左键以购买");
        }else if(titleData.getType().equalsIgnoreCase("activity")){
            lore.add("描述:" + titleData.getDescription());
            lore.add("限时活动获取");
            lore.add("不能购买");
        }else if(titleData.getType().equalsIgnoreCase("permission")){
            lore.add("描述:" + titleData.getDescription());
            lore.add("所需权限:" + titleData.getPermission());
            lore.add("不能购买");
        }

        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

    public static List<TitleData> readgerendatabase(Player player) {
        UUID player_uuid = player.getUniqueId();
        String stplayer_uuid = player_uuid.toString();

        List<TitleData> titles = new ArrayList<>();

        String sql = "SELECT * FROM PlayerTitle WHERE player_uuid = " + "'" + stplayer_uuid + "'";
        ResultSet rs = Sql().readeventquery(sql);
        if(rs != null){
            try{
                while (rs.next()) {
                    int title_id = rs.getInt("title_id");
                    String sql1 = "SELECT * FROM Title WHERE title_id = '" + title_id + "'";
                    ResultSet rs1 = Sql().readeventquery(sql1);
                    if(rs1 != null){
                        try{
                            while (rs1.next()){
                                int titleId = rs1.getInt("title_id");
                                String titleName = rs1.getString("title_name");
                                String type = rs1.getString("type");
                                String description = rs1.getString("description");
                                int coin = rs1.getInt("vault");
                                int point = rs1.getInt("playerpoints");
                                boolean canBuy = rs1.getBoolean("canbuy");
                                String permission = rs1.getString("permission");
                                int youxiao = rs1.getInt("youxiao");
                                String saleEndDate = rs1.getString("sale_end_date");
                                String expireDate = rs.getString("expire_date");
                                boolean prefixEnable = rs.getBoolean("prefix_enable");
                                boolean suffixEnable = rs.getBoolean("suffix_enable");

                                titles.add(new TitleData(titleId, titleName, type, description, coin, point, canBuy, permission, youxiao, saleEndDate, expireDate, prefixEnable, suffixEnable));
                            }
                        }catch (Exception e){
                            Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4个人仓库读取数据库错误2!");
                        }
                    }
                }
            }catch (Exception e){
                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4个人仓库读取数据库错误1!");
            }
        }

        return titles;
    }

    public static ItemStack createGeRenTitleItem(TitleData titleData) {
        ItemStack item = new ItemStack(Material.NAME_TAG);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(String.valueOf(titleData.getTitleId()));

        List<String> lore = new ArrayList<>();

        lore.add("称号名:" + titleData.getTitleName());
        lore.add("描述:" + titleData.getDescription());
        if(titleData.getExpirationDate() != null){
            lore.add("有效期至:" + titleData.getExpirationDate());
        }
        lore.add("前缀:" + (titleData.isPrefixEnable() ? "启用" : "禁用"));
        lore.add("后缀:" + (titleData.isSuffixEnable() ? "启用" : "禁用"));
        lore.add("左键启用/禁用前缀");
        lore.add("右键启用/禁用后缀");

        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

    public static class TitleData {
        int titleId;
        String titleName;
        String type;
        String description;
        int coin;
        int points;
        boolean canBuy;
        String permission;
        int youxiao;
        String saleEndDate;
        String playerUUID;
        String expirationDate;
        boolean prefixEnable;
        boolean suffixEnable;

        public TitleData(int titleId, String titleName, String type, String description, int coin, int points, boolean canBuy, String permission, int youxiao, String saleEndDate, String playerUUID, String expirationDate, boolean prefixEnable, boolean suffixEnable) {
            this.titleId = titleId;
            this.titleName = titleName;
            this.type = type;
            this.description = description;
            this.coin = coin;
            this.points = points;
            this.canBuy = canBuy;
            this.permission = permission;
            this.youxiao = youxiao;
            this.saleEndDate = saleEndDate;
            this.playerUUID = playerUUID;
            this.expirationDate = expirationDate;
            this.prefixEnable = prefixEnable;
            this.suffixEnable = suffixEnable;
        }
        public int getTitleId() {
            return titleId;
        }
        public String getTitleName() {
            return titleName;
        }
        public String getType() {
            return type;
        }
        public String getDescription() {
            return description;
        }
        public int getCoin() {
            return coin;
        }
        public int getPoints() {
            return points;
        }
        public boolean isCanBuy() {
            return canBuy;
        }
        public String getPermission() {
            return permission;
        }
        public int getYouxiao() {
            return youxiao;
        }
        public String getSaleEndDate() {
            return saleEndDate;
        }
        public String getPlayerUUID() {
            return playerUUID;
        }
        public String getExpirationDate() {
            return expirationDate;
        }
        public boolean isPrefixEnable() {
            return prefixEnable;
        }
        public boolean isSuffixEnable() {
            return suffixEnable;
        }

    }

}
