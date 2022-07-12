package org.osial.osialores.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.osial.osialores.commands.real.GiveOreCommand;

import java.util.ArrayList;
import java.util.List;

public class OsialCommand implements CommandExecutor, TabCompleter {
    private List<SubCommand> subCommands = new ArrayList<>();

    public OsialCommand() {
        subCommands.add(new GiveOreCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        System.out.println("Command: " + command.getName());

        if (args.length == 0) {
            sender.sendMessage("/osialores <command>");
            return false;
        }

        /*
        Loop through each subcommand, get the name and compare it to args[0].
        If the name matches, find the total number of args after args[0] and check it against getArgsReq().
        If the number of args is correct, compile all args after args[0] into a new arraylist
        and pass it to the subcommand's perform method.
         */
        if (sender instanceof Player) {
            for (SubCommand subCommand : subCommands) {
                System.out.println("SubCommand: " + subCommand.getName());
                System.out.println("Args: " + args[0]);
                if (subCommand.getName().equalsIgnoreCase(args[0])) {
                    if (args.length - 1 >= subCommand.getArgsReq()) {
                        List<String> newArgs = new ArrayList<>();
                        for (int i = 1; i < args.length; i++) {
                            newArgs.add(args[i]);
                        }
                        System.out.println("Args: " + newArgs);
                        subCommand.perform((Player) sender, newArgs);
                        return true;
                    } else {
                        sender.sendMessage("/osialores " + subCommand.getName() + " <args>");
                        return false;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
