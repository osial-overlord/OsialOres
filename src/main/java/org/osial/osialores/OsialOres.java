package org.osial.osialores;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.osial.osialores.commands.OsialCommand;
import org.osial.osialores.listeners.OsialOreListener;
import org.osial.osialores.ores.OsialOreHandler;

@Getter
public final class OsialOres extends JavaPlugin {

    private OsialCommand command;
    private OsialOreListener listener;
    @Getter private OsialOreHandler handler;

    @Override
    public void onEnable() {
        register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void register() {
        // Register commands
        command = new OsialCommand();
        getCommand("osialores").setExecutor(command);
        getCommand("osialores").setTabCompleter(command);

        // Register listeners
        listener = new OsialOreListener(this);
        getServer().getPluginManager().registerEvents(listener, this);

        // Register handlers
        handler = new OsialOreHandler(this);
    }
}
