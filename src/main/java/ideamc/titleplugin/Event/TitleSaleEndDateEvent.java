package ideamc.titleplugin.Event;

import ideamc.titleplugin.GUI.biyao;
import org.bukkit.Bukkit;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static ideamc.titleplugin.Date.isDateLater;
import static ideamc.titleplugin.TitlePlugin.Sql;

public class TitleSaleEndDateEvent {

    public TitleSaleEndDateEvent(){
        Calendar targetTime1 = Calendar.getInstance();
        targetTime1.set(Calendar.HOUR_OF_DAY, 12);
        targetTime1.set(Calendar.MINUTE, 0);
        targetTime1.set(Calendar.SECOND, 0);
        targetTime1.set(Calendar.MILLISECOND, 0);

        Calendar targetTime2 = Calendar.getInstance();
        targetTime2.set(Calendar.HOUR_OF_DAY, 0);
        targetTime2.set(Calendar.MINUTE, 0);
        targetTime2.set(Calendar.SECOND, 0);
        targetTime2.set(Calendar.MILLISECOND, 0);

        Calendar targetTime3 = Calendar.getInstance();
        targetTime3.set(Calendar.HOUR_OF_DAY, 0);
        targetTime3.set(Calendar.MINUTE, 0);
        targetTime3.set(Calendar.SECOND, 0);
        targetTime3.set(Calendar.MILLISECOND, 0);

        // 如果当前时间已超过目标时间，将目标时间设置为明天的同一时刻
        if (targetTime1.before(Calendar.getInstance())) {
            targetTime1.add(Calendar.DATE, 1);
        }

        if (targetTime2.before(Calendar.getInstance())) {
            targetTime2.add(Calendar.DATE, 1);
        }

        if (targetTime3.before(Calendar.getInstance())) {
            targetTime3.add(Calendar.DATE, 1);
        }

        // 创建一个Timer实例
        Timer timer1 = new Timer();
        Timer timer2 = new Timer();
        Timer timer3 = new Timer();

        // 创建一个定时任务（匿名内部类）
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                titlesaleendate();
            }
        };

        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                titlesaleendate();
            }
        };

        TimerTask task3 = new TimerTask() {
            @Override
            public void run() {
                titlesaleendate();
            }
        };

        // 计划任务，从现在开始每天的指定时间执行
        timer1.scheduleAtFixedRate(task1, targetTime1.getTime(), 24 * 60 * 60 * 1000); // 每隔一天（毫秒）
        timer2.scheduleAtFixedRate(task2, targetTime2.getTime(), 24 * 60 * 60 * 1000);
        timer3.scheduleAtFixedRate(task3, targetTime3.getTime(), 24 * 60 * 60 * 1000);
    }

    public static void titlesaleendate(){
            String sql = "SELECT * FROM Title";
            List<biyao.TitleData> rs = Sql().readeventquery(sql, "title");
            if(rs != null){
                for(biyao.TitleData t : rs){
                    int title_id = t.getTitleId();
                    String end_date = t.getSaleEndDate();
                    if(end_date != null){
                        if(isDateLater(end_date)){
                            String sql1 = "UPDATE Title";
                            sql1 += " SET canbuy = false";
                            sql1 += " WHERE title_id = " + title_id;
                            if(Sql().eventquery(sql1)){
                                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§2称号ID为" + title_id + "的称号已到截止日期,已修改为不能购买!");
                            }else{
                                Bukkit.getConsoleSender().sendMessage("[TitlePlugin]§4称号ID为" + title_id + "的称号到截止日期时修改为不能购买失败!");
                            }
                        }
                    }
                }
            }
        }
}
