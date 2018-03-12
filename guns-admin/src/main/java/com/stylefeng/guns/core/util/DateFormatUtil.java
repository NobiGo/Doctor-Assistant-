package com.stylefeng.guns.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dx on 2018/3/11.
 */
public class DateFormatUtil {
    /**
     * 将字符串格式为“Fri Jul 03 10:48:32 CST 2009”转化为正常的字符串
     *
     * @return
     */
    public static String changeDate(String value) throws Exception {
        SimpleDateFormat simples = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        Date date = simples.parse(value);
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simple.format(date);
    }


    public static String getDate() {
        //将Date类型转成String类型,以String作为表名，保证表名唯一
        Date now=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String tablename=dateFormat.format(now);
        System.out.println(tablename);
        return null;
    }
}
