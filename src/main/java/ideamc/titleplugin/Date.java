package ideamc.titleplugin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Date {
    //获取当前日期
    public static String getCurrentDate() {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 创建一个DateTimeFormatter实例，指定输出格式为"yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 使用formatter将当前日期格式化为字符串
        String formattedDate = currentDate.format(formatter);

        return formattedDate;
    }
    //计算当前日期与指定日期之间相差的天数
    public static int calculateDaysBetweenDates(String givenDate) {
        String dateFormat = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate date1 = LocalDate.parse(givenDate, formatter);//指定日期
        LocalDate date2 = LocalDate.now();//当前日期
        // 直接使用ChronoUnit.DAYS.between()方法计算相差天数
        return (int) Math.abs(ChronoUnit.DAYS.between(date1, date2));
    }
    //计算当前日期是否晚于指定日期
    public static boolean isDateLater(String givenDate) {
        String dateFormat = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate date1 = LocalDate.parse(givenDate, formatter);//指定日期
        LocalDate date2 = LocalDate.now();//当前日期
        return date1.isAfter(date2) || date1.isEqual(date2);
    }
    //计算指定日期加n天后的日期
    public static String addDaysToDate(String givenDate, int n) {
        String dateFormat = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate date = LocalDate.parse(givenDate, formatter);
        LocalDate newDate = date.plusDays(n);
        return newDate.format(formatter);
    }
}
