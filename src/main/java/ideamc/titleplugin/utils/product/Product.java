package ideamc.titleplugin.utils.product;

import org.bukkit.permissions.Permission;

/**
 * @author xiantiao
 * @date 2024/4/13
 * TitlePlugin
 *
 * 价格管理器
 */
public interface Product {
    // 该商品的类型

    // 设置价格与可以使用的时间(毫秒时间戳)
    void setVault(int vault, long time);
    void setPoint(int point, long time);
    void setPermission(Permission permission, long time);

    // 获取价格
    int vault();
    int point();
    Permission permission();

    // 获取该价格的可以使用的时间 Continuously
    long vaultContinuously();
    long pointContinuously();
    long permissionContinuously();
}
