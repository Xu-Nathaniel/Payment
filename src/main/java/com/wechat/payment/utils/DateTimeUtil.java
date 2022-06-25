package com.wechat.payment.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateTimeUtil {

  public static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
  public static final String YYYY_MM_FORMATTER = "yyyy-MM";
  public static final String YYYY_MM_DD_FORMATTER = "yyyy-MM-dd";
  public static final String ZONE_ID = "GMT+08:00";
  public static ZoneId DEFAULT_ZONE_ID = ZoneId.of(ZONE_ID);
  private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);

  /**
   * 日期时间格式化
   */
  public static String format(long time) {
    return formatter.format(ZonedDateTime.ofInstant(Instant.ofEpochMilli(time), DEFAULT_ZONE_ID));
  }

  /**
   * 今天开始的时间
   */
  public static ZonedDateTime startDayOfNow() {
    return ZonedDateTime.now(DEFAULT_ZONE_ID).truncatedTo(ChronoUnit.DAYS);
  }

  /**
   * 获取每周的第一天
   *
   * @param nowTime 当前时间
   */
  public static ZonedDateTime getFirstDayOfWeek(ZonedDateTime nowTime) {
    return nowTime.with(DayOfWeek.MONDAY).withHour(0).withMinute(0).withSecond(0).withNano(0);
  }

  /**
   * 获取每年的第一天
   * <p> if LocalDateTime 2019-03-23 return 2019-01-01 00:00 <p/>
   *
   * @param nowTime 当前时间
   */
  public static LocalDateTime getFirstDayOfYear(LocalDateTime nowTime) {
    return nowTime.withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
  }

  public static String format(LocalDateTime ldt) {
    return format(ldt, DATE_TIME_FORMATTER);
  }

  public static String format(LocalDateTime ldt, String formatter) {
    return ldt.format(DateTimeFormatter.ofPattern(formatter));
  }

  /**
   * 获取指定时间范围的月份
   */
  public static List<String> getTimeRange(String startData, String endDate) throws ParseException {
    List<String> result = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_FORMATTER);
    Calendar min = Calendar.getInstance();
    Calendar max = Calendar.getInstance();
    min.setTime(sdf.parse(startData));
    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
    max.setTime(sdf.parse(endDate));
    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
    while (min.before(max)) {
      result.add(sdf.format(min.getTime()));
      min.add(Calendar.MONTH, 1);
    }
    return result;
  }

  public static ZonedDateTime now() {
    return ZonedDateTime.now(DEFAULT_ZONE_ID);
  }

}
