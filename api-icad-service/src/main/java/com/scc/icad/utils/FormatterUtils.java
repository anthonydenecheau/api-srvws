package com.scc.icad.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatterUtils {

    public static String dateToString(Date sDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(sDate);
    }

    public static String handleStringNullValue(Object value) {
        return (value == null ? "" : (String) value);
    }
}
