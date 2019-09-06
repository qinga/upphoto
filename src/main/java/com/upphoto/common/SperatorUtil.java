package com.upphoto.common;

public class SperatorUtil {
    private static String seperator = System.getProperty("file.separator");
    private static String dir = "upphoto";

    public static String getPath(String path) {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = path.replace("\\", seperator);
        } else {
            basePath = path.replace("/", seperator);
        }
        int baseLen = basePath.indexOf(seperator + dir);
        basePath = basePath.substring(0, baseLen) + seperator + "images" + seperator + "rawImages";
        return basePath;
    }
}
