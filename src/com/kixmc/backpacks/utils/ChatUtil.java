package com.kixmc.backpacks.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatUtil {

    public final static char COLOR_CHAR = ChatColor.COLOR_CHAR;

    // Colorizes messages with preset colorcodes (&) and if using 1.16, applies hex
    // values via "&#hexvalue"
    public static String colorize(String input) {

        input = ChatColor.translateAlternateColorCodes('&', input);

        if (Bukkit.getVersion().contains("1.16")) {
            input = translateHexColorCodes("\\&#", "", input);
        }

        return input;
    }

    public static String translateHexColorCodes(String startTag, String endTag, String message) {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x" + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1) + COLOR_CHAR
                    + group.charAt(2) + COLOR_CHAR + group.charAt(3) + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5));
        }
        return matcher.appendTail(buffer).toString();
    }

}