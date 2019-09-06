package com.upphoto.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUtilOp {

    public static String setPicName(String type) {

        final SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss"); // 时间格式化的格式
        Random random = new Random();

        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
        String nowTimeStr = sDateFormat.format(new Date()); // 当前时间
        return nowTimeStr + rannum + type;
    }
}
