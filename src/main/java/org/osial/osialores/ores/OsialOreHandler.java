package org.osial.osialores.ores;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;
import org.osial.osialores.OsialOres;

import java.util.ArrayList;
import java.util.List;

public class OsialOreHandler implements Listener {

    private final int REGEN_TIME = 60;
    private BukkitTask regenTask;
    private List<OsialOre> osialOres;

    public OsialOreHandler(OsialOres plugin) {
        osialOres = new ArrayList<>();

        this.regenTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (OsialOre ore : osialOres) {
                ore.regen();
            }
        }, 0L, REGEN_TIME);
    }

    public OsialOre getOsialOre(Location location) {
        for (OsialOre ore : osialOres) {
            if (ore.getLocation().equals(location)) {
                return ore;
            }
        }
        return null;
    }

    public void addOsialOre(OsialOre osialOre) {
        osialOres.add(osialOre);
    }

    public void removeOsialOre(OsialOre osialOre) {
        osialOres.remove(osialOre);
    }

    public boolean isOsialOre(Location location) {
        for (OsialOre osialOre : osialOres) {
            if (osialOre.getLocation().equals(location)) {
                return true;
            }
        }
        return false;
    }

}
