package org.osial.osialores.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.osial.osialores.ores.OsialOre;

public class OsialOreBreakEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @Getter private OsialOre ore;

    public OsialOreBreakEvent(OsialOre ore) {
        this.ore = ore;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
