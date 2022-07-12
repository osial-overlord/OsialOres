package org.osial.osialores.commands.real;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.osial.osialores.commands.SubCommand;
import org.osial.osialores.events.OsialOreGiveEvent;
import org.osial.osialores.utils.NBTEditor;

import java.util.List;

public class GiveOreCommand implements SubCommand {
    @Override
    public String getName() {
        return "giveore";
    }

    @Override
    public String getDescription() {
        return "A command to give players a custom osial ore.";
    }

    @Override
    public int getArgsReq() {
        return 1;
    }

    @Override
    public void perform(Player player, List<String> args) {
        if (player.hasPermission("osialores.give")) {
            Material mat = Material.getMaterial(args.get(0));
            System.out.println("Material: " + mat);

            if (mat == null) {
                System.out.println("Material not found.");
                return;
            }

            System.out.println("Material found.");
            ItemStack ore = new ItemStack(mat);
            ore.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            ore = NBTEditor.set(ore, mat.toString(), "osialore");
            System.out.println("Ore created.");

            OsialOreGiveEvent event = new OsialOreGiveEvent(player);
            player.getServer().getPluginManager().callEvent(event);

            player.getInventory().addItem(ore);
        }
    }
}
