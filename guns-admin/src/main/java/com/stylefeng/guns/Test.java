package com.stylefeng.guns;

import com.zte.datamask.name.ChinaNameMask;

import java.text.ParseException;

/**
 * Created by dx on 2018/3/11.
 */
public class Test {
    public static void main(String[] args) throws ParseException {
//        String hql = "Fri Jul 03 10:48:32 CST 2009";
//        SimpleDateFormat simples = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
//        Date date = simples.parse(hql);
//        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(simple.format(date));
//        Date now=new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String tablename=dateFormat.format(now);
//        System.out.println(tablename);
        maskTest();
    }

    public static void maskTest() {
        //  return AccountMask.masking_account_info_caesarcipher(value, flag);
        System.out.println(ChinaNameMask.masking_china_name_full_hash("段雄", "1"));
    }
}
