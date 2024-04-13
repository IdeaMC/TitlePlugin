package ideamc.titleplugin.utils;

import ideamc.titleplugin.utils.product.Product;
import ideamc.titleplugin.utils.interfaces.Title;

/**
 * @author xiantiao
 * @date 2024/4/12
 * TitlePlugin
 */
public class TitleIm implements Title {
    long createTime;
    TitleType titleType;
    Product price;
    String display;
    public TitleIm(TitleType titleType) {
        this.createTime = System.currentTimeMillis();
        this.titleType = titleType;
        this.price = new StandardPrice();
    }

    @Override
    public long createTime() {
        return this.createTime;
    }

    @Override
    public String display() {
        return this.display;
    }

    @Override
    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public Product price() {
        return this.price;
    }

    @Override
    public TitleType getType() {
        return this.titleType;
    }
}
