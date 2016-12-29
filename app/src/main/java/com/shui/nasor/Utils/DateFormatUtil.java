package com.shui.nasor.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者： max_Shui on 2016/12/26.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * http://www.cnblogs.com/bmbm/archive/2011/12/06/2342264.html
 */


public class DateFormatUtil {
    private  static  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
    public static Date StringToDate(String date)
    {

        try {
            Date mDate = sdf.parse(date);
            return mDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static long StringDateToLong(String date)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Long time=df.parse(date).getTime();
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
