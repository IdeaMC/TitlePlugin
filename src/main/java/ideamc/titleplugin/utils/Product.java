package ideamc.titleplugin.utils;

import org.bukkit.permissions.Permission;

/**
 * @author xiantiao
 * @date 2024/4/13
 * TitlePlugin
 */
public interface Product {
    long createTime();

    int vault();
    void setVault(int vault);

    int point();
    void setPoint(int point);

    Permission permission();
    void setPermission(Permission permission);

    boolean canBuy();
    void setCanBuy(boolean canBuy);
}
