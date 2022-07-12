package org.osial.osialores.ores;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

@Data
public class OsialOre {

    private Material material;
    private Location location;

    public OsialOre(Material material, Location location) {
        this.material = material;
        this.location = location;
    }

    public void regen() {
        Block block = location.getBlock();
        block.setType(material);
    }

}
