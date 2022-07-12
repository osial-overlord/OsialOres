package org.osial.osialores.utils;

import org.bukkit.inventory.ItemStack;

public class OsialOresUtil {

    public static boolean isOsialOre(ItemStack i) {
        if (i != null) {
            if (NBTEditor.contains(i, "osialore")) {
                return true;
            }
        }

        return false;
    }

}
