package ideamc.titleplugin.utils;

import ideamc.titleplugin.utils.interfaces.Title;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiantiao
 * @date 2024/4/12
 * TitlePlugin
 */
public class TitlePlayer {
    List<Title> titleList;
    List<Long> buyTimeList;

    public TitlePlayer() {
        this.titleList = new ArrayList<>();
    }

    public List<Title> getTitleList() {
        return null;
    }

    public void addTitle(Title title) {
        this.titleList.add(title);
    }

    public void removeTitle(Title title) {
        this.titleList.remove(title);
    }

    public void cleanUpExpired() {
        for (Title title : this.titleList) {
            if (title)
        }
    }
}
