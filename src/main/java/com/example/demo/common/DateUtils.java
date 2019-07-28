package com.example.demo.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @description  日期相关操作
 * @author jay_he
 * @create 2015-10-23
 */
public class DateUtils {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Date parseToDate(String s) throws ParseException {
		return sdf.parse(s);
	}
	
	public static Date parseToDate(String s,String format) throws ParseException {
		SimpleDateFormat sdfdefind = new SimpleDateFormat(format);
		return sdfdefind.parse(s);
	}
	
	public static String ParseToString(Date date)
	{
		return sdf.format(date);
	}
	
	public static String ParseToString(Date date,String format)
	{
		SimpleDateFormat sdfdefind = new SimpleDateFormat(format);
		return sdfdefind.format(date);
	}
	
	//当前日期的开始时间，即当前日的0点0分
	public static Date getDayBegin(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	//当前日期的结束时间，即当前日的23点59分
	public static Date getDayEnd(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	
	//当前日期所在周的第一天(默认一周是从周日到周六)
	public static Date getWeekBegin(Date date){
		Calendar c = Calendar.getInstance();
		date = getDayBegin(date);
		c.setTime(date);
		int curdow =c.get(Calendar.DAY_OF_WEEK);
		int curdoy =c.get(Calendar.DAY_OF_YEAR);
		curdoy = curdoy - curdow + 1;
		c.set(Calendar.DAY_OF_YEAR, curdoy);
		
		return c.getTime();
	}
	
	//当前日期所在周的第七天(默认一周是从周日到周六)
	public static Date getWeekEnd(Date date){
		Calendar c = Calendar.getInstance();
		date = getDayEnd(date);
		c.setTime(date);
		int curdow =c.get(Calendar.DAY_OF_WEEK);
		int curdoy =c.get(Calendar.DAY_OF_YEAR);
		curdoy =  curdoy - curdow + 7;
		c.set(Calendar.DAY_OF_YEAR,curdoy);
		
		return c.getTime();
	}
	
	//当前日期所在的月的第一天
	public static Date getMonthBegin(Date date){
		Calendar c = Calendar.getInstance();
		date = getDayBegin(date);
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}
	
	//当前日期所在月的最后一天
	public static Date getMonthEnd(Date date){
		Calendar c = Calendar.getInstance();
		date = getDayEnd(date);
		c.setTime(date);
		int curmon = c.get(Calendar.MONTH);
		c.set(Calendar.MONTH, curmon + 1);//加一个月
		c.set(Calendar.DAY_OF_MONTH, 1);//加一个月后的第一天
		int curdoy = c.get(Calendar.DAY_OF_YEAR);
		c.set(Calendar.DAY_OF_YEAR, curdoy - 1);//上一天
		return c.getTime();
	}
	
	//当前日期所在季度的第一天
	public static Date getSeasonBegin(Date date){
		Calendar c = Calendar.getInstance();
		date = getDayBegin(date);
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch(month){
		case 0:
		case 1:
		case 2:{
			c.set(Calendar.MONTH, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);
			break;
		}
		case 3:
		case 4:
		case 5:{
			c.set(Calendar.MONTH, 3);
			c.set(Calendar.DAY_OF_MONTH, 1);
			break;
		}
		case 6:
		case 7:
		case 8:{
			c.set(Calendar.MONTH, 6);
			c.set(Calendar.DAY_OF_MONTH, 1);
			break;
		}
		case 9:
		case 10:
		case 11:{
			c.set(Calendar.MONTH, 9);
			c.set(Calendar.DAY_OF_MONTH, 1);
			break;
		}
		}
		return c.getTime();
	}
	
	//当前日期所在季度的最后一天
	public static Date getSeasonEnd(Date date){
		Calendar c = Calendar.getInstance();
		date = getDayEnd(date);
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch(month){
		case 0:
		case 1:
		case 2:{
			c.set(Calendar.MONTH, 3);
			c.add(Calendar.DAY_OF_YEAR, -1);
			break;
		}
		case 3:
		case 4:
		case 5:{
			c.set(Calendar.MONTH, 5);
			c.set(Calendar.DAY_OF_MONTH, 31);
			break;
		}
		case 6:
		case 7:
		case 8:{
			c.set(Calendar.MONTH, 9);
			c.set(Calendar.DAY_OF_MONTH, 30);
			break;
		}
		case 9:
		case 10:
		case 11:{
			c.set(Calendar.MONTH, 11);
			c.set(Calendar.DAY_OF_MONTH, 31);
			break;
		}
		}
		return c.getTime();
	}
	
	//当前日期所在年的第一天
	public static Date getYearBegin(Date date){
		Calendar c = Calendar.getInstance();
		date = getDayBegin(date);
		c.setTime(date);
		c.set(Calendar.DAY_OF_YEAR,1);//第一天
		return c.getTime();
	}
	
	//当前日期所在年的最后一天
	public static Date getYearEnd(Date date){
		Calendar c = Calendar.getInstance();
		date = getDayEnd(date);
		c.setTime(date);
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DAY_OF_MONTH, 31);
		return c.getTime();
	}
	
	/*
	 * 获取当前时间的间隔天数
	 */
	public static Date addDay(Date date,int day){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, day);
		
		return c.getTime();
	}
	
