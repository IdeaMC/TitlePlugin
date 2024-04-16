package ideamc.titleplugin.GUI;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static ideamc.titleplugin.TitlePlugin.Sql;

public class biyao {
    public static List<TitleData> readshopdatabase(Player player) {
        List<TitleData> titles = new ArrayList<>();

        String sql = "SELECT * FROM Title";
        List<TitleData> rs = Sql().readeventquery(sql, "title");

        String sql1 = "SELECT * FROM PlayerTitle WHERE player_uuid = '" + player.getUniqueId().toString() + "'";
        List<TitleData> rs1 = Sql().readeventquery(sql1, "playertitle");
        Set<Integer> playerhvae = new HashSet<>();
        if(rs1 != null){
            for(TitleData t : rs1){
                playerhvae.add(t.getTitleId());
            }
        }
        if(rs != null){
            for(TitleData t : rs){
                if(!playerhvae.contains(t.getTitleId())){
                    int titleId = t.getTitleId();
                    String titleName = t.getTitleName();
                    String type = t.getType();
                    String description = t.getDescription();
                    int coin = t.getCoin();
                    int point = t.getPoints();
                    boolean canBuy = t.isCanBuy();
                    String permission = t.getPermission();
                    int youxiao = t.getYouxiao();
                    String saleEndDate = t.getSaleEndDate();

                    titles.add(new TitleData(titleId, titleName, type, description, coin, point, canBuy, permission, youxiao, saleEndDate, null, null, false, false));
                }
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
            lore.add("称号:" + titleData.getTitleName());
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
            lore.add("称号:" + titleData.getTitleName());
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
            lore.add("称号:" + titleData.getTitleName());
            lore.add("描述:" + titleData.getDescription());
            lore.add("限时活动获取");
            lore.add("不能购买");
        }else if(titleData.getType().equalsIgnoreCase("permission")){
            lore.add("称号:" + titleData.getTitleName());
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
        List<TitleData> rs = Sql().readeventquery(sql, "playertitle");
        if(rs != null){
            for(TitleData t : rs){
                int title_id = t.getTitleId();
                String sql1 = "SELECT * FROM Title WHERE title_id = '" + title_id + "'";
                List<TitleData> rs1 = Sql().readeventquery(sql1, "title");
                if(rs1 != null){
                    for(TitleData t1 : rs1){
                        int titleId = t1.getTitleId();
                        String titleName = t1.getTitleName();
                        String type = t1.getType();
                        String description = t1.getDescription();
                        int coin = t1.getCoin();
                        int point = t1.getPoints();
                        boolean canBuy = t1.isCanBuy();
                        String permission = t1.getPermission();
                        int youxiao = t1.getYouxiao();
                        String saleEndDate = t1.getSaleEndDate();
                        String playerUUID = t.getPlayerUUID();
                        String expireDate = t.getExpirationDate();
                        boolean prefixEnable = t.isPrefixEnable();
                        boolean suffixEnable = t.isSuffixEnable();

                        titles.add(new TitleData(titleId, titleName, type, description, coin, point, canBuy, permission, youxiao, saleEndDate, playerUUID, expireDate, prefixEnable, suffixEnable));
                    }
                }
            }
        }

        return titles;
    }

    public static List<TitleData> readlistdatabase(Player player) {
        List<TitleData> titles = new ArrayList<>();

        String sql = "SELECT * FROM Title";
        List<TitleData> rs = Sql().readeventquery(sql, "title");

        if(rs != null){
            for(TitleData t : rs){
                int titleId = t.getTitleId();
                String titleName = t.getTitleName();
                String type = t.getType();
                String description = t.getDescription();
                int coin = t.getCoin();
                int point = t.getPoints();
                boolean canBuy = t.isCanBuy();
                String permission = t.getPermission();
                int youxiao = t.getYouxiao();
                String saleEndDate = t.getSaleEndDate();

                titles.add(new TitleData(titleId, titleName, type, description, coin, point, canBuy, permission, youxiao, saleEndDate, null, null, false, false));
            }
        }else{
            player.sendMessage("§4[TitlePlugin]商店中没有称号!");
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

    //自定义数据类型
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
