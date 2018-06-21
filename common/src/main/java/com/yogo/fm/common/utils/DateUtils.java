package com.yogo.fm.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author qiqiang
 * @Description 日期相关工具类
 * @date 2018-05-28
 */
public class DateUtils {

    public enum Type {
        YEAR, MONTH, DAY, HOUR, MINUTE, SECOND
    }

    /**
     * 获取当前时间的 年、月、日、时、分、秒
     *
     * @param type
     * @return
     */
    public static int nowDate(Type type) {
        if (type == null) {
            return 0;
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        int result;
        switch (type) {
            case YEAR:
                result = localDateTime.getYear();
                break;
            case MONTH:
                result = localDateTime.getMonthValue();
                break;
            case DAY:
                result = localDateTime.getDayOfMonth();
                break;
            case HOUR:
                result = localDateTime.getHour();
                break;
            case MINUTE:
                result = localDateTime.getMinute();
                break;
            case SECOND:
                result = localDateTime.getSecond();
                break;
            default:
                result = 0;
        }
        return result;
    }

    /**
     * 根据 需要加减的数字，类型和日期对日期进行修改
     *
     * @param num  可以为负数
     * @param type
     * @return
     */
    public static Date plus(long num, Type type, Date date) {
        if (type == null) {
            return date;
        }
        if (date == null) {
            date = new Date();
        }
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        switch (type) {
            case YEAR:
                localDateTime.plus(num, ChronoUnit.YEARS);
                break;
            case MONTH:
                localDateTime.plus(num, ChronoUnit.MONTHS);
                break;
            case DAY:
                localDateTime.plus(num, ChronoUnit.DAYS);
                break;
            case HOUR:
                localDateTime.plus(num, ChronoUnit.HOURS);
                break;
            case MINUTE:
                localDateTime.plus(num, ChronoUnit.MINUTES);
                break;
            case SECOND:
                localDateTime.plus(num, ChronoUnit.SECONDS);
                break;
            default:
        }
        instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 将时间按格式转字符串
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date,String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime().format(formatter);
    }
    public static String dateToString(Date date){
        return dateToString(date,"YYYY-MM-dd HH:mm:ss");
    }

    public static void main(String[] args) {
        System.out.println(dateToString(new Date(),"YYYY-MM-dd HH:mm:ss"));
    }
}
