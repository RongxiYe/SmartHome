package com.group.smarthome.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormat {
    public static String tf(LocalDateTime ld){

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH：mm：ss");
        return df.format(ld);
    }
}
