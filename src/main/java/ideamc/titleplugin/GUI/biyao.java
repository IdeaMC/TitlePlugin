package ideamc.titleplugin.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ideamc.titleplugin.TitlePlugin.Sql;

public class biyao {
    public static List<TitleData> readdatabase(Player player) {
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
            }catch (Exception ignored){}
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

                        titles.add(new TitleData(titleId, titleName, type, description, coin, point, canBuy, permission, youxiao, saleEndDate));
                    }
                }
            }catch (Exception ignored){}
        }

        return titles;
    }

    public static ItemStack createTitleItem(TitleData titleData) {
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

        TitleData(int titleId, String titleName, String type, String description, int coin, int points, boolean canBuy, String permission, int youxiao, String saleEndDate) {
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

    }

}
