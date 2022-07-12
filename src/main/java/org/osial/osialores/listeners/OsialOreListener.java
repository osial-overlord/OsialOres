package org.osial.osialores.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.osial.osialores.OsialOres;
import org.osial.osialores.events.OsialOreBreakEvent;
import org.osial.osialores.events.OsialOrePlaceEvent;
import org.osial.osialores.ores.OsialOre;
import org.osial.osialores.utils.OsialOresUtil;

import java.util.Collection;

public class OsialOreListener implements Listener {

    private OsialOres plugin;

    public OsialOreListener(OsialOres plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
        ItemStack i = e.getItemInHand();
        if (OsialOresUtil.isOsialOre(i)) {
            e.setCancelled(true);

            //Instantiate Osial Ore
            Material handItem = i.getType();
            Location loc = e.getBlock().getLocation();
            OsialOre ore = new OsialOre(handItem, loc);

            OsialOrePlaceEvent orePlaceEvent = new OsialOrePlaceEvent(ore);
            Bukkit.getPluginManager().callEvent(orePlaceEvent);

            plugin.getHandler().addOsialOre(ore);
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {
        Location loc = e.getBlock().getLocation();
        if (plugin.getHandler().isOsialOre(loc)) {
            OsialOre ore = plugin.getHandler().getOsialOre(loc);
            OsialOreBreakEvent event = new OsialOreBreakEvent(ore);
            Bukkit.getPluginManager().callEvent(event);

            //Set ore to bedrock
            e.getBlock().setType(Material.BEDROCK);

            //Get the drops, and simulate them dropping
            Collection<ItemStack> drops = e.getBlock().getDrops();
            for (ItemStack drop : drops) {
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), drop);
            }

            e.setCancelled(true);
        }
    }

}
