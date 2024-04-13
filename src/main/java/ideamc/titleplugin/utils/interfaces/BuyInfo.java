package ideamc.titleplugin.utils.interfaces;

/**
 * @author xiantiao
 * @date 2024/4/13
 * TitlePlugin
 */
public interface BuyInfo {
    long buyTime();
    BuyType getBuyType();
    enum BuyType {
        Vault,
        Point,
        Permission,
        Command
    }
}
