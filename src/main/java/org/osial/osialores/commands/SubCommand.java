package org.osial.osialores.commands;

import org.bukkit.entity.Player;

import java.util.List;

public interface SubCommand {

    String getName();
    String getDescription();
    int getArgsReq();
    void perform(Player player, List<String> args);

}
