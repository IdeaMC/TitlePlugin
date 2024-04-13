package ideamc.titleplugin;

/**
 * @author xiantiao
 * @date 2024/4/13
 * TitlePlugin
 */
public class Calculator {
    //加时间（获取当前日期+xday）返回到期日期

    public long getExpireTime(int day) {
        return System.currentTimeMillis() + (long) day *24*60*60*1000;
    }
}
