package ideamc.titleplugin.utils;

import ideamc.titleplugin.utils.product.Product;
import org.bukkit.permissions.Permission;

/**
 * @author xiantiao
 * @date 2024/4/13
 * TitlePlugin
 */
public class StandardPrice implements Product {
    int vault;
    int point;
    Permission permission;
    long vaultContinuously;
    long pointContinuously;
    long permissionContinuously;

    public StandardPrice() {
        this.vault = 0;
        this.point = 0;
        this.vaultContinuously = 0;
        this.pointContinuously = 0;
        this.permissionContinuously = 0;
    }

    @Override
    public void setVault(int vault, long time) {
        this.vault = vault;
        this.vaultContinuously = time;
    }

    @Override
    public void setPoint(int point, long time) {
        this.point = point;
        this.pointContinuously = time;
    }

    @Override
    public void setPermission(Permission permission, long time) {
        this.permission = permission;
        this.permissionContinuously = time;
    }

    @Override
    public int vault() {
        return this.vault;
    }

    @Override
    public int point() {
        return this.point;
    }

    @Override
    public Permission permission() {
        return this.permission;
    }

    @Override
    public long vaultContinuously() {
        return this.vaultContinuously;
    }

    @Override
    public long pointContinuously() {
        return this.pointContinuously;
    }

    @Override
    public long permissionContinuously() {
        return this.permissionContinuously;
    }
}
