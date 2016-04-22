package me.daququ.common.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities for working with java {@link java.util.Date}s.
 */
public final class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_PATTERN_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_PATTERN_LONG_MS = "yyyyMMddHHmmssSSS";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String MM_DD_HH_MM = "MM月dd日 HH:mm";
    public static final String YYYYMM = "yyyyMM";
    public static final String HH_MM = "HH:mm";
    public static final String HH_MM_SS = "HH-mm-ss";
    public static final String MM_DD = "MM-dd";
    public static final String YYYY_MM_DD = "yyyy年MM月dd日";
    public static final String CHINESE_YYYY_MM_DD_HH_MM = "yyyy年MM月dd日  HH时mm分";
    public static final long DAY_IN_MILLISECOND = 24 * 60 * 60 * 1000;

    public static Date now() {
        return new Date();
    }

    public static String stringDateformFmt2Fmt(String dataStr, String form_fmt, String to_fmt) throws ParseException {
        Date d = DateUtils.parseDateStrictly(dataStr, form_fmt);
        return DateUtils.convertDateToString(d, to_fmt);
    }

    public static String now2yyyy_mm_dd() {
        FastDateFormat fdf = FastDateFormat.getInstance(DATE_PATTERN_YYYY_MM_DD);
        return fdf.format(new Date());
    }

    public static String now2yyyymm() {
        FastDateFormat fdf = FastDateFormat.getInstance(YYYYMM);
        return fdf.format(new Date());
    }

    public static String now2yyyy_mm_dd_hh_mm_ss() {
        FastDateFormat fdf = FastDateFormat.getInstance(YYYY_MM_DD_HH_MM_SS);
        return fdf.format(new Date());
    }

    public static String timeStamp2yyyy_mm_dd_hh_mm_ss(String data) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sd = sdf.format(new Date(Long.parseLong(data)));

        return sd;
    }


    public static long toUnixTime(Date date) {
        return toUnixTime(date.getTime());
    }

    public static long nowUnixTime() {
        return toUnixTime(System.currentTimeMillis());
    }

    public static long toUnixTime(long millisSinceEpoch) {
        return TimeUnit.MILLISECONDS.toSeconds(millisSinceEpoch);
    }

    public static Date ago(int calendarField, int amount) {
        return ago(now(), calendarField, amount);
    }

    public static Date ago(Date referenceDate, int calendarField, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(referenceDate);
        calendar.add(calendarField, -1 * amount);
        return calendar.getTime();
    }


    /**
     * 得到当前日期的时间戳
     *
     * @return
     */
    public static final long getTimeInMillis() {
        Date d = new Date();
        return d.getTime();
    }

    /**
     * 得到当前时间
     *
     * @return
     */
    public static final Date getCurrentDateTime() {
        return new Date();
    }

    /**
     * 得到当前时间的字符串
     *
     * @param pattern
     * @return
     * @throws java.text.ParseException
     */
    public static String getCurrentDateString(String pattern) throws ParseException {
        return convertDateToString(getCurrentDateTime(), pattern);
    }

    /**
     * 得到当前年份
     *
     * @param date
     * @return
     */
    public static final int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 得到当前月份
     *
     * @param date
     * @return
     */
    public static final int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 得到当前日期
     *
     * @param date
     * @return
     */
    public static final int getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 格式化时间戳为 2013-05-08 00:08:40 形式
     *
     * @param millisecond
     * @return
     */
    public static final String convertDateToString(Long millisecond) {
        return convertDateToString(millisecond, null);
    }

    /**
     * 格式化日期成字符串，  2013-05-08 00:08:40 形式
     *
     * @param date
     * @return
     */
    public static final String convertDateToString(Date date) {
        if (null == date) {
            return null;
        } else {
            return convertDateToString(date.getTime(), YYYY_MM_DD_HH_MM_SS);
        }
    }

    /**
     * 格式化日期成字符串， 按pattern
     *
     * @param date
     * @param pattern
     * @return
     */
    public static final String convertDateToString(Date date, String pattern) {
        FastDateFormat fdf = FastDateFormat.getInstance(pattern);
        return fdf.format(date);
    }

    /**
     * 格式化时间戳成日期字符串，按pattern
     *
     * @param millisecond
     * @param pattern
     * @return
     */
    public static final String convertDateToString(Long millisecond, String pattern) {

        FastDateFormat fdf = FastDateFormat.getInstance(pattern);
        return fdf.format(millisecond);

    }


    /**
     * 格式化字符串为日期对象，按pattern
     *
     * @param dateStr
     * @param pattern
     * @return
     * @throws java.text.ParseException
     */
    public static final Date convertStringToDate(String dateStr, String pattern)
            throws ParseException {
        if (null == dateStr || dateStr.equals("")) {
            return null;
        }
        if (null == pattern) {
            pattern = YYYY_MM_DD_HH_MM_SS;
        }
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.parse(dateStr);


    }

    /**
     * 获取指定时间的前一天
     *
     * @param date
     * @return
     * @throws java.text.ParseException
     */
    public static Date getBeforeDate(Date date) throws ParseException {
        return new Date(date.getTime() - 1000 * 3600 * 24);
    }

    /**
     * 获取指定时间的后一天
     *
     * @param date
     * @return
     */
    public static Date getAfterDate(Date date) {
        return new Date(date.getTime() + 1000 * 3600 * 24);
    }

    /**
     * 获取指定时间的前N天或后N天的时间
     *
     * @param time
     * @param daysNum
     * @return
     */
    public static Date getDayBeforeOrAfter(Date time, int daysNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + daysNum);
        return calendar.getTime();
    }

    /**
     * 获取指定时间的前N天或后N天的时间
     *
     * @param time
     * @param days
     * @return
     */
    public static Date getDayBeforeOrAfter2(Date time, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 计算2个日期相隔的天数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static long differDay(Date d1, Date d2) {
        int betweenDays = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            Calendar tmp = c1;
            c1 = c2;
            c2 = tmp;
        }
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        betweenDays = c2.get(Calendar.DAY_OF_YEAR)
                - c1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < betweenYears; i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
        }
        return Math.abs(betweenDays);
    }

    /**
     * 计算两个日期相隔的小时
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long differHour(Date startDate, Date endDate) {
        long time = endDate.getTime() - startDate.getTime();
        return time / (60 * 60 * 1000);
    }

    /**
     * 计算两个日期相隔的小时
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long differMinute(Date startDate, Date endDate) {
        long time = endDate.getTime() - startDate.getTime();

        return time / (60 * 1000);
    }


    /**
     * 计算某日期与当前日期相隔的小时
     *
     * @param fromDate
     * @return
     */
    public static long differHourWithCurrentTime(Date fromDate) {
        long time = new Date().getTime() - fromDate.getTime();
        return time / (60 * 60 * 1000);
    }

    /**
     * 得到当天日期
     *
     * @return
     */
    public static String getToday() {
        Date d = getCurrentDateTime();
        return convertDateToString(d, DATE_PATTERN_YYYY_MM_DD);
    }


    public static String beautifulDate(Date startTime, Date endTime) {

        String r = null;
        long minutes = DateUtils.differMinute(startTime, endTime);

        if (minutes == 0) {
            r = "刚刚";
        } else if (minutes < 60) {
            r = minutes + "分钟前";
        } else if (minutes < 60 * 24) {
            r = (minutes / 60) + "小时前";
        } else if (minutes < 60 * 24 * 30) {
            r = (minutes / (60 * 24)) + "天前";
        } else {
            r = DateUtils.convertDateToString(startTime, DateUtils.MM_DD_HH_MM);
        }
        return r;
    }

    public static String beautifulDate(Date startTime) {
        return beautifulDate(startTime, new Date());
    }


    public static String beautifulDate(String startTime) {
        try {
            return beautifulDate(parseDate(startTime, YYYY_MM_DD_HH_MM_SS), new Date());
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return "";
    }


    private DateUtils() {
        // utility
    }
}
