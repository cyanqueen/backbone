package org.backbone.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bianliang (06/04/2017)
 */
public class SqlUtils {

    private static final Pattern HUMP_PATTERN = Pattern.compile("\\.(.)");

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static String hump(String src) {
        Matcher matcher = HUMP_PATTERN.matcher(src);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String find = matcher.group(1);
            matcher.appendReplacement(sb, find.toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
