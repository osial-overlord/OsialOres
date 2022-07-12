package org.osial.osialores.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {

    //ColorUtils.java - A color utility class made by Osial.

    public static final String HEX_PATTERN_TRIANGLE_BRACKETS = "\\<#[0-9A-F]{6}\\>";
    public static final String HEX_PATTERN_SQUARE_BRACKETS = "\\[#[0-9A-F]{6}\\]";
    public static final String HEX_PATTERN_CURLY_BRACKETS = "\\{#[0-9A-F]{6}\\}";
    public static final String HEX_PATTERN_NO_BRACKETS = "#[0-9A-F]{6}";

    /*
    A public static translation method which combines the spigot translation & hex translations.
     */
    public static String translate(String s) {
        return t(hexTranslate(s));
    }

    /*
    A method which takes a given string and applies spigot chat colors to it
     */
    private static String t(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    /*
    A method which uses the above regex and translates the hex codes to the spigot chat colors
     */
    private static String hexTranslate(String s) {
        Pattern pattern = Pattern.compile(HEX_PATTERN_TRIANGLE_BRACKETS);

        StringBuilder sb = new StringBuilder();

        //Replace the Color Code <#FFFFFF>
        //With the format &F&F&F&F&F&F

        Matcher matcher = pattern.matcher(s);

        while (matcher.find()) {
            String hex = matcher.group();
            String colorCode = hex.substring(2, hex.length() - 1);
            //For each letter in "colorCode", append & followed by the letter
            for (char c : colorCode.toCharArray()) {
                sb.append("&").append(c);
            }

            //Replace the hex code with the color code
            s = s.replace(hex, sb.toString());
        }

        //Repeat the process for the square brackets
        pattern = Pattern.compile(HEX_PATTERN_SQUARE_BRACKETS);
        matcher = pattern.matcher(s);
        sb = new StringBuilder();

        while (matcher.find()) {
            String hex = matcher.group();
            String colorCode = hex.substring(2, hex.length() - 1);
            for (char c : colorCode.toCharArray()) {
                sb.append("&").append(c);
            }
            s = s.replace(hex, sb.toString());
        }

        //Repeat the process for the curly brackets
        pattern = Pattern.compile(HEX_PATTERN_CURLY_BRACKETS);
        matcher = pattern.matcher(s);
        sb = new StringBuilder();

        while (matcher.find()) {
            String hex = matcher.group();
            String colorCode = hex.substring(2, hex.length() - 1);
            for (char c : colorCode.toCharArray()) {
                sb.append("&").append(c);
            }
            s = s.replace(hex, sb.toString());
        }

        //Repeat the process for the no brackets
        pattern = Pattern.compile(HEX_PATTERN_NO_BRACKETS);
        matcher = pattern.matcher(s);
        sb = new StringBuilder();

        while (matcher.find()) {
            String hex = matcher.group();
            String colorCode = hex.substring(1);
            for (char c : colorCode.toCharArray()) {
                sb.append("&").append(c);
            }
            s = s.replace(hex, sb.toString());
        }

        return s;
    }

}
