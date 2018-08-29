package me.glaremasters.superoof.utils;

import org.bukkit.ChatColor;

/**
 * Created by GlareMasters
 * Date: 8/29/2018
 * Time: 9:09 AM
 */
public class ColorUtil {

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
