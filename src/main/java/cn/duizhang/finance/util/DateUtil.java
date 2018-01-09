package cn.duizhang.finance.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hanlinli on 2018/1/9.
 */
public class DateUtil {

    public static String getNowDtString(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        return fmt.format(calendar.getTime());
    }

}
