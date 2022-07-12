package org.osial.osialores.api;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.osial.osialores.OsialOres;
import org.osial.osialores.ores.OsialOre;

public class OsialOresAPI {

    /*
    This class is meant to be an interface to regular users to be able to use the plugins methods in a simple static way.
     */

    public static OsialOres getPluginInstance() {
         return JavaPlugin.getPlugin(OsialOres.class);
    }

    public OsialOre getOre(Location location) {
        return getPluginInstance().getHandler().getOsialOre(location);
    }

}
