/*
 * �������� 2006-6-21
 *
 * ����  ë־Ǭ  (zqmao)
 *
 * ��Ŀ���ƣ��籨�ն� (NTS)
 * 
 * Copyright 2005 baidu, Inc. All right reserved.
 */
package com.baidu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author 123
 *
 * ����ʱ�䴦����,ȡ�õ�ǰ�����������
 */
public class BaseTime
{
    /** <code>SECOND</code> 15min */
    public static final long SECOND = 1000;

    /** <code>MIN</code> ��ע�� */
    public static final long MIN = 60 * SECOND;

    /** <code>HOUR</code> ��ע�� */
    public static final long HOUR = 60 * MIN;

    /** <code>DAY</code> ��ע�� */
    public static final long DAY = 24 * HOUR;

    /** <code>WEEK</code> 7day */
    public static final long WEEK = 7 * DAY;

    /** <code>MONTH</code> 31day */
    public static final long MONTH = DAY * 30;

    /** <code>SEC_SEC</code> ��ע�� */
    public static final int SEC_SEC = 0;

    /** <code>MIN_MIN</code> ��ע�� */
    public static final int MIN_MIN = 1;

    /** <code>HOUR_HOUR</code> ��ע�� */
    public static final int HOUR_HOUR = 2;

    /** <code>DAY_DAY</code> ��ע�� */
    public static final int DAY_DAY = 3;

    /** <code>WEEK_WEEK</code> ��ע�� */
    public static final int WEEK_WEEK = 4;

    /** <code>MONTH_MONTH</code> ��ע�� */
    public static final int MONTH_MONTH = 5;

	//���ڸ�ʽ
    public static String dateFormat = "yyyy-MM-dd";
    //ʱ���ʽ
    public static String timeFormat = "HH:mm:ss";
    //������ڸ�ʽ
    public static String chinaFormat = dateFormat + " " + timeFormat;

    // mm/dd/yyy HH:mm��ʽ
    public static String dateTimeString = "MM/dd/yyyy HH:mm";
    /**
     * @return ��������
     */
    public static Locale getCurrentLocale()
    {
        return Locale.getDefault();
    }

    /**
     * @return ��ǰϵͳʱ�� (String ��ʽ��
     */
    public static String getTime()
    {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(chinaFormat);
        Date date = new Date();
        return bartDateFormat.format(date);
    }
    /**
     * @return ��ǰϵͳʱ�� (String ��ʽ��
     */
    public static String getShortTime()
    {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy��MM��dd��");
        Date date = new Date();
        return bartDateFormat.format(date);
    }

    /**
     * @param format
     * @return ��ǰϵͳʱ�� (String ��ʽ��
     */
    public static String getShortTime(String format)
    {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return bartDateFormat.format(date);
    }

    public static String getDateBeginTime() {
    	String date = getShortTime(dateFormat);
    	System.err.println(date + " 00:00:00");
    	return date + " 00:00:00";
    }
    /**
     * �õ���ǰ��Сʱ��
     * 
     * @return int
     */
    public static int getHour()
    {
        Locale currentLocale = getCurrentLocale();

        Calendar rightNow = Calendar.getInstance(currentLocale);
        Date currentDate = rightNow.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("HH");
        return Integer.parseInt(formater.format(currentDate));
    }

    /**
     * �õ���ǰ�ķ�����
     * 
     * @return int
     */
    public static int getMinute()
    {
        Locale currentLocale = getCurrentLocale();
        Calendar rightNow = Calendar.getInstance(currentLocale);
        Date currentDate = rightNow.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("mm");
        return Integer.parseInt(formater.format(currentDate));
    }

    /**
     * �õ���ǰ������
     * 
     * @return int
     */
    public static int getWeekInYear()
    {
        Locale currentLocale = getCurrentLocale();
        Calendar rightNow = Calendar.getInstance(currentLocale);
        Date currentDate = rightNow.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("ww");
        return Integer.parseInt(formater.format(currentDate));
    }

    /**
     * @return ��ǰ�µĵ�һ��
     */
    public static String getFirstOfMonth()
    {//TODO deprecate this method
        String strMonth = null;
        int month = getMonth();
        if (month < 10)
            strMonth = "0" + month;
        else
            strMonth = "" + month;
        return getYear() + "-" + strMonth + "-" + "01 00:00:00";
    }

    /**
     * �õ���ǰ��������
     * 
     * @return int
     */
    public static int getDayInWeek()
    {
        Locale currentLocale = getCurrentLocale();

        Calendar rightNow = Calendar.getInstance(currentLocale);
        Date currentDate = rightNow.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("FF");
        return Integer.parseInt(formater.format(currentDate));
    }

    /**
     * �õ���ǰ�����Ǽ���
     * 
     * @return int
     */
    public static int getDayInMonth()
    {
        Locale currentLocale = getCurrentLocale();

        Calendar rightNow = Calendar.getInstance(currentLocale);
        Date currentDate = rightNow.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("dd");
        return Integer.parseInt(formater.format(currentDate));
    }

    /**
     * �õ���ǰ������
     * 
     * @return int
     */
    public static int getMonth()
    {
        Locale currentLocale = getCurrentLocale();
        Calendar rightNow = Calendar.getInstance(currentLocale);
        Date currentDate = rightNow.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("MM");
        return Integer.parseInt(formater.format(currentDate));
    }

    /**
     * �õ���ǰ������
     * 
     * @return int
     */
    public static int getSecond()
    {
        Locale currentLocale = getCurrentLocale();
        Calendar rightNow = Calendar.getInstance(currentLocale);
        Date currentDate = rightNow.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("ss");
        return Integer.parseInt(formater.format(currentDate));
    }

    /**
     * �õ���ǰ����
     * 
     * @return int
     */
    public static int getYear()
    {
        Locale currentLocale = getCurrentLocale();
        Calendar rightNow = Calendar.getInstance(currentLocale);
        Date currentDate = rightNow.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy");
        return Integer.parseInt(formater.format(currentDate));
    }
    
    /**
     * ��ȡ��ǰʱ��
     * 
     * @return Date
     */
    public static Date getNow()
    {
        Calendar rightNow = Calendar.getInstance();
        Date dt = rightNow.getTime();
        return dt;
    }


}
