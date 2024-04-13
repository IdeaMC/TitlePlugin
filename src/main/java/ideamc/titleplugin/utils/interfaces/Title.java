package ideamc.titleplugin.utils.interfaces;

import ideamc.titleplugin.utils.TitleType;
import ideamc.titleplugin.utils.product.Product;

/**
 * @author xiantiao
 * @date 2024/4/12
 * TitlePlugin
 *
 * 这个类用于管理单个Title
 */
public interface Title {
    long createTime();

    String display();

    void setDisplay(String display);

    Product price();

    TitleType getType();
}
