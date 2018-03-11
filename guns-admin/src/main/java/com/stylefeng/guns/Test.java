package com.stylefeng.guns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dx on 2018/3/11.
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        String hql = "Fri Jul 03 10:48:32 CST 2009";
        SimpleDateFormat simples = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        Date date = simples.parse(hql);
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simple.format(date));
    }
}
