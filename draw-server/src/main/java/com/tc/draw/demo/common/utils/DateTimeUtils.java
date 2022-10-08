package com.tc.draw.demo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    private DateTimeUtils() {
    }

    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YY_MM_DD = "yy-MM-dd";

    public final static String DOT_YYYY_MM_DD_HH_MM_SS = "yyyy.MM.dd HH:mm:ss";
    public final static String DOT_YY_MM_DD_HH_MM_SS = "yy.MM.dd HH:mm:ss";
    public final static String DOT_YY_MM_DD = "yy.MM.dd";

    public static final String DOT_YYYY_MM_DD = "yyyy.MM.dd";

    public final static int ONE_DAY_MILL_TIMESTAMP = 86400000;


    /**
     * 获取今天星期几
     *
     * @param time 毫秒
     * @return int
     */
    public static int getDayOfWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获取在当前月份的第几天
     *
     * @return
     */
    public static int getDay() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取当前月份最大的天数
     *
     * @return
     */
    public static int getMaxDay() {
        Calendar cal = Calendar.getInstance();
        int Maxday = cal.getActualMaximum(Calendar.DATE);
        return Maxday;
    }


    /**
     * 获取某年第一天时间戳
     * @param year 年份
     * @return long
     */
    public static long getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst.getTime();
    }


    /**
     * 获取某年最后一天时间戳
     * @param year 年份
     * @return long
     */
    public static long getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date currYearLast = calendar.getTime();
        return currYearLast.getTime();
    }



    /**
     * 获取指定月份第一天时间戳
     * @param year 年份 month 月份
     * @return long
     */
    public static long getFirstDay(int year, int month) {
        // 获取Calendar类的实例
        Calendar c = Calendar.getInstance();
        // 设置年份
        c.set(Calendar.YEAR, year);
        // 设置月份，因为月份从0开始，所以用month - 1
        c.set(Calendar.MONTH, month - 1);
        // 设置日期
        c.set(Calendar.DAY_OF_MONTH, 1);

        return c.getTime().getTime();
    }

    /**
     * 获取指定月份最后一天时间戳
     * @param year 年份 month 月份
     * @return long
     */
    public static long getLastDay(int year, int month) {
        // 获取Calendar类的实例
        Calendar c = Calendar.getInstance();
        // 设置年份
        c.set(Calendar.YEAR, year);
        // 设置月份，因为月份从0开始，所以用month - 1
        c.set(Calendar.MONTH, month - 1);
        // 获取当前时间下，该月的最大日期的数字
        int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 将获取的最大日期数设置为Calendar实例的日期数
        c.set(Calendar.DAY_OF_MONTH, lastDay);
        return c.getTime().getTime();
    }




    /**
     * 获取当月第一天 (毫秒)
     *
     * @return
     * @author Jason
     * @date 2018年6月16日 下午1:18:18
     */
    public static long getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 将LocalDateTime转为自定义的时间格式的字符串
     *
     * @param localDateTime
     * @param format
     * @return
     * @author Jason
     * @date 2018年6月27日 下午8:18:14
     */
    public static String getDateTimeAsString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     *
     * @param timestamp 毫秒
     * @return
     * @author Jason
     * @date 2018年6月27日 下午8:17:33
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 将LocalDateTime转为long类型的timestamp
     *
     * @param localDateTime
     * @return long 毫秒秒
     * @author Jason
     * @date 2018年6月27日 下午8:17:29
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 毫秒时间戳转日期文本
     *
     * @param timestamp
     * @param format
     * @return
     * @author 鳏夫村村姑
     * @Description:
     */
    public static String getStringByTimestamp(long timestamp, String format) {
        LocalDateTime localDateTime = getDateTimeOfTimestamp(timestamp);
        return getDateTimeAsString(localDateTime, format);
    }

    /**
     * 日期文本转时间戳毫秒
     *
     * @param time
     * @param format
     * @return
     * @author 鳏夫村村姑
     * @Description:
     */
    public static long getTimestampByString(String time, String format) {
        LocalDateTime localDateTime = parseStringToDateTime(time, format);
        return getTimestampOfDateTime(localDateTime);
    }

    /**
     * 将某时间字符串转为自定义时间格式的LocalDateTime
     *
     * @param time
     * @param format
     * @return
     * @author Jason
     * @date 2018年6月27日 下午8:17:22
     */
    public static LocalDateTime parseStringToDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.from(LocalDate.parse(time, df).atStartOfDay());
    }

    /**
     * 获取本周第一天 (毫秒)
     */
    public static long getWeekFirstDay() {
    	DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime inputDate = LocalDateTime.now();
		TemporalAdjuster FIRST_OF_WEEK =
		TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()- DayOfWeek.MONDAY.getValue()));
		String weekStart = df.format(inputDate.with(FIRST_OF_WEEK));
		return getTimestampByString(weekStart,YYYY_MM_DD);
    }

    /**
     * 判断两个日期的时间差
     *
     * @param stime 毫秒
     * @param etime 毫秒
     * @return
     * @author Jason
     * @date 2018年6月27日 下午8:43:59
     */
    public static Period dateBetweenDMY(long stime, long etime) {
        LocalDate endDate = getDateTimeOfTimestamp(etime).toLocalDate();
        LocalDate startDate = getDateTimeOfTimestamp(stime).toLocalDate();
        Period between = Period.between(startDate, endDate);
        return between;
    }

    /**
     * 叠加下某个小时后的时间戳
     *
     * @param timestamp
     * @param hours
     * @return
     * @author Jason
     * @date 2018年6月28日 上午9:44:16
     */
    public static long plusHours(long timestamp, int hours) {
        LocalDateTime time = getDateTimeOfTimestamp(timestamp);
        LocalDateTime nextTime = time.plusHours(hours);
        return getTimestampOfDateTime(nextTime);
    }

    /**
     * 叠加下某天数后的时间戳
     *
     * @param timestamp 毫秒
     * @param days
     * @return
     * @author Jason
     * @date 2018年6月28日 上午9:44:54
     */
    public static long plusDays(long timestamp, int days) {
        LocalDateTime time = getDateTimeOfTimestamp(timestamp);
        LocalDateTime nextTime = time.plusDays(days);
        return getTimestampOfDateTime(nextTime);
    }
    /**
     * 叠加下某周数后的时间戳
     *
     * @param timestamp 毫秒
     * @param months
     * @return
     * @author Jason
     * @date 2018年6月28日 上午9:45:14
     */
    public static long plusWeeks(long timestamp, int week) {
        LocalDateTime time = getDateTimeOfTimestamp(timestamp);
        LocalDateTime nextTime = time.plusWeeks(week);
        return getTimestampOfDateTime(nextTime);
    }
    /**
     * 叠加下某月数后的时间戳
     *
     * @param timestamp 毫秒
     * @param months
     * @return
     * @author Jason
     * @date 2018年6月28日 上午9:45:14
     */
    public static long plusMonths(long timestamp, int months) {
        LocalDateTime time = getDateTimeOfTimestamp(timestamp);
        LocalDateTime nextTime = time.plusMonths(months);
        return getTimestampOfDateTime(nextTime);
    }

    /**
     * 叠加某年数后的时间戳
     *
     * @param timestamp 毫秒
     * @param years
     * @return
     * @author Jason
     * @date 2018年6月28日 上午9:45:33
     */
    public static long plusYears(long timestamp, int years) {
        LocalDateTime time = getDateTimeOfTimestamp(timestamp);
        LocalDateTime nextTime = time.plusYears(years);
        return getTimestampOfDateTime(nextTime);
    }

    /**
     * 昨日凌晨零点
     *
     * @return
     * @author 鳏夫村村姑
     * @Description:
     */
    public static long yesterdayBreak() {
        return dayBreak() - ONE_DAY_MILL_TIMESTAMP;
    }

    /**
     * 今日凌晨零点
     *
     * @return
     * @author 鳏夫村村姑
     * @Description:
     */
    public static long dayBreak() {
        long currentTimestamp = System.currentTimeMillis();
        String dateString = getStringByTimestamp(currentTimestamp, YYYY_MM_DD);
        long dayBreak = getTimestampByString(dateString + " 00:00:00", YYYY_MM_DD_HH_MM_SS);
        return dayBreak;
    }

    public static long dayBreak(long timestamp) {
    	 String dateString = getStringByTimestamp(timestamp, YYYY_MM_DD);
         long dayBreak = getTimestampByString(dateString + " 00:00:00", YYYY_MM_DD_HH_MM_SS);
         return dayBreak;
    }


    /**
     * 获取两个日期相差的月数
     * @param d2  较大的日期
     * @param d1  较小的日期
     * @return 如果d1>d2返回 月数差 否则返回0
     */
    public static String getMonthDiff(String d1, String d2)throws ParseException {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        //将String日期转换成date
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1=sdf.parse(d1);
        java.util.Date date2=sdf.parse(d2);
        c1.setTime(date1);
        c2.setTime(date2);

        //判断两个日期的大小
        if(c2.getTimeInMillis() < c1.getTimeInMillis()) return "0";
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-9-30   d2 = 2015-12-16
        int yearInterval = year2 - year1;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if(month2 < month1 || month1 == month2 && day2 < day1) yearInterval --;
        // 获取月数差值
        int monthInterval = (month2 + 12) - month1 ;
        if(day2 > day1) monthInterval ++;
        monthInterval %= 12;
        if((yearInterval * 12 + monthInterval) ==0){
            return ((int) ((c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000*3600*24))) +"天";
        }
        return (yearInterval * 12 + monthInterval) +"月";
    }

}