	/*
	 * 当前日期加上传入的时分秒时间
	 * date为一个日期，默认时间为00:00:00
	 * time为任意日期，只有时分秒有意义
	 */
	public static Date addTime(Date date,Date time){
		Calendar c = Calendar.getInstance();
		Calendar t = Calendar.getInstance();
		c.setTime(date);
		t.setTime(time);
		int mins =  t.get(Calendar.MINUTE);
		int hour = t.get(Calendar.HOUR_OF_DAY);
		c.add(Calendar.HOUR_OF_DAY,hour);
		c.add(Calendar.MINUTE,mins);
		
		return c.getTime();
	}
	
	/*
	 * 获取当前时间的间隔周数
	 */
	public static Date addWeek(Date date,int week){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, week * 7);
		
		return c.getTime();
	}
	
	/*
	 * 获取当前时间的间隔月数
	 */
	public static Date addMonth(Date date,int month){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		
		return c.getTime();
	}
	
	/*
	 * 获取当前时间的间隔月数
	 */
	public static Date addYear(Date date,int year){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, year);
		
		return c.getTime();
	}
	
	/*
	 * 获取当前时间的间隔季度
	 */
	public static Date addSeason(Date date,int season){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int curmon = c.get(Calendar.MONTH);//当前月份
		curmon = curmon + 3 * season;//减去季度的倍数
		c.add(Calendar.MONTH, curmon);
		
		return c.getTime();
	}
     /*
      * 日期计算
      * Calendar.MONTH，
      * Calendar.DATE
      * Calendar.HOUR
      * Calendar.MINUTE
      */
	public static Date CalcDate(Date date,int calcType,int val) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//c.add(Calendar.MONTH, val);		
		c.add(calcType, val);
		return c.getTime();
		
	}
	/*
	 * 计算日期
	 */
	public static Long CalcDatetoNum(Date date1,Date date2,int calcType){
		Calendar cal = Calendar.getInstance();  
		cal.setTime(date1);
		Long d1= cal.getTimeInMillis();
		cal.setTime(date2);
		Long d2= cal.getTimeInMillis();
		
		Long calcTypeNum;
		switch (calcType) {
		case Calendar.MINUTE:
			calcTypeNum=(long)1000*60;
			break;
		case Calendar.HOUR:
			calcTypeNum=(long)1000*3600;
			break;
		case Calendar.DATE:
			calcTypeNum=(long)1000*3600*24;
			break;
		default:
			calcTypeNum=1000L;
			break;
		}
		return (d2-d1)/calcTypeNum;
		
	}
	
	/**
	 * 计算date1和date2间隔几个月
	 * @param date1 <String>
	 * @param date2 <String>
	 * @return int
	 * @throws ParseException
	 */
	public static int getMonthSpace(Date date1, Date date2){
		int result = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		result += year * 12 + 1;
		return result;

	}
	
	//获取当前日期的星期
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    
	public static boolean isValidDate(String str,String formatString) {
	      boolean convertSuccess=true;
	       SimpleDateFormat format = new SimpleDateFormat(formatString);
	       try {
	          format.setLenient(false);
	          format.parse(str);
	       } catch (ParseException e) {
	           convertSuccess=false;
	       } 
	       return convertSuccess;
	}
}
