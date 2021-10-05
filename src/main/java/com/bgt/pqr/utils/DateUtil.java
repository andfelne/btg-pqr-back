package com.bgt.pqr.utils;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static Date getCurrentDate() {
        return GregorianCalendar.getInstance().getTime();
    }
}
