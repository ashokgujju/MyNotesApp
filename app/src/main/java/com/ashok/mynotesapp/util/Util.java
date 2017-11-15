package com.ashok.mynotesapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ashok on 15/11/17.
 */

public class Util {
    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(new Date());
    }
}